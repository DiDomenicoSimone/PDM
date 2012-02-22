package animation.p;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;

public class AnimationActivity extends Activity {
    /** Called when the activity is first created. */
	TranslateAnimation animation;
	Button btn;
	LinearLayout ll;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ll=new LinearLayout(this);
        ll.setLayoutParams(new LayoutParams(300,300));
        btn = new Button(this);
        btn.setHeight(80);
        btn.setWidth(150);
        btn.setText("Animazione");
        ll.addView(btn);
        btn.setOnClickListener(new OnClickListener()
        {
        @Override
        public void onClick(View v) 
        	{ 
    			int screenWidth = getWindow().getWindowManager().getDefaultDisplay().getWidth();
    			int x=screenWidth;
    			x-=btn.getWidth()+btn.getLeft();
    			animation= new TranslateAnimation(Animation.ABSOLUTE, 0, Animation.ABSOLUTE, x, Animation.ABSOLUTE,  0, Animation.ABSOLUTE, 0);
    			animation.setDuration(5000);
    			animation.setFillAfter(true);
    			btn.startAnimation(animation);
        	}    
        });
        setContentView(ll);
        
	}
}