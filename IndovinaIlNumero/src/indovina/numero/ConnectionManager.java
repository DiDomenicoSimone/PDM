package indovina.numero;

import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.MessageTypeFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;

import android.content.Context;
import android.util.Log;
 
public class ConnectionManager implements PacketListener 
{
	Context context;
	String nomeP;
	String nomeA;
	MessageReceiver mr;
	Connection connection;


	public ConnectionManager(String nomeProprio,String nomeAvversario,MessageReceiver msgrcv)
	{
		nomeP=nomeProprio;
		nomeA=nomeAvversario;
		mr=msgrcv;
		try 
        {
        	ConnectionConfiguration config = new ConnectionConfiguration ("ppl.eln.uniroma2.it",5222); // Viene creata e inizilizzata una nuova connessione al server con il socket e il relativo indirizzo
        	config.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled);  // Viene impostato il parametro di sicurezza tramite il metodo "setSecurityMode" gestito dalla classe ConnectionConfiguration
        	connection = new XMPPConnection(config);
        	connection.connect();
        	connection.login(nomeProprio,nomeProprio); //Username e Password per accesso al server
        	connection.addPacketListener(this, new MessageTypeFilter(Message.Type.normal));
			Log.d("TAG", "XMPP Connection Started");
		} 
		catch (XMPPException e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public void processPacket(Packet pkt) 
	{
		Message msg = (Message) pkt;
		Log.d("TAG", "MSG RECV from:" + msg.getFrom() + " BODY:" + msg.getBody());
		if (msg.getFrom().startsWith(nomeP)) 
		{
			Log.d("TAG", "MSG DISCARDED coming from " + msg.getFrom()	+ "with body " + msg.getBody() + " myuser:" + nomeP);
		} 
		else 
		{
			mr.receiveMessage(msg.getBody());
		}
	}


	public void send(String string)
	{
		Message msg = new Message();
		msg.setTo(nomeA);
		msg.setBody(string);
		Log.d("TAG", "MSG SENT to:" + msg.getTo() + " BODY:" + msg.getBody());
		connection.sendPacket(msg);
	}
	
	public void close()
	{
		connection.disconnect();
	}




	 

}


