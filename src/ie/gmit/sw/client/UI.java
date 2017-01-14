package ie.gmit.sw.client;
/* Tara O'Kelly - G00322214
 * Object Oriented Programming, Third Year, Software Development, GMIT.
 */
import java.util.Scanner;

import ie.gmit.sw.client.config.Connection;

public class UI {
	// Variables
	Scanner in = new Scanner(System.in);
	String option = "", response;
	Connection c = new Connection();
	// NULL Constructor
	public UI(){}
	// Methods
	public void run(){	
		/* while loop - looping menu
		 * execute option entered by user
		 */
		while(!option.equals("4")){
			System.out.println("Enter:\n1 - To Connect to Server\n2 - To Print File Listing\n3 - To Download File\n4 - To Quit");
			option = in.nextLine();
			execute(option);
		}
	}
	public void execute(String request){
		if(request.equals("1")&&(c.isConnected()==false)){
			c.connect();// Connecting to server.
		}
		else if(request.equals("2")&&(c.isConnected()==true)){
			c.sendMessage(request);// Sending option.
			response = c.receiveMessage();// Receiving listing.
			System.out.println(response);
		}
		else if(request.equals("3")&&c.isConnected()==true){
			c.sendMessage(request);// Sending option.
			response = c.receiveMessage();// Receiving message prompting user to enter which file they want.
			System.out.println(response);
			request = in.nextLine();// Giving option.
			c.sendMessage(request);
			response = c.receiveMessage();// Receiving validity of option.
			System.out.println(response);
			if(response.equals("Sending file...")){// If option is valid.
				c.sendMessage("Ready for download.");
				c.receiveFile();
			}
		}
		else if (request.equals("4")&&c.isConnected()==true){
			c.sendMessage(request);
			response = c.receiveMessage();
			System.out.println(response);
			c.disconnect();// Close connections.
			in.close();
		}	
		else if(request.equals("1")&&(c.isConnected()==true)){// To stop users from connecting again unnecessarily, and without closing resources.
			System.out.println("Looks like you're already connected. Please select another option.\n");
		}
		else if ((request.equals("2")|request.equals("3"))&&c.isConnected()==false){// To stop users from querying when not connected to the server. 
			System.out.println("Not connected to the server. Connect to the server to undergo this action.\n");
		}
		else{
			System.out.println("Invalid Option.\n");
		}
	}
}