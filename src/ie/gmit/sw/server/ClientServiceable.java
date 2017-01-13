package ie.gmit.sw.server;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public abstract class ClientServiceable extends Thread{
	private Socket clientSocket; 
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private BufferedInputStream bin;
	
	public ClientServiceable(Socket s) {
		this.clientSocket = s;
	}
	public abstract void run();
	public void getStreams(){
		try {
			out = new ObjectOutputStream(clientSocket.getOutputStream());
			in = new ObjectInputStream(clientSocket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void sendMessage(String msg)
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
	public void sendFile(String temp)
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
	public String receiveMessage(){
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
	public Socket getClientSocket() {
		return clientSocket;
	}
	public void disconnect(){
		try {
			in.close();
			out.close();
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
