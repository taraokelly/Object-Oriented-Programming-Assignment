package ie.gmit.sw.server.logger;


@SuppressWarnings("serial")
public class Request extends Requestable {
	
	public Request(String request, String host, String status) {
		super(request, host, status);
	}
	
	// Methods
	@Override
	public String toString() {
		return String.format("[%s] %s requested by %s %tR %<tp on %<te %<tB %<tY", getStatus(), getRequest(), getHost(), getDate());
	}
}