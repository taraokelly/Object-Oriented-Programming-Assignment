package ie.gmit.sw.server;
/* Tara O'Kelly - G00322214
 * Object Oriented Programming, Third Year, Software Development, GMIT.
 */
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

import ie.gmit.sw.server.logger.PoisonRequest;
import ie.gmit.sw.server.logger.Request;

public class ClientListener extends Thread{ //IS-A Thread.
	 private ServerSocket SS;
	 private BlockingQueue<Request> loggingQueue;//HAS-A Request - Aggregation - defined at class level, however ClientListener shares access with other classes.
	 private String path;
	 private volatile ArrayList<ClientService> threads = new ArrayList<ClientService>();//HAS-A ClientService - Full Composition
	 private volatile boolean running = true;// Variable will never be cached
	 
	public ClientListener(ServerSocket SS, BlockingQueue<Request> loggingQueue, String path){	
		this.SS = SS;
		this.path = path;
		this.loggingQueue = loggingQueue; 
	}
		public void run() {
			Thread serverList = new Thread(new ServerListener()); 
			serverList.start();
			int id = 0; //client ID
			/* loop - always looking for connections
		     * that accepts the socket connection and starts a new thread.
		     */
			 while (running) {
			 try{
					Socket clientSocket = SS.accept();
			        ClientService cThread = new ClientService(clientSocket, id++, path, loggingQueue);
			        cThread.run();    
				} catch (IOException e) { 
					System.err.println("Error: Communication Fault:" + e.getMessage());
				}
			}
			 /* try - wait for ClientService threads to finish.
			  * Then close ServerSocket.
			  */
			try {
				for (ClientService t: threads){
					t.join();
				}
				loggingQueue.put(new PoisonRequest("", "", ""));//"Poison" blocking queue.
				//Now HAS-A PoisinRequest - Dependancy
				SS.close();// Close socket when loop terminates.
			} catch (IOException e) {
				System.err.println("Error: Closing Socket. " + e.getMessage());
			} catch (InterruptedException e) {
				System.err.println("Error: " + e.getMessage());
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