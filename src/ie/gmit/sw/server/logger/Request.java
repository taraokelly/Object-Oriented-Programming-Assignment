package ie.gmit.sw.server.logger;
/* Tara O'Kelly - G00322214
 * Object Oriented Programming, Third Year, Software Development, GMIT.
 */
@SuppressWarnings("serial")
public class Request extends Requestable {// IS-A Requestable
	// Constructor
	// Inherit from Requestable
	public Request(String request, String host, String status) {
		super(request, host, status);
	}
	// toString method
	@Override
	public String toString() {
		return String.format("[%s] %s requested by %s %tR %<tp on %<te %<tB %<tY", getStatus(), getRequest(), getHost(), getDate());
	}
}