package drag.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class MyView extends View {	//Si crea una classe estendendo la classe "View" ovvero quella alla quale appartengono tutte le componenti grafiche di un'app

	private int x=100;			//Coordinata x iniziale dell'immagine bitmap
	private int y=100;			//Coordinata y iniziale dell'immagine bitmap
	private Bitmap img=null;	//Si crea un oggetto "Bitmap"
	private boolean dragOn=false; //Flag per la gestione degli eventi relativi all'immagine bitmap
	
	public MyView(Context context) //Si definisce il costruttore della classe "MyView",che permette di caricare nel bitmap "img"
	{								//l'immagine presente nella cartella res/drawable con id "ic_launcher"
		super(context);
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inJustDecodeBounds = true;
		img = BitmapFactory.decodeResource(context.getResources(),R.drawable.ic_launcher);
	}
	
	@Override
	protected void onDraw(Canvas canvas)
	{										//Il metodo onDraw è necessario per disegnare il bitmap sullo schermo,e ciò viene fatto
		canvas.drawBitmap(img,x,y,null);	//attraverso un oggetto di classe Canvas, sul quale si invoca il metodo drawBitmap, al quale
	}										//si passa il bitmap da disegnare e le coordinate x e y
	
	@Override
	public boolean onTouchEvent (MotionEvent event)	//Si sovrascrive e implementa il metodo "onTouch" per intercettare la gestione degli 
	{												//degli eventi di touch sullo schermo,altrimenti non si ha il controllo
		int eventaction = event.getAction();		//Dato che l'oggetto "event" descrive il touch anche in termini di coordinate degli assi allora è possibile
		int tx = (int) event.getX();				//estrarre tramite i metodi getter la coordinata x e y del punto in cui è avvenuto il touch
		int ty = (int) event.getY();  
		switch (eventaction)
		{
			case MotionEvent.ACTION_DOWN: 												//Controlla se l'azione coincide con ACTION_DOWN, cioè con la pressione sul bitmap
				if (tx>x && tx> x+img.getWidth() & ty>y & ty> y + img.getHeight());		//Se il touch di coordinate tx e ty, è stato fa tto sul bitmap, allora si setta a true 
				{																		//il flag "dragOn" e si visualizza un Toast che indica che il bitmap è stato cliccato 	
					dragOn=true;
					Toast.makeText(getContext(), "L'Immagine è stata cliccata ", Toast.LENGTH_LONG).show();
				}
				break;
			
			case MotionEvent.ACTION_MOVE: //Controlla se l'azione è stata quella di "move" ovvero il touch persistente
				if(dragOn)				  //Se "dragOn" è true, ovvero l'immagine è stata già "toccata" allora si 
				{						  //aggiornano le coordinate che individuano la posizione del bitmap, con quelle
					x=tx;				  //touch, e tramite il metodo statico "invalidate()" si richiama onDraw e quindi 
					y=ty;				  //si ridisegna il bitmap con le nuove coordinate (l'effetto è quello dello spostamento)
					invalidate();
				}
				break;
			
			case MotionEvent.ACTION_UP:	 //Controlla se l'azione è stata quella di rilasciare il "touch"
				dragOn=false;			 //Si imposta a false il flag 
				Toast.makeText(getContext(), "L'Immagine è stata rilasciata ", Toast.LENGTH_LONG).show(); //Si visualizza un Toast che sottolinea il rilascio dell'immagine
				break;
		}
		return true;	//Uno degli eventi si è consumato e se ne può gestire un altro
	}
	
}
