
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.IOException;


/**
 * This is the main class of this project
 *
 */
public class MainClass {
	private static int Asize=12500;
	private static int counter;
	private static BufferedReader br;
	private static String filename;
	private static List<File> fileList=new ArrayList<File>();
	
    public static void main(String[] args) 
    {	
    	long start = System.currentTimeMillis();
    	counter=-1;
		filename="T1.txt";
		SortFiles sortFiles = new SortFiles();
		Comparator comparator = new Comparator();
		ProcessedFilesTracker processedFilesTracker = new ProcessedFilesTracker();
		processedFilesTracker.setProcessedFilesNumber(0);
		try{
			 br=new BufferedReader(new FileReader(filename));
		} catch (IOException e) {
            //Do Nothing
        }
		int max= (500000/Asize);
        List<String> gainedArrFromFile;
        for (int j = 0; j <max; j++) {
        	
            gainedArrFromFile = TPMMSFileReader.readFile(br);
            sortFiles.sortFiles(gainedArrFromFile);
            
            try {
            	counter ++;
            	TPMMSFileWriter.writeListToFile(gainedArrFromFile,counter, filename, fileList, Asize);
            } catch (IOException e) {
                e.printStackTrace();  
            }
        }
        System.out.println("Merging files...");
        Merge.mergeFiles( 500000, "T1", Asize);
       
        fileList.clear();
        
        System.out.println("Finish process and combine 1st set processed into T1Sorted.txt");
        
        counter=-1;
        processedFilesTracker.setProcessedFilesNumber(0);
    	
    	filename="T2_100.txt";
    	try{
    		br.close();
    		br=new BufferedReader(new FileReader(filename));
    	} catch (IOException e) {
            //Do Nothing
        }
    	max=1000000/Asize;
        for (int j = 0; j < (max); j++) {
        	gainedArrFromFile = TPMMSFileReader.readFile(br);
            if (gainedArrFromFile != null) {               
            	sortFiles.sortFiles(gainedArrFromFile);            	       
            }

            try {
            	counter ++;
            	TPMMSFileWriter.writeListToFile(gainedArrFromFile, counter, filename, fileList, Asize);
            } catch (IOException e) {
                //Do Nothing
            }
        }
        System.out.println("Merging files...");
        Merge.mergeFiles( 1000000, "T2_100", Asize);
		fileList.clear();		
        if(true)
        {	
            System.out.println("2nd set processed and combined into T2_100Sorted.txt");
        }
        System.out.println("Join the two main sorted tuples..");
        comparator.compareP();
        System.out.println("join is completed and stored into CommonRecords.txt");
        long end = System.currentTimeMillis();
        System.out.println("the program took: "+(end - start) / 1000f + " seconds");
        
    }

}
