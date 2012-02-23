package indovina.numero;


import java.util.Timer;
import java.util.TimerTask;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends Activity implements MessageReceiver
{
	ConnectionManager connection;
	enum Stato {WAIT_FOR_START,WAIT_FOR_START_ACK, USER_SELECTING, WAIT_FOR_NUMBER_SELECTION, WAIT_FOR_BET, USER_BETTING};	//Stati del gioco
	Stato statoCorrente;
	protected static final int SHOW_TOAST = 0;
	String selectedNumber;
	
	
	Timer timer = new Timer();				//Si definisce un nuovo timer
	TimerTask sendstart = new TimerTask()	//Si definisce un'azione (TimerTask) associata al timer
	{

		@Override
		public void run()
		{
			if (statoCorrente==Stato.WAIT_FOR_START_ACK)
			{
				connection.send("START");	//Si invia il messaggio "START" se si è nello stato "WAIT_FOR_START_ACK"
			}
			else
			{
				Log.d("TAG","Sending START but the state is" +statoCorrente); //Se lo stato non è "WAIT_FOR_START_ACK" allora significa che lo "START" è già stato inviato
			}
		}
	};
	
	final Handler handler = new Handler()
	{
		@Override
		public void handleMessage(android.os.Message msg)	//Viene richiamato automaticamente ogni volta che arriva un messaggio al thread principale
		{
			switch (msg.what)	//Il campo "what" della classe Message, è un intero che identifica il contenuto del messaggio
			{
				case Main.SHOW_TOAST:
					Toast.makeText(Main.this,msg.getData().getString("toast"),Toast.LENGTH_LONG).show();	//Visualizza il messaggio con chiave "toast"
					break;
				default :
					super.handleMessage(msg);
			}
			
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		TextView tv = (TextView) findViewById(R.id.textview);	//Si prende il riferimento alla text view
		String nomeProprio = getIntent().getExtras().getString("nome proprio");
		String nomeAvversario = getIntent().getExtras().getString("nome avversario");
		tv.setText(nomeProprio+"	"+nomeAvversario); 	//Si visualizzano nella textview il nome proprio e dell'avversario
		connection = new ConnectionManager(nomeProprio,nomeAvversario,this);	//Si inizializza una nuova connessione
		if (nomeAvversario.hashCode()<nomeProprio.hashCode())	
		{
				timer.schedule(sendstart, 1000,5000);		//Inizia "nomeProprio",e si invia tramite il il TimerTask "sendStart" il messaggio si "START" ogni 5 s
				statoCorrente = Stato.WAIT_FOR_START_ACK;	//Si imposta lo stato dello partita a "WAIT_FOR_START_ACK"
		}
		else
		{													//Inizia "nomeAvversario"			
			statoCorrente=Stato.WAIT_FOR_START;				//Si imposta lo stato della partita a "WAIT_FOR_START", in quanto è necessario attendere che l'avversario invii lo "START"
		}
		
		Button btn1 = (Button) findViewById(R.id.button1);
		Button btn2 = (Button) findViewById(R.id.button2);
		Button btn3 = (Button) findViewById(R.id.button3);
		btn1.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				numberSelected(v);
			}
		});
		
		btn2.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				numberSelected(v);
			}
		});
		
		btn3.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				numberSelected(v);
			}
		});
		
	}
	
		
		public void receiveMessage (String body)
		{
			if (body.equals("START"))
			{
				if (statoCorrente==Stato.WAIT_FOR_START)
				{
					connection.send("STARTACK");		//Se il messaggio ricevuto è "START" e si è nello stato "WAIT_FOR_START" allora si invia l'ack relativo al messaggio di "START"
					Message osmsg = handler.obtainMessage(Main.SHOW_TOAST);	//Il metodo "obtainMessage()" ritorna un messaggio e in questo caso si imposta il campo "what" del messaggio che ritorna
																			//settando il campo "what" del messaggio "osmsg" pari a "MAIN.SHOW_TOAST" (che vale 0), allora l'handler quando riceve 
																			//quando riceve il messaggio eseguirà la visualizzazione del Toast,visualizzando il testo "Scegli il numero"     
					Bundle b = new Bundle ();
					b.putString("toast", "Scegli un numero");
					osmsg.setData(b);
					handler.sendMessage(osmsg);			//Tramite l'handler si informa l'utente che deve scegliere un numero
					statoCorrente=Stato.USER_SELECTING;	//Si imposta lo stato del giocatore a "USER_SELECTING"
					
				}
				else 
				{
					Log.e("TAG","Ricevuto START ma lo stato è "+statoCorrente);
				}
			}
			else if (body.equals("STARTACK"))
			{
				if (statoCorrente==Stato.WAIT_FOR_START_ACK)	//Se si riceve il messaggio "STARTACK" e lo stato era "WAIT_FOR_START_ACK" allora si imposta lo stato "WAIT_FOR_NUMBER_SELECTION"
				{
					statoCorrente=Stato.WAIT_FOR_NUMBER_SELECTION;
				}
				else
				{
					Log.e("TAG", "Ricevuto STARTACK ma lo stato è "+statoCorrente);
				}
			}
			else if (body.startsWith("SELECTED"))	//Verifica se nel prefeisso di "body" è presente la stringa "SELECTED",che implica che l'avversario ha scelto il numero
				{
					if(statoCorrente==Stato.WAIT_FOR_NUMBER_SELECTION)
					{
						selectedNumber = body.split(":")[1];	//Si preleva il numero selezionato dall'avversario
						Message osmsg = handler.obtainMessage(Main.SHOW_TOAST);
						Bundle b = new Bundle();
						b.putString("toast", "Indovina il numero");
						osmsg.setData(b);
						handler.sendMessage(osmsg);			//Si comunica al giocatore che deve indovinare il numero selezionato dall'avversario
						statoCorrente=Stato.USER_BETTING;
					}
					else
					{
						Log.e("TAG", "Ricevuto SELECTED ma lo stato è "+statoCorrente);
					}
				}
			else if (body.startsWith("BET"))			//Se il giocatore riceve il messaggio che inizia con "BET",contiene l'esito della scommessa, valutato in funzione di "Y" e "N"
				{
					if(statoCorrente==Stato.WAIT_FOR_BET)
					{
						String result = body.split(":")[1];
						Message osmsg =handler.obtainMessage(Main.SHOW_TOAST);
						Bundle b = new Bundle();
						if (result.equals("Y"))
							b.putString("toast", "Hai perso, il tuo avversario ha indovinato");
						else
							b.putString("toast","Hai vinto, il tuo avversario ha sbagliato");
						osmsg.setData(b);
						handler.sendMessage(osmsg);
						statoCorrente=Stato.WAIT_FOR_NUMBER_SELECTION;
					}
					else
					{
						Log.e("TAG","Ricevuto SELECTED ma lo stato è: "+statoCorrente);
					}
				}
			
		}
		
		public void numberSelected (View v)
		{
			Button b = (Button) v;
			String number = b.getText().toString();
			if (statoCorrente==Stato.USER_SELECTING)	//Se lo stato del giocatore è "USER_SELECTING",questo implica l'aver ricevuto il messaggio di "START"
			{
				connection.send("SELECTED:"+number);	//Si invia all'altro giocatore il numero selezionato tramite il bottone
				statoCorrente=Stato.WAIT_FOR_BET;		//Si imposta lo stato del giocatore a "WAIT_FOR_BET"
			}
			else if (statoCorrente==Stato.USER_BETTING)	//Se lo stato del giocatore è "USER_BETTING",questo implica che deve selezionare con i bottoni il numero da indovinare
			{
				String bet = b.getText().toString();	//Si preleva il numero selezionato con il bottone
				if (bet.equals(selectedNumber))			//e se questo coincide con quello digitato dall'avversario allora il giocatore ha vinto
				{
					connection.send("BET:Y" + bet);
					Toast.makeText(this,"Bravo hai indovinato ora tocca a te", Toast.LENGTH_LONG).show();
					statoCorrente=Stato.USER_SELECTING;
				}
				else 
				{
					connection.send("BET:N" + bet);
					Toast.makeText(Main.this, "Peccato non hai indovinato, ora tocca a te",Toast.LENGTH_LONG).show();
				}
			statoCorrente=Stato.USER_SELECTING;
		}
	}
}
		



