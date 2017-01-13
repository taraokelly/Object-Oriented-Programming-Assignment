package ie.gmit.sw.client.config;

public interface Connectable {
	public abstract Boolean isConnected();
	public abstract void connect();
	public abstract void disconnect();
}
