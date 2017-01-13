package ie.gmit.sw.server;

import java.io.File;
import java.net.Socket;

public class ClientService extends ClientServiceable {
	private int clientID; 
	private String message;
	private String files;

	public ClientService(Socket s, int i){
		super(s);
		this.clientID = i;
	}
	public void run(){
		System.out.println("Accepted Client : ID - " + clientID + " : Address - " + getClientSocket().getInetAddress().getHostName());
		
		getStreams();
		
		//File curDir = new File(".");
		//code testing
		File dir = new File("./files-available-for-download");
        getAllFiles(dir);
        System.out.println(files);
		
		do{
			message = receiveMessage();
			sendMessage("Your answer was " + message);
			
		}while(!message.equals("4"));
		
		System.out.println("Ending Client : ID - " + clientID + " : Address - " + getClientSocket().getInetAddress().getHostName());
		
		disconnect();
		
	}
	public void getAllFiles(File dir) {
		files = "File Listing:\n";
        File[] filesList = dir.listFiles();
        for(File f : filesList){
            if(f.isFile()&&f.getName().charAt(0)!='.'&&(f.getName().equals("client-config.xml")==false)){
                files += f.getName();
            	}//if
            }//for
        }//method
	}