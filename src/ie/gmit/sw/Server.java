package ie.gmit.sw;

import java.io.File;
import java.net.ServerSocket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import ie.gmit.sw.server.ClientListener;
import ie.gmit.sw.server.logger.Request;
import ie.gmit.sw.server.logger.RequestLogger;

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
					    BlockingQueue<Request> loggingQueue  = new ArrayBlockingQueue<Request>(100);
					    Thread server = new Thread(new ClientListener(SS, loggingQueue, path)); //new Client Listener to listen for requests
						server.setPriority(Thread.MAX_PRIORITY); //Ask the Thread Scheduler to run this thread as a priority
						server.start();
						
						Thread logger = new Thread(new RequestLogger(loggingQueue));
						logger.start();
					    
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