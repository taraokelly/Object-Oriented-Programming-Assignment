package ie.gmit.sw.client;

import java.util.Scanner;

import ie.gmit.sw.client.config.Connection;

public class Runner {
	public static void main(String args[])
	{	
		//following code is for testing purposes
		Scanner in = new Scanner(System.in);
		String request = null, response;
		Connection c = new Connection();
		c.connect();
		
		while(request.equals("4")==false){
			System.out.println("Enter a message to send:");
			request = in.nextLine();
			c.sendMessage(request);
			response = c.receiveMessage();
			System.out.println(response);
		}
		in.close();
	}
}
