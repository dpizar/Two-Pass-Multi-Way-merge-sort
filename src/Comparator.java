
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * This class compares tuples with the files *
 */
public class Comparator {

public  void compareP(){
	Writer writer=null;
	BufferedReader br1=null;
	BufferedReader br2=null;
	try { 
		 br1=new BufferedReader(new FileReader("T1Sorted.txt"));
		 br2=new BufferedReader(new FileReader("T2_100Sorted.txt"));
		 writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("SharedRecords.txt")));
		 String s1="";
		 String s2="";		 
		 int counter=0;
		 int indexT1=0;
		 int indexT2=0;
		 s1=br1.readLine();
		 s2=br2.readLine();
		 while (true)
		 { 
			 if(s1==null||s2==null||indexT1>500000||indexT2>1000000)
			 {
				 break;
			 }
			 if(s1.substring(0, 7).equals(s2.substring(0, 7)))
			 { 
				 writer.write(s1+s2);
		         writer.write("\r");
				 counter++;
				 indexT1++;
				 indexT2++;
				 s1=br1.readLine();
				 s2=br2.readLine();
			 }
			 else
			 {
				 if(s1.substring(0, 7).compareTo(s2.substring(0, 7)) < 0){
					 s1=br1.readLine();
					 indexT1++;
				 }
				 else{
					 s2=br2.readLine();
					 indexT2++;
				 }
			 }
		 }
		 System.out.println("The two files share "+counter+" records");
		}
	 catch (IOException e) {
		e.printStackTrace();
	} 
	
	finally {
		try{
			if (br1 != null)
				br1.close();
			if (br2!=null)
				br2.close();
			if (writer!=null)
				writer.close();
		} catch (IOException ex) {
		ex.printStackTrace();
		}
	}
}
}
