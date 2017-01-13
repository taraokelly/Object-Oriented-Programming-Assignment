package ie.gmit.sw.server;

import java.net.Socket;

public class ClientService extends ClientServiceable {
	private int clientID = -1; 
	private String message;

	public ClientService(Socket s, int i){
		super(s);
	}
	public void run(){
		System.out.println("Accepted Client : ID - " + clientID + " : Address - "
		        + getClientSocket().getInetAddress().getHostName());
		
		getStreams();
		
		do{
			message = receiveMessage();
			sendMessage("Your answer was " + message);
			
		}while(!message.equals("4"));
		
		System.out.println("Ending Client : ID - " + clientID + " : Address - "
		        + getClientSocket().getInetAddress().getHostName());
		
		disconnect();
		
	}
}