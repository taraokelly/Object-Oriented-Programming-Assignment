package ie.gmit.sw;

import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;

import ie.gmit.sw.server.ClientService;

public class Server{
	 public static void main(String[] args){
		 if (args.length == 2) {
				int port = 0;
				String path = args[1];
				File d = new File(path);
				if(d.exists()&&d.isDirectory()){			
					try{
						port = Integer.parseInt(args[0]);
					    ServerSocket SS = new ServerSocket(port);
					    int id = 0;
					    /* infinite loop - always looking for connections
					     * that accepts the socket connection and starts a new thread
					     */
					    while (true) {
					        Socket clientSocket = SS.accept();
					        ClientService cThread = new ClientService(clientSocket, id++, path);
					        cThread.start();    
					      }//while
					 } catch (NumberFormatException e) {
							System.err.println("Error: Invalid Arguements. The Port Number Must Be an Integer.");
					 }catch(Exception e){
						 System.err.println("Error: " + e.getMessage());
					 }
				}else{
					System.err.println("Error: Invalid Arguements. The Directory " + path + " does not exist.");
				}
		 }else {
				System.err.println("Error: Invalid Arguements.");
			}
	 }
}
