package ie.gmit.sw.server;

import java.net.Socket;
import java.util.concurrent.BlockingQueue;

import ie.gmit.sw.server.logger.Request;

public class ClientService extends ClientServiceable{
	private int clientID; 
	private String message;
	private FileListing fl;
	private BlockingQueue<Request> loggingQueue;

	public ClientService(Socket s, int i, String path, BlockingQueue<Request> loggingQueue){
		super(s);
		this.clientID = i;
		this.fl = new FileListing(path);
		this.loggingQueue = loggingQueue;
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
			try {
				loggingQueue.put(new Request("File Listing", getClientSocket().getInetAddress().getHostName(), "INFO"));
			} catch (InterruptedException e) {
				System.err.println("Error: "+ e.getMessage());
			}
			sendMessage(fl.getAllFiles());
			break;
		case "3":
			sendMessage("Enter the number of the file you wish to upload:");
			message = receiveMessage();
			if(fl.getValue(Integer.parseInt(message))!=null){
				try {
					loggingQueue.put(new Request(fl.getValue(Integer.parseInt(message)), getClientSocket().getInetAddress().getHostName(), "INFO"));
				} catch (InterruptedException e) {
					System.err.println("Error: "+ e.getMessage());
				}
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