package ie.gmit.sw.client.config;
/* Tara O'Kelly - G00322214
 * Object Oriented Programming, Third Year, Software Development, GMIT.
 */
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLParser extends XMLParsable {
	
	public XMLParser(){}
	
	public void parseFile(File file){
		/* DOM - Document Object Model.
		 * Using the DOM to parse the XML document, then store an in memory tree representation of
		 * the XML document. From there, extract meaningful pieces of information,
		 * in this case, extract the set of values required to interact with the remote server.
		 */	
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);
			NodeList list = doc.getElementsByTagName("client-config");
			for(int i = 0; i < list.getLength(); i++){
				Node node = list.item(i);	
				if(node.getNodeType() == Node.ELEMENT_NODE){
					Element element = (Element) node;
					getClientConfig().put("username", element.getAttribute("username"));
					getClientConfig().put("server-host", element.getElementsByTagName("server-host").item(0).getTextContent());
					getClientConfig().put("server-port", element.getElementsByTagName("server-port").item(0).getTextContent());
					getClientConfig().put("download-dir", element.getElementsByTagName("download-dir").item(0).getTextContent());
				}
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void clearClientConfig() {
		getClientConfig().clear();
	}
}