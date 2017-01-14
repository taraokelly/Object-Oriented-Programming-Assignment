package ie.gmit.sw.server.logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.concurrent.BlockingQueue;

public class RequestLogger implements Runnable {
	private final String FILENAME = "log.txt";
	private BlockingQueue<Request> queue;
	private Writer logger;
	private volatile boolean running = true;
	
	public RequestLogger(BlockingQueue<Request> queue) throws IOException {
		super();
		this.queue = queue;
		this.logger = new FileWriter(new File(FILENAME), true);
	}
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