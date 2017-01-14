package ie.gmit.sw.server.logger;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public abstract class Requestable implements Serializable{
	private String request; 
	private String host;
	private Date date;
	private String status;
	
	public Requestable(String request, String host, String status) {
		super();
		this.request = request;
		this.host = host;
		this.status = status;
		this.date = new Date();
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
