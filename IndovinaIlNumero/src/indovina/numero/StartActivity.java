package indovina.numero;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class StartActivity extends Activity {
    
	EditText et1 ; 
	EditText et2 ; 
	@Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
        et1 = (EditText) findViewById(R.id.editText1);	//Si prende il riferimento all'edit text relativa al nome proprio
        et2 = (EditText) findViewById(R.id.editText2);	//Si prende il riferiment all'edit text relativa al nome avversario
        Button gioca = (Button) findViewById(R.id.button1);	//Si prende il riferimento al bottone
        gioca.setOnClickListener(new OnClickListener() {	//Si aggancia un click listener al bottone
			
			@Override
			public void onClick(View v) 
			{
				Intent intent = new Intent(StartActivity.this,Main.class);
				intent.putExtra("nome proprio",et1.getText().toString());
				intent.putExtra("nome avversario",et2.getText().toString());
				startActivity(intent);		//Si avvia la nuova activity, passano tramite intent il nome proprio e il nome dell'avversario
			}
		});
        
    }
}