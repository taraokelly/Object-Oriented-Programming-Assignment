package ie.gmit.sw.client;

import java.util.Scanner;

import ie.gmit.sw.client.config.Connection;

public class UI {
	Scanner in = new Scanner(System.in);
	String option = "", response;
	Connection c = new Connection();
	
	UI(){}
	
	public void run(){	
		while(!option.equals("4")){
			System.out.println("java -cp .:./oop.jar ie.gmit.sw.Client");
			System.out.println("Enter:\n1 - To Connect to Server\n2 - To Print File Listing\n3 - To Download File\n4 - To Quit");
			option = in.nextLine();
			execute(option);
		}
	}
	public void execute(String request){
		if(request.equals("1")&&(c.isConnected()==false)){
			c.connect();
		}
		else if(request.equals("2")&&(c.isConnected()==true)){
			c.sendMessage(request);
			response = c.receiveMessage();
			System.out.println(response);
		}
		else if(request.equals("3")&&c.isConnected()==true){
			c.sendMessage(request);
			response = c.receiveMessage();
			System.out.println(response);
			System.out.println("Let's pretend the file downloaded for now");
			//c.sendMessage(request);
			//receive and download file
		}
		else if (request.equals("4")&&c.isConnected()==true){
			c.sendMessage(request);
			response = c.receiveMessage();
			System.out.println(response);
			c.disconnect();
		}	
		else if(request.equals("1")&&(c.isConnected()==true)){
			System.out.println("Looks like you're already connected. Please select another option.\n");
		}
		else if ((request.equals("2")|request.equals("3"))&&c.isConnected()==false){
			System.out.println("Not connected to the server. Connect to the server to undergo this action.\n");
		}
	}
}