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
		String iltestoricevuto = getIntent().getExtras().getString(
				"iltestonelbox");
		label.setText(iltestoricevuto);
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
		Log.d(TAG, "onRestart");
	}
	
	protected void onReStart() {
		super.onRestart();
		Log.d(TAG,"onReStart");
	}
	
}