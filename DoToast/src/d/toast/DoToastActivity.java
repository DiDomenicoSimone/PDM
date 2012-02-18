package d.toast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class DoToastActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
	EditText inserisci_testo ;		//Si instanzia un EditText,come variabile di istanza
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        LinearLayout ll = new LinearLayout (DoToastActivity.this);	//Si crea un LinearLayout,passandogli come context la classe stessa,in quanto estendendo Activity,contiene un context
        ll.setOrientation (LinearLayout.VERTICAL);					//Imposta l'orientazione del LinearLayout, in questo caso verticale
        ll.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT)); //Imposta i parametri di altezza e larghezza del layout,che essendo settati al valore FILL_PARENT,occuperà l'intero schermo 
        
        Button btn = new Button (this); //Si crea un nuovo widget,di classe Button,passandogli il contesto dell'activity corrente
        btn.setText("Saluta");			//Si imposta il testo che verrà visualizzato all'interno del bottone
        btn.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT)); //Si impostano i parametri di altezza e larghezza del bottone.
        
        inserisci_testo = new EditText(this); 
        inserisci_testo.setText(null);
        inserisci_testo.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT)); //Si impostano i parametri di altezza e larghezza dell'edittext.
        
        ll.addView (inserisci_testo);	//Si aggiunge l'edittext al LinerLayout creato in precedenza
        ll.addView(btn);				//Si aggiunge il bottone al LinerLayout creato in precedenza
        setContentView(ll);				//Si imposta il LinearLayout ll come View radice associata all'activity

        btn.setOnClickListener(this); //Si aggancia un evento di click al bottone
    }
        	public void onClick(View v) 	//Quando il bottone viene cliccato verrà chiamato questo metodo
        	{
				String s = inserisci_testo.getText().toString();	//Si estrae e si copia in una stringa il contenuto dell'edittext
        		Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show(); //Il Toast permette,tramite il metodo "makeText" e "show", di visualizzare sullo schermo un messaggio che gli viene 
        																			  //passato come parametro,in questo caso è la stringa digitata nell'edittext,impostanto anche la durata della
        																			  //della visualizzazione.
        	}
        
     
        
   }

