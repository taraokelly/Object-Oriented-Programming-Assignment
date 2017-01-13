package ie.gmit.sw.client.config;

import java.io.File;
import java.util.HashMap;

public abstract class XMLParsable implements Parsable{
	//HashMap to store information extracted from an XML file
	private HashMap<String, String> clientConfig =new HashMap<String, String>();
	
	public XMLParsable(){}
	//this method will differentiate depending on the file being parsed
	public abstract void parseFile(File file);
	//getter
	public HashMap<String, String> getClientConfig() {
		return clientConfig;
	}
}