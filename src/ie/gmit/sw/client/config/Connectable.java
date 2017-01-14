package ie.gmit.sw.client.config;
/* Tara O'Kelly - G00322214
 * Object Oriented Programming, Third Year, Software Development, GMIT.
 */
public interface Connectable {
	// Methods an expected Connectable class should conform to.
	public abstract Boolean isConnected();
	public abstract void connect();
	public abstract void disconnect();
}
