package ie.gmit.sw.server.logger;

@SuppressWarnings("serial")
public class PoisonRequest extends Request {
	// Constructor
	public PoisonRequest(String request, String host, String status) {
		super(request, host, status);
	}
}