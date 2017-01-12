package ie.gmit.sw.client.config;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;

public class Connection {
	
	private HashMap<String, String> clientConfig =new HashMap<String, String>();
	private XMLParser p = new XMLParser();
	private Socket requestSocket;
	private ObjectOutputStream out;
 	private ObjectInputStream in;
	private String msg;
	
	public Connection(){}
	
	public Boolean isConnected(){
		return requestSocket.isConnected();
	}
	// Creating a socket to connect to the server
	public void connect(){
		p.parseFile(new File("client-config.xml"));
		clientConfig = p.getClientConfig();
		try {
			requestSocket = new Socket(clientConfig.get("server-host"), Integer.parseInt(clientConfig.get("server-port")));
		} catch (UnknownHostException e) {
			System.err.println("Error: Unknown Host.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Socket Connected to Server");
	}
	// Communicating with the server
	public void sendMessage(String msg){
		try{
			out = new ObjectOutputStream(requestSocket.getOutputStream());
			out.writeObject(msg);
			out.flush();
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
	public String receiveMessage(){
		try {
			in = new ObjectInputStream(requestSocket.getInputStream());
			msg = (String)in.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return msg;
	}
	// Closing connection
	public void disconnect(){
		try {
			in.close();
			out.close();
			requestSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}