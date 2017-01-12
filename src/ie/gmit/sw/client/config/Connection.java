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
	
	public Connection(){}
	
	//creating a socket to connect to the server
	public void connect(){
		p.parseFile(new File("client-config.xml"));
		this.clientConfig = p.getClientConfig();
		try {
			this.requestSocket = new Socket(this.clientConfig.get("server-host"), Integer.parseInt(this.clientConfig.get("server-port")));
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// Closing connection
	public void disconnect(){
		try {
			this.requestSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args){
		Connection c = new Connection();
		c.connect();
	}
}
