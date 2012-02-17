package helloWorld.apk;

import android.app.Activity;
import android.os.Bundle;

public class HelloWorldActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
}

/*  Questo è il progetto android più semplice,in quanto viene creata una nuova classe "HelloWorldActivity" che estende la  
 	classe Activity,ereditandone quindi tutti i metodi, e viene sovrascritto (Override) il metodo "onCreate",in cui all'interno
	si richiama semplicemente il costruttore della super classe.Inoltre si utilizza l'istruzione "setContentView(R.layout.main)"
	con questo metodo si imposta come layout associato all'activity corrente, quello identificato dalla risorsa con percorso 
	R.layout.main. Infatti in Android tutte le risorse esterne, così come i layout, costruiti ad esempio in XML, possono essere
	richiamati nel codice Java ,attraverso la classe R.java(generata automaticamente) e attravero la quale ogni risorse è 
	univocamente identificata da un numero esadecimale (id).

*/