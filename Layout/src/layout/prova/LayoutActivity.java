package layout.prova;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LayoutActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button startButton = (Button) findViewById (R.id.button);
        startButton.setOnClickListener (new OnClickListener()
        {
        	public void onClick(View v)
        	{
        		startActivity(new Intent(LayoutActivity.this,LayoutActivity1.class));
        	}
        });
    }
}