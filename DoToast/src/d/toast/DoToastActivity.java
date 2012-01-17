package d.toast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class DoToastActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
	EditText inserisci_testo ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout ll = new LinearLayout (DoToastActivity.this);
        ll.setOrientation (LinearLayout.VERTICAL);
        ll.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
        Button btn = new Button (this);
        btn.setText("Saluta");
        btn.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
        inserisci_testo = new EditText(this);
        inserisci_testo.setText(null);
        inserisci_testo.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
        ll.addView (inserisci_testo);
        
        ll.addView(btn);
        setContentView(ll);
        btn.setOnClickListener (this);
    }
        	public void onClick(View v)
        	{
				String s = inserisci_testo.getText().toString();
        		Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
        	}
        
     
        
   }

