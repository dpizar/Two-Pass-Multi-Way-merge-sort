

import java.util.Map;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.HashMap;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * This class is to merge the sorted lists into one list.
 *
 */
public class Merge {
	
	public static  void mergeFiles(int maxL,String newFilename,int Asize){
		
		Map<String, BufferedReader> bufferreaders = new HashMap<>();
		int Generalsize=(maxL/Asize);	
		Writer writer=null;
		try{
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFilename+"Sorted.txt")));
			for(int i=0;i<Generalsize ;i++)
			{
				String b = String.format("%05d", i);
				bufferreaders.put("br"+b, new BufferedReader(new FileReader("SampleOutput"+b+".txt")));
			}			
			String []inputTA=new String[(Generalsize)];
			int counter=0;
			
			for(int i=0;i<inputTA.length;i++)
			{
				String b = String.format("%05d", i);
				String temp=bufferreaders.get("br"+b).readLine();
				inputTA[i]=temp.substring(0, 7)+"br"+b+temp;
			}			
			counter=inputTA.length;
			
			for(int i=0;i<maxL;i++)
			{
				int min=1000000000;
				int index=-1;
				for(int j=0;j<inputTA.length ;j++){
			        if(Integer.parseInt(inputTA[j].substring(0, 7)) < min){	
			        	min=Integer.parseInt(inputTA[j].substring(0, 7));
			            index = j;
			        }
				}
				String temp=inputTA[index].substring(14);
				writer.write(inputTA[index].substring(14));
	            writer.write("\r"); 
	            
				temp=inputTA[index].substring(7, 14);
				String record="";
			        
				record=bufferreaders.get(temp).readLine();
	    		if(record==null)
	    		{
	    			inputTA[index]="99999999";
	    		}
	    		else{
	    			inputTA[index]=record.substring(0, 7)+temp+record;
	    			counter++;
	    		}
			    min=1000000000;
			}
			System.out.println(counter+" records compined together in "+newFilename+"Sorted.txt");
			
		}catch (IOException e) {
			e.printStackTrace();
		} 
		finally {
			try{
				for(int i=0;i<(Generalsize);i++)
				{
					String b = String.format("%05d", i);
					if(bufferreaders.get("br"+b)!=null)
						bufferreaders.get("br"+b).close();
				}				
				writer.close();				
			} catch (IOException ex) {
			//Swallow exception
			}
		}
	}
}
