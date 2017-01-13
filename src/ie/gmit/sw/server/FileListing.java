package ie.gmit.sw.server;

import java.io.File;
import java.util.HashMap;

public class FileListing {
	
	private HashMap<Integer, String> fileListing =new HashMap<Integer, String>();
	private String files;
	private static File dir = new File("./files-available-for-download");

	public FileListing() {
		int i = 0;
		files = "File Listing:\n\n";
        File[] filesList = dir.listFiles();
        if(filesList.length==0)
        	files += "No files available for download.\n";
        for(File f : filesList){
        	i++;
            if(f.isFile()&&f.getName().charAt(0)!='.'&&(f.getName().equals("client-config.xml")==false)){
                files += i + " - " + f.getName() +"\n";
                fileListing.put(i, f.getName());
            	}//if
            }//for      
	}
	
	public String getAllFiles() {
        return files;
        }//method
	public String getValue(int key) {
		String value = fileListing.get(key);
        return value;
        }//method

}
