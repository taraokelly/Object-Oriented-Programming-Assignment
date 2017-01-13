package ie.gmit.sw.server;

import java.net.ServerSocket;
import java.net.Socket;

public class Runner {
	 public static void main(String[] args) throws Exception {
		    ServerSocket SS = new ServerSocket(7777);
		    int id = 0;
		    while (true) {
		        Socket clientSocket = SS.accept();
		        ClientService cliThread = new ClientService(clientSocket, id++);
		        cliThread.start();
		        
		      }//while
	 }
}
