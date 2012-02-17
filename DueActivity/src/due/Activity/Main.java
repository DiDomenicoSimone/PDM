package due.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Main extends Activity {
    /** Called when the activity is first created. */
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);								//Viene invocato il costruttore della superclasse
        setContentView(R.layout.main);									//Viene settato il content della schermata con la View passata come parametro, da una risorsa esterna
        Button startButton = (Button) findViewById (R.id.startButton);	//Viene creato un oggetto di classe Button, e si prende il riferimento con l'id relativo 
        startButton.setOnClickListener (new OnClickListener()			//Si aggancia al bottone un evento di click, e lo si gestisce tramite override del metodo "onClicK"
        {	
        	public void onClick(View v)
        	{
        		startActivity(new Intent(Main.this, Second.class));		//Tramite il metodo "startActivity" viene lanciata una nuova activity, ovvero "Second.java",e ciò
        																//viene realizzato tramite l'intent.
        	}
        });
    }
}