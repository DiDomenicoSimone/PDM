package swc.image;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.ToggleButton;

public class SwitchImageActivity extends Activity {
	/** Called when the activity is first created. */
	ImageView iv;	//Si crea una variabile d'istanza di classe ImageView

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);					//Si associa come View radice il layout descritto da main.xml
		iv = (ImageView) findViewById(R.id.imageView1); //Si prende il riferimento all'imageview creata nel layout main.xml
		ToggleButton state = (ToggleButton) findViewById(R.id.toggleButton1); //Si prende il rifermimeto al togglebutton, ovvero un bottone che ha 
																			  //associato anche uno stato, checked o unchecked
		state.setOnCheckedChangeListener(new OnCheckedChangeListener() {	//Si aggancia al togglebutton un listener che permette di ascoltare lo stato del bottone 

			public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) //Questo metodo permette di cambiare l'immagine associata all'imageview nel layout,in funzione dello stato del bottone
			{ 
				{
					if (isChecked) 
					{	
						iv.setImageResource(R.drawable.button_pause);	//Se lo stato del bottone è checked,allora viene associato all'imageview l'immagine button_pause	
					} 
					else 
					{
						iv.setImageResource(R.drawable.button_play);	//Se lo stato del bottone è unchecked,allora viene associato all'imageview l'immagine button_play
					}
				}
			}
		});

	}
}

/* Nella cartella res/drawable sono state aggiunte le due immagini relative ai bottoni di play e pausa,e viene preso il riferimento tramite la classe R */