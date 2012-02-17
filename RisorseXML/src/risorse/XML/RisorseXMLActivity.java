package risorse.XML;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.util.Log;

public class RisorseXMLActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        XmlResourceParser parser = getResources().getXml(R.xml.compilation);	// In questo caso il parser è utilizzato per analizzare uno stream continuo di input dal file
        																		// compilation.xml passato come parametro nel metodo getResources().getXml, che prende lo 
        																		// stream di dati e lo memorizza nella variabile parser di tipo XmlResourceParser.
        try {
        	int eventType = parser.getEventType(); 						//Prende il valore dell'evento in "parser" e lo memorizza nella variabile "eventType"
        	while (eventType != XmlResourceParser.END_DOCUMENT) {		//Svolge il ciclio finchè non si arriva alla "fine del testo"
        		if (eventType == XmlResourceParser.START_TAG) {			//Se l'eventType si trova nello START_TAG allora
    			String tagName = parser.getName();						//memorizza nella stringa "tagName" il nome del tag
        			if ("brano".equals(tagName)) {						//Se il "tagName" corrisponde a "brano" allora
        				String id = parser.getAttributeValue(0);		//Copia il valore dell'attributo nella variabile "id" di tipo String
        				String str = "Brano: " + id;				
        				Log.d("XML PARSER", str);						//Visualizza nel logcat la stringa "str" (numero del brano) contrassegnata dal tag "XML PARSER"
        			}
        		}
        		else if (eventType == XmlResourceParser.TEXT) {			//Se l'"eventType" corrisponde al "Text"
        			String elementValue = parser.getText();				//allora copia nella string "elemenValue" il testo contenuto in "parser"
        			String str = elementValue;
    				Log.d("XML PARSER", str);							//Visualizza nel logcat la stinga "str" (nome del brano)
        		}
        		eventType = parser.next();								//Incrementa al prossimo evento
        	}
        }
        catch (XmlPullParserException e) {
        	e.printStackTrace();
        }
        catch (IOException e) {
        	e.printStackTrace();
        }
    }
}