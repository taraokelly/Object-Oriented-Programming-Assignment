package ie.gmit.sw.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientListener extends Thread{ 
	 private ServerSocket SS;
	 private String path;
	 private volatile ArrayList<ClientService> threads = new ArrayList<ClientService>();
	 private volatile boolean running = true, threadsFin = false;
	 
	public ClientListener(ServerSocket SS, String path){	
		this.SS = SS;
		this.path = path;
	}
		public void run() {
			Thread serverList = new Thread(new ServerListener()); 
			serverList.start();
			int id = 0; //client ID
			/* loop - always looking for connections
		     * that accepts the socket connection and starts a new thread
		     */
			 while (running) {
			 try{
					Socket clientSocket = SS.accept();
			        ClientService cThread = new ClientService(clientSocket, id++, path);
			        cThread.run();    
				} catch (IOException e) { 
					System.err.println("Error: Communication Fault:" + e.getMessage());
				}
			}
			while(threadsFin==false){
				 threadsFin=true;
				 for (ClientService t: threads){
					  if((t).isAlive())
						  threadsFin=false;
				 }//for
			 }//while	
			try {
				SS.close();//close socket when loop terminates
			} catch (IOException e) {
				System.err.println("Error: Closing Socket. " + e.getMessage());
			}
			
		}
		private class ServerListener implements Runnable{ //Inner class ServerListener Listening for EXIT command
			Scanner in = new Scanner(System.in);
			String message = "";
			 
				public void run() {
					while(message.equalsIgnoreCase("EXIT")==false){
						message = in.nextLine();
						}//while
					System.out.println("Terminating...");
					running = false;//stop Listener loop from accepting socket requests when current loop is finished
					in.close();//close scanner
					}//run
					
				}//ServerListener
}