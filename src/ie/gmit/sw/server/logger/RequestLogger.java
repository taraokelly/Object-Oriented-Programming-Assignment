package ie.gmit.sw.server.logger;
/* Tara O'Kelly - G00322214
 * Object Oriented Programming, Third Year, Software Development, GMIT.
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.concurrent.BlockingQueue;

public class RequestLogger implements Runnable { //IS-A Runnable 
	// - Runnable is an interface that allows the RequesterLogger to be put into a Thread
	// and requires that requires the implementation of a run() method (inherits abstract method run())
	
	// Variables
	private BlockingQueue<Request> queue;
	private Writer logger;
	private volatile boolean running = true;
	// Constructor
	public RequestLogger(BlockingQueue<Request> queue) throws IOException {
		super();
		this.queue = queue;
		this.logger = new FileWriter(new File("log.txt"), true);
	}
	// Run method
	public void run() {
		try {
			while (running) {
				try {
					Request request = queue.take();// blocking call
					if (request instanceof PoisonRequest) {// if PoisonRequest
						running = false;	// terminate loop
					} else {
						logger.write(request.toString() + "\n"); //write to file
						logger.flush();
					}
				} catch (InterruptedException e) {
					System.err.println("Error: " + e.getMessage());
				}
			}
			logger.close();//close logger
		} catch (IOException e) {
			System.err.println("Error: " + e.getMessage());
		}
	}
}