package ie.gmit.sw.server.logger;
/* Tara O'Kelly - G00322214
 * Object Oriented Programming, Third Year, Software Development, GMIT.
 */
@SuppressWarnings("serial")
public class PoisonRequest extends Request {// IS-A Request
	// Constructor
	// Inherit from Request
	public PoisonRequest(String request, String host, String status) {
		super(request, host, status);
	}
}