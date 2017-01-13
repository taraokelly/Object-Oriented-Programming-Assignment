package ie.gmit.sw.server;

import java.io.File;
import java.net.Socket;

public class ClientService extends ClientServiceable {
	private int clientID; 
	private String message;

	public ClientService(Socket s, int i){
		super(s);
		this.clientID = i;
	}
	public void run(){
		System.out.println("Accepted Client : ID - " + clientID + " : Address - " + getClientSocket().getInetAddress().getHostName());
		
		getStreams();
		
		do{
			message = receiveMessage();
			sendMessage("Your answer was " + message);
			
		}while(!message.equals("4"));
		
		System.out.println("Ending Client : ID - " + clientID + " : Address - " + getClientSocket().getInetAddress().getHostName());
		
		disconnect();
		
	}
	public void getAllFiles(File curDir) {

        File[] filesList = curDir.listFiles();
        for(File f : filesList){
            if(f.isFile()&&f.getName().charAt(0)!='.'){
                System.out.println(f.getName());
            }
        }
	}
}