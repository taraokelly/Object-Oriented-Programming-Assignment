package ie.gmit.sw.server;
/* Tara O'Kelly - G00322214
 * Object Oriented Programming, Third Year, Software Development, GMIT.
 */
import java.io.File;
import java.util.HashMap;

public class FileListing {
	// Variables
	private HashMap<Integer, String> fileListing =new HashMap<Integer, String>();
	private File dir;
	// Constructor
	public FileListing(String path) {
		this.dir = new File(path);
	}
	// Methods
	public String getAllFiles() { // Construct String of files, and add to the hashmap. Return String of files.
		int i = 0;
		String files = "File Listing:\n\n";
        File[] filesList = dir.listFiles();
        if(filesList.length==0)
        	files += "No files available for download.\n";
        for(File f : filesList){
        	i++;
            if(f.isFile()&&f.getName().charAt(0)!='.'&&(f.getName().equals("client-config.xml")==false)){ // In the case the user picks the current directory.
                files += i + " - " + f.getName() +"\n";
                fileListing.put(i, f.getName());
            	}//if
            }//for      
        return files;
        }//method
	public String getValue(int key) { // Get the value of corresponding key. Returns null if empty.
		String value = fileListing.get(key);
        return value;
        }//method

}
