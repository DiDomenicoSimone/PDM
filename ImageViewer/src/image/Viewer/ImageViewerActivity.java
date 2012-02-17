package image.Viewer;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

public class ImageViewerActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ImageView image = (ImageView) findViewById(R.id.imageView1); 			//Si prende il riferimento all'imageview creata nel layout
        image.setImageURI((Uri) getIntent().getExtras().get(Intent.EXTRA_STREAM)); //Con questa istruzione, quello che si fa è settare come imageview nel layout, l'eventuale imageview
        																		   //trasportata da un intent lanciato dal sistema,ovvero si fa in modo che questa applicazione sia in grado
        																		   //di gestire la visualizzazione delle immagine, cioè di ricevere intent con l'azione "SEND" che va registrata
        																		   //nel file manifest,nel tag intent-filters
    }
}