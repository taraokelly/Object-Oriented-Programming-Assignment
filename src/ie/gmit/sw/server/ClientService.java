package ie.gmit.sw.server;
/* Tara O'Kelly - G00322214
 * Object Oriented Programming, Third Year, Software Development, GMIT.
 */
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

import ie.gmit.sw.server.logger.Request;

public class ClientService extends ClientServiceable{// IS-A ClientServiceable.
	private int clientID; 
	private String message;
	private FileListing fl;// HAS-A FileListing - Full Composition.
	private BlockingQueue<Request> loggingQueue;//HAS-A (well has 0..* technically) Request - Aggregation.

	public ClientService(Socket s, int i, String path, BlockingQueue<Request> loggingQueue){
		super(s);
		this.clientID = i;
		this.fl = new FileListing(path);
		this.loggingQueue = loggingQueue;
	}
	public void run(){
		System.out.println("Accepted Client : ID - " + clientID + " : Address - " + getClientSocket().getInetAddress().getHostName());
		/* Already connected, just need to acquire streams.
		 * loop - communicate with Client until prompted by user to quit.
		 */
		getStreams();// Inherit behavior from ClientServiceable - Delegation.
		do{
			message = receiveMessage();
			execute(message); // Calls function to execute command from client.
			
		}while(!message.equals("4"));
		System.out.println("Ending Client : ID - " + clientID + " : Address - " + getClientSocket().getInetAddress().getHostName());
		disconnect();
		
	}
	public void execute(String request){
		switch(message){
		case "2":
			/* try - log request. 
			 * Then, send a numbered file listing.
			 */
			try { 
				loggingQueue.put(new Request("File Listing", getClientSocket().getInetAddress().getHostName(), "INFO"));
			} catch (InterruptedException e) {
				System.err.println("Error: "+ e.getMessage());
			}
			sendMessage(fl.getAllFiles());
			break;
		case "3":
			/* Get number of file form Client.
			 * if - valid number - log request & send file
			 * else - send error
			 */ 
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