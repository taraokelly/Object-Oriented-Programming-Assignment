package ie.gmit.sw;
/* Tara O'Kelly - G00322214
 * Object Oriented Programming, Third Year, Software Development, GMIT.
 */
import ie.gmit.sw.client.UI;
//Runner Class.
public class Client {
	public static void main(String args[])
	{	
		UI ui = new UI();// HAS-A UI - Full Composition.
		ui.run();
	}
}