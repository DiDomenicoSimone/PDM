package due.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Main extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final EditText text = (EditText) findViewById(R.id.editText1);	//Il metodo "findViewById" permette di "prendere" il riferimento ad una risorsa
        Button startButton =(Button) findViewById (R.id.button1);	//esterna,ad esempio un edittext o un button, tramite l'id relativo contenuto nella classe R.java
        startButton.setOnClickListener (new OnClickListener()		//Con l'OnClickListener è possibile gestire l'evento di click sul buttone
        {
        	public void onClick(View v)
        	{
        		Intent intent = new Intent (Main.this, Second.class); //Si genera un intent per il lancio della seconda activity
        		String iltesto = text.getText().toString();		 //Invocando il metodo "getText().toString() sull'edittext,ritorna la stringa scritta in quest'ultimo 
        		intent.putExtra("iltestonelbox", iltesto);	//Tramite il metodo "putExtra()" si aggiunge all'intent un valore associato al campo "iltestonelbox",che sarà trasportato e quindi disponibile nell'altra actvity
        		startActivity(intent); //Viene lanciata la seconda activity
        	}
        });
		
	}
}		

