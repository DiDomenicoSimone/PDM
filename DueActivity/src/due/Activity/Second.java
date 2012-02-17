package due.Activity;
import android.os.Bundle;
import android.app.Activity;

public class Second extends Activity
{
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second); //In questa seconda activity, si associa un layout radice differente,ovvero quello con
        								 //rifermento all'id R.layout.second, che si trova nella cartella res/layout
	}			
}