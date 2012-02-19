package drag.image;

import android.app.Activity;
import android.os.Bundle;

public class DragImageActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(new MyView (this));	//Viene settata la "MyView" come la view root per il layout di questa activity
    }
}
