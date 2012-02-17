package due.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Second extends Activity {

	
	final static String TAG = "SECOND ACTIVITY";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG,"onCreate");
		setContentView(R.layout.second);
		TextView label = (TextView) findViewById(R.id.textView1); 
		String iltestoricevuto = getIntent().getExtras().getString("iltestonelbox"); //Tramite questi metodi,ritorna la stringa associata all'intent corrispondente al campo "iltestonelbox"
		label.setText(iltestoricevuto);	//Aggiungo,tramite il metodo setText(), la stringa da visualizzare nella textview
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.d(TAG,"onStop");
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		Log.d(TAG,"onPause");
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		Log.d(TAG,"onResume");
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "onDestroy");
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		Log.d(TAG, "onStart");
	}
	
	protected void onReStart() {
		super.onRestart();
		Log.d(TAG,"onReStart");
	}
	
}

/*Sovrascrivendo ciascun metodo della classe Activit, e inserendo in ognuno di questi un log, con il nome del metodo stesso,allora 
  durante l'esecuzione dell'applicazione, sarà possibile visualizzare nel logcat le varie fasi del ciclo di vita di una activity*/