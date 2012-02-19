package drag.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class DragViewActivity extends Activity {
	

	private View selected_item = null;
	private int offset_x = 0;
	private int offset_y = 0;
	

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
 
		ImageView iv = (ImageView) findViewById(R.id.imageView1); //Si prende il riferimento all'immagine con id "imageView1"
		iv.setOnTouchListener(new View.OnTouchListener() {		  //Si aggiunge all'imageview un listener per intercettare l'evento di touch

			@Override
			public boolean onTouch(View v, MotionEvent event) //Si sovrascrive il metodo "onTouch" per gestire tale evento sull'imageview
			{
				if(event.getActionMasked()==MotionEvent.ACTION_DOWN)	//Se l'azione sull'immagine è stata quella del tocco, cioè "ACTION_DOWN"
				{														//allora vengono memorizzate le coordinate del touch e viene selezionata la view
					offset_x = (int) event.getX();
					offset_y = (int) event.getY();
					selected_item = v;
				}
				return false;
			}
		});
		
		RelativeLayout vg = (RelativeLayout) findViewById(R.id.relativeLayout1);	//Si prende il riferimento al relative layout creato in main.xml
		vg.setOnTouchListener(new View.OnTouchListener() {					//Si aggiunge al layout un listener per intercettare e poi gestire l'evento di touch

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getActionMasked()==MotionEvent.ACTION_MOVE)	//Controlla se l'evento associato al touch, è "ACTION_MOVE" cioè un touch persistente
				{
					if(selected_item==null) return false;      //Se non è selezionata nessuna view alla il return false, indica che l'evento non è stato consumato
					int x = (int) event.getX() - offset_x;		//Si calcola la coordinata x relativa allo spostamento
					int y = (int) event.getY() - offset_y;		//Si calcola la coordinata y relativa alla spostemanto

					//Con questi due strutture condizionali si evita che le immagini non escano dalla schermata
					
					int w = getWindowManager().getDefaultDisplay().getWidth() -150;
					int h = getWindowManager().getDefaultDisplay().getHeight()-150;
					if (x > w)
						x = w;
					if (y > h)
						y = h;
					RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
							new ViewGroup.MarginLayoutParams(
									RelativeLayout.LayoutParams.WRAP_CONTENT,
									RelativeLayout.LayoutParams.WRAP_CONTENT));
					lp.setMargins(x, y, 0, 0); 		//Margini del relative layout in funzione delle coordinate x e y

					selected_item.setLayoutParams(lp); //Si impostano i margini del layout alla view che è stata selezionata
				}
				
				if(event.getActionMasked()==MotionEvent.ACTION_UP)
				{
					selected_item=null;	//Se il touch viene rilasciato, allora la view viene deselezionata
				}
				return true; //Indica che gli eventi di ACTION_UP e ACTION_DOWN sono stati consumati
			}
		});
		
		ImageView iv2 = (ImageView) findViewById(R.id.imageView2);  //Si prende il riferimento all'immagine con id "imageView2"
		iv2.setOnTouchListener(new OnTouchListener() {				//Si aggiunge all'imageview un listener per intercettare l'evento di touch
			
			public boolean onTouch(View v, MotionEvent event)		//Si sovrascrive il metodo "onTouch" per gestire tale evento sull'imageview 
			{			
				if(event.getActionMasked()==MotionEvent.ACTION_DOWN)	//Se l'azione sull'immagine è stata quella del tocco, cioè "ACTION_DOWN"
				{														//allora vengono memorizzate le coordinate del touch e viene seleziona la view 
					offset_x = (int) event.getX();
					offset_y = (int) event.getY();
					selected_item = v;
				}
				return false;
			}
		});
    }
}