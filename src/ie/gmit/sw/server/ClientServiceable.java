package ie.gmit.sw.server;
/* Tara O'Kelly - G00322214
 * Object Oriented Programming, Third Year, Software Development, GMIT.
 */
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
//This class contains the methods to communicate with a Client to offer the Server's services.
public abstract class ClientServiceable extends Thread{ //IS-A Thread
	// Variables
	private Socket clientSocket; 
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private BufferedInputStream bin;
	// Constructor
	public ClientServiceable(Socket s) {
		this.clientSocket = s;
	}
	// Implementation of run will be defined by the inheriting class.
	public abstract void run();
	public void getStreams(){ // Acquire necessary streams for communication.
		try {
			out = new ObjectOutputStream(clientSocket.getOutputStream());
			in = new ObjectInputStream(clientSocket.getInputStream());
		} catch (IOException e) {
			System.err.println("Error: " + e.getMessage());
		}
	}
	public void sendMessage(String msg)// Send message.
	{
		try{
			out.writeObject(msg);
			out.flush();
			System.out.println("Server:" + msg);
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
	public void sendFile(String temp) // Send file.
	{
		try{
			System.out.println("creating new file");
			File file = new File("./files-available-for-download/"+ temp);
			out.writeObject(temp);
			out.flush();
			temp = (String)in.readObject();
			out.writeObject(file.length());
			out.flush();
			byte[] mybytearray = new byte[(int) file.length()];
		    bin = new BufferedInputStream(new FileInputStream(file));
		    bin.read(mybytearray, 0, mybytearray.length);
		    OutputStream os = clientSocket.getOutputStream();
		    os.write(mybytearray, 0, mybytearray.length);
		    os.flush();    
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public String receiveMessage(){ // Receive Message and print to console.
		String msg = "";
		try {
			msg = (String)in.readObject();
			System.out.println("Client:" + msg);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return msg;
		}
	public Socket getClientSocket() { //Getter.
		return clientSocket;
	}
	public void disconnect(){ // Close Connections.
		try {
			in.close();
			out.close();
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
