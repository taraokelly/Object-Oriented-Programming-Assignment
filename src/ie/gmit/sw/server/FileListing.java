package ie.gmit.sw.server;

import java.io.File;
import java.util.HashMap;

public class FileListing {
	
	private HashMap<Integer, String> fileListing =new HashMap<Integer, String>();
	private File dir;

	public FileListing(String path) {
		this.dir = new File(path);
	}
	
	public String getAllFiles() {
		int i = 0;
		String files = "File Listing:\n\n";
        File[] filesList = dir.listFiles();
        if(filesList.length==0)
        	files += "No files available for download.\n";
        for(File f : filesList){
        	i++;
            if(f.isFile()&&f.getName().charAt(0)!='.'&&(f.getName().equals("client-config.xml")==false)){//in the case the user picks the current directory
                files += i + " - " + f.getName() +"\n";
                fileListing.put(i, f.getName());
            	}//if
            }//for      
        return files;
        }//method
	public String getValue(int key) {
		String value = fileListing.get(key);
        return value;
        }//method

}
