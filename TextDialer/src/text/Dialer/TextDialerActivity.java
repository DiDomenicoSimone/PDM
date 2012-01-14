package text.Dialer;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class TextDialerActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button goButton =(Button) findViewById (R.id.button1);
        goButton.setOnClickListener (new OnClickListener()
        {
        	public void onClick(View v)
        	{
        		EditText etext = (EditText) findViewById (R.id.editText1);
        		String telString = etext.getText().toString();
        		String telUriString = "tel:" + telString;
        		Uri telURI = Uri.parse(telUriString);
        		Intent intent = new Intent(Intent.ACTION_DIAL);
        		intent.setData(telURI);
        		startActivity(intent);
        	}
        });
    
        EditText etext = (EditText) findViewById (R.id.editText1);
        etext.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
        Button callButton =(Button) findViewById (R.id.button2);
        callButton.setOnClickListener (new OnClickListener()
        {
        	public void onClick(View v)
        	{
        		EditText etext = (EditText) findViewById (R.id.editText1);
        		String telString = etext.getText().toString();
        		String telUriString = "tel:" + telString;
        		Uri telURI = Uri.parse(telUriString);
        		Intent intent = new Intent(Intent.ACTION_CALL);
        		intent.setData(telURI);
        		startActivity(intent);
        	}
        });

    }
}