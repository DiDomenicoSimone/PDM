package chat.activity;

import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.MessageTypeFilter;
import org.jivesoftware.smack.packet.Message; 
import org.jivesoftware.smack.packet.Packet;  

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod; 
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText; 
import android.widget.TextView;

public class ChatActivity extends Activity {
    /** Called when the activity is first created. */
	EditText et ;
	TextView tv ;
	Connection connection;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        et = (EditText) findViewById(R.id.editText1);
        tv = (TextView) findViewById(R.id.textview);
        tv.setMovementMethod (new ScrollingMovementMethod()); //Abilita lo scroll del layout nel caso che le textview occupino tutto lo schermo 
        try 
        {
        	ConnectionConfiguration config = new ConnectionConfiguration ("ppl.eln.uniroma2.it",5222); // Viene creata e inizilizzata una nuova connessione al server con il socket e il relativo indirizzo
        	config.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled);  // Viene impostato il parametro di sicurezza tramite il metodo "setSecurityMode" gestito dalla classe ConnectionConfiguration
        	connection = new XMPPConnection(config);
        	connection.connect();
        	connection.login("didomenico","didomenico"); //Username e Password per accesso al server
        }
        catch(XMPPException e)
        {
        	e.printStackTrace();
        }
        connection.addPacketListener(new PacketListener()  //Si aggiunge un listener di pacchetti, per poter ricvevere e processare i pacchetti in ricezione
        {
        	@Override
        	public void processPacket (Packet pkt)
        	{
        		Message msg = (Message) pkt; //Si memorizza il contenuto del pacchetto in un oggetto di classe "Message"
        		String from = msg.getFrom();
        		String body = msg.getBody();
        		tv.append(from+" : "+body+"\n"); //Si visualizza nella textView il messaggio ricevuto
        	}
        }, new MessageTypeFilter(Message.Type.normal));
        Button bottone = (Button) findViewById(R.id.button1);
        bottone.setOnClickListener (new OnClickListener ()
        {
        	public void onClick(View v)
        	{
        		tv.append("ME: "+ et.getText().toString()+"\n"); //Si aggiunge alla TextView il testo digitato dall'utente nell'EditText
        		Message msg = new Message();
        	    msg.setTo("loreti@ppl.eln.uniroma2.it"); // Il metodo "setTo" imposta il destinatario che riceverà il messaggio 	
        	    msg.setBody(et.getText().toString()); // Il metodo "setBody" memorizza nell'oggetto "msg" il testo scritto nell'EditText
        	    connection.sendPacket(msg); // Viene inviato il messaggio tramite il metodo "sendPacket" mediante l'uso della variabile connection
            }
        });
    }
}
