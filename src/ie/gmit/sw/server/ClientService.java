package ie.gmit.sw.server;

import java.net.Socket;

public class ClientService extends ClientServiceable {
	private int clientID; 
	private String message;
	private FileListing fl;

	public ClientService(Socket s, int i, String path){
		super(s);
		this.clientID = i;
		this.fl = new FileListing(path);
	}
	public void run(){
		System.out.println("Accepted Client : ID - " + clientID + " : Address - " + getClientSocket().getInetAddress().getHostName());
		
		getStreams();
		
		do{
			message = receiveMessage();
			execute(message);
			
		}while(!message.equals("4"));
		
		System.out.println("Ending Client : ID - " + clientID + " : Address - " + getClientSocket().getInetAddress().getHostName());
		
		disconnect();
		
	}
	public void execute(String request){
		switch(message){
		case "2":
			sendMessage(fl.getAllFiles());
			break;
		case "3":
			sendMessage("Enter the number of the file you wish to upload:");
			message = receiveMessage();
			if(fl.getValue(Integer.parseInt(message))!=null){
				sendMessage("Sending file...");
				String response = receiveMessage();
				System.out.println(response);
				sendFile(fl.getValue(Integer.parseInt(message)));
			}else{
				sendMessage("Error: invalid option.");
			}
			break;
		case "4":
			sendMessage("Goodbye!");
			break;
		}
	}
}