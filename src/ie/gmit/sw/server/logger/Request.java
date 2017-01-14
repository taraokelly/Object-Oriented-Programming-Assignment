package ie.gmit.sw.server.logger;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Request implements Serializable {
	private String request; 
	private String host;
	private Date date;
	private String status;
	
	public Request(String request, String host, String status) {
		super();
		this.request = request;
		this.host = host;
		this.status = status;
		this.date = new Date();
	}
	
	// Methods
	@Override
	public String toString() {
		return String.format("[%s] %s requested by %s %tR %<tp on %<te %<tB %<tY", status, request, host, date);
	}
}