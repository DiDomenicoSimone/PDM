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
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class ChatActivity2Activity extends Activity {
	EditText et;
	ListView lv;
	Connection connection;
	ArrayAdapter<String> adapter; // Istanzio un ArrayAdapter che servirà a "passare" alla ListView i messaggi
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        et = (EditText) findViewById(R.id.editText1);
        lv = (ListView) findViewById(R.id.listView1);
        Button sendButton = (Button) findViewById(R.id.button1);
        sendButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				adapter.add("ME: " +et.getText().toString()); // Aggiungo l'elemento di tipo String contenuto nell'edittext
				Message msg = new Message();				
				msg.setTo("loreti@ppl.eln.uniroma2.it");			
				msg.setBody(et.getText().toString());
				connection.sendPacket(msg);
				lv.setSelection(adapter.getCount()-1);
			}
		});
        try{

        	ConnectionConfiguration config = new ConnectionConfiguration("ppl.eln.uniroma2.it",5222); // Viene creata e inizilizzata una nuova connessione al server con il socket e il relativo indirizzo
        	config.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled);  // Viene impostato il parametro di sicurezza tramite il metodo "setSecurityMode" gestito dalla classe ConnectionConfiguration
        	connection = new XMPPConnection(config);
        	connection.connect();
        	connection.login("didomenico","didomenico"); //Username e Password per accesso al server

        }catch (XMPPException e) {
        	e.printStackTrace();
        }

        connection.addPacketListener(new PacketListener() {     //Si aggiunge un listener di pacchetti, per poter ricvevere e processare i pacchetti in ricezione 
			
			public void processPacket(Packet pkt) {
				// TODO Auto-generated method stub
				Message msg = (Message) pkt;   //Si memorizza il contenuto del pacchetto in un oggetto di classe "Message"
				String from = msg.getFrom();
				String body = msg.getBody();
				adapter.add(from+" : " +body);	//Si visualizza nella textView il messaggio ricevuto
				lv.setSelection(adapter.getCount()-1);
			}
		
        }, new MessageTypeFilter(Message.Type.normal)); 
        
        adapter = new ArrayAdapter<String> (ChatActivity2Activity.this,R.layout.row,R.id.rowText); //Ho creato un layput "row" da passare all'Adapter 
        
        lv.setAdapter(adapter); //Assegno l'adapter alla ListView, ovvero all'AdapterView
    }
}