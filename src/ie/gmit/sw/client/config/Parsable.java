package ie.gmit.sw.client.config;
/* Tara O'Kelly - G00322214
 * Object Oriented Programming, Third Year, Software Development, GMIT.
 */
import java.io.File;

public interface Parsable {
	// Method an expected Parsable class should conform to.
	public abstract void parseFile(File file);
}