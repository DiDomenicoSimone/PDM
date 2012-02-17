package text.Dialer;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class TextDialerActivity extends Activity {
    /** Called when the activity is first created. */
	 EditText etext;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        etext = (EditText) findViewById (R.id.editText1); //Si prende il riferimento all'editor di testo 
        Button digitButton =(Button) findViewById (R.id.button1); //Si prende il riferimento al bottone relativo alla "digitazione"
        Button callButton =(Button) findViewById (R.id.button2); //Si prende il riferimento al bottone relativo alla "chiamata"
        digitButton.setOnClickListener (new OnClickListener()
        {
        	public void onClick(View v)
        	{
        		String telString = etext.getText().toString(); 			  //Metto nella variabile "telString" ciò che c'è scritto nell'editor
        		String telUriString = "tel:" + telString;
        		Uri telURI = Uri.parse(telUriString); 			//Tramite un'operazione di parser si trasforma il numero sotto forma di stringa in una URI 
        		Intent intent = new Intent(Intent.ACTION_DIAL); //Si lancia un Intent implicito,ovvero non specificando chi lo gestirà,ma la cui azione è quella di visualizzare la tastiera numerica
        		intent.setData(telURI); 						//Si associa un dato all'intent, sul quale dovrà essere eseguita l'azione specificata(ACTION_DIAL),la coppia azione-dato identificano una activity
        		startActivity(intent);							//Si avvia una nuova activity passando l'intent come parametro
        	}
        });
    
        etext.addTextChangedListener(new PhoneNumberFormattingTextWatcher());	//Questo metodo invocato sull'edittext permette, di formattare i caratteri digitati nell'editor, adattandoli a numeri,nel caso vengono scritte delle lettere
        callButton.setOnClickListener (new OnClickListener()
        {
        	public void onClick(View v)
        	{
        		EditText etext = (EditText) findViewById (R.id.editText1);
        		String telString = etext.getText().toString();
        		String telUriString = "tel:" + telString;
        		Uri telURI = Uri.parse(telUriString);	
        		Intent intent = new Intent(Intent.ACTION_CALL); //Si lancia un intent implicito,alla quale è associato l'azione di "ACTION_CALL",ovvero permette di compiere una chiamata(è necessario aggiungere nel manifest il permesso CALL_PHONE)
        		intent.setData(telURI);	//Si associa un dato all'intent,in questo caso la URI impostata andrà a specificare il numero che deve essere chiamato,cioè il dato specifica l'azione
        		startActivity(intent);	//Viene lanciata l'activity,che è proprio descritta dalla coppia azione-dato associata all'intent
        	}
        });

    }
}