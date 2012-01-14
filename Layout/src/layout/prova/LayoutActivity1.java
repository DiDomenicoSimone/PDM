package layout.prova;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;



public class LayoutActivity1 extends Activity
{
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout ll = new LinearLayout (this);
        ll.setOrientation(LinearLayout.VERTICAL);
        
        TextView mio_testo = new TextView (this);
        EditText inserisci_testo = new EditText(this);
        
        mio_testo.setText("Activity con Layout creato direttamente nel codice!");
        inserisci_testo.setText("Era solo una prova! :) ");
        
        int lheight = LinearLayout.LayoutParams.FILL_PARENT;
        int lwidht = LinearLayout.LayoutParams.WRAP_CONTENT;
        
        ll.addView (mio_testo, new LayoutParams(lheight,lwidht));
        ll.addView (inserisci_testo, new LayoutParams(lheight,lwidht));
        setContentView (ll);
      }
	
}