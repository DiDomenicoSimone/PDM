package my.player;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MyPlayerActivity extends Activity {
    /** Called when the activity is first created. */
	MediaPlayer mp;			//Variabile d'istanza di classe MediaPlayer
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main); 
        mp=MediaPlayer.create(MyPlayerActivity.this, R.raw.dst); 	//Viene creato il media player tramite il metodo "create" e passandogli come riferimento  il brano da riprodurre,
        															//contenuto nella cartella res/raw;essendo una risorsa esterna viene preso il riferimento tramite l'id della classe R
        Button startButton = (Button) findViewById (R.id.button1);	//Viene preso il riferimento al bottone creato nel layout
        startButton.setOnClickListener (new OnClickListener()		//Si aggancia un evento di click al bottone
        {
        	public void onClick(View v)
        	{
        		mp.start();		//Quando viene cliccato il bottone "startButton" viene avviata l'esecuzione del media player
        	}
        });
        Button stopButton = (Button) findViewById (R.id.button2);
        stopButton.setOnClickListener (new OnClickListener()
        {
        	public void onClick(View v)	
        	{
        		mp.pause(); 	//Quando viene cliccato il bottone "stopButton" viene interrotta l'esecuzione del media player
        	}
        });
        
        
        
    }
    @Override
    public void onDestroy()
    {
    	mp.release();	//Si riscrive il metodo "onDestroy" per chiudere il media player,altrimenti alla chiusura dell'app tramite
    					//tasto "back", il media player rimarrebbe attivo e quindi continuerebbe l'esecuzione della traccia audio
    }
}