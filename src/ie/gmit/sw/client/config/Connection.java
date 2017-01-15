package ie.gmit.sw.client.config;
/* Tara O'Kelly - G00322214
 * Object Oriented Programming, Third Year, Software Development, GMIT.
 */
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;

public class Connection implements Connectable{ //IS-A Connectable
	// Variables
	private HashMap<String, String> clientConfig =new HashMap<String, String>();
	private XMLParser p = new XMLParser(); //HAS-A XMLParser - Full Composition
	private Socket requestSocket;
	private ObjectOutputStream out;
 	private ObjectInputStream in;
	private String msg;
	// Constructor
	public Connection(){}
	// Methods
	// Check connection.
	public Boolean isConnected(){
		try{
			if(requestSocket.isConnected())
				return true;
		}catch(Exception e){}
		return false;
	}
	// Creating a socket to connect to the server.
	public void connect(){
		p.parseFile(new File("client-config.xml"));
		clientConfig = p.getClientConfig();
		try {
			requestSocket = new Socket(clientConfig.get("server-host"), Integer.parseInt(clientConfig.get("server-port")));
			in = new ObjectInputStream(requestSocket.getInputStream());
			out = new ObjectOutputStream(requestSocket.getOutputStream());
			System.out.println("Connected to: " + clientConfig.get("server-host") + ", on port: "+ clientConfig.get("server-port")+".\n");
		} catch (UnknownHostException e) {
			System.err.println("Error: Unknown Host.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// Communicating with the server.
	public void sendMessage(String msg){
		try{
			out.writeObject(msg);
			out.flush();
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
	public String receiveMessage(){
		try {
			msg = (String)in.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return msg;
	}
	public void receiveFile(){
		try {
			String fileName = (String) in.readObject();
			out.writeObject("recieved");
			long fileLength = (Long) in.readObject();
			byte[] bytes = new byte[(int) fileLength];
			InputStream is = requestSocket.getInputStream();
			FileOutputStream fos = new FileOutputStream(clientConfig.get("download-dir") +fileName);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			int bytesRead = is.read(bytes, 0, bytes.length);
			bos.write(bytes, 0, bytesRead);
			bos.flush();
			bos.close();
			System.out.println("Download Complete!\n");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// Closing connection.
	public void disconnect(){
		try {
			in.close();
			out.close();
			requestSocket.close();
			p.clearClientConfig();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}