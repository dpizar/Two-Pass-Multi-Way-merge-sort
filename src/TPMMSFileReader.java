
import java.util.List;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.IOException;

/**
 * This class is used to read the files and return their contents
 *
 */
public class TPMMSFileReader {
	BufferedReader br;
	private static int Asize=12500;
	
	public TPMMSFileReader (BufferedReader bufferReader){
		br = bufferReader;
	}
	
	public static List<String> readFile(BufferedReader br) {	
	    List<String> returnedArray = new ArrayList<String>();    
	    try {
	    	for (int i = 0 ; i < Asize; i++) {
	            String temp=br.readLine();
	            if(temp==null){
	            	break;
	            }
	            returnedArray.add(temp.trim());
	        }	    	
		    } catch (IOException e) {
		       //Do nothing
		    }
	    return returnedArray;
	}
}
