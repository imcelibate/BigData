package org.testMapReduce.mapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.filecache.DistributedCache;

/**
     Created by imcelibate
     on Sep 18, 2017
*/

public class DistributedCacheWrdCntMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	
	private List<String> stopWords = new ArrayList<String>();
	
    @Override
    public void setup(Context context) throws IOException, InterruptedException {
    	try{
    		URI[] stopWordsFiles = context.getCacheFiles();
    		//Path[] stopWordsFiles = context.getLocalCacheFiles();
    		if(stopWordsFiles != null && stopWordsFiles.length > 0) {
    			for(URI stopWordFile : stopWordsFiles) {
    				readFile(stopWordFile,context);
    			}
    		}
    	} catch(IOException ex) {
    		System.err.println("Exception in mapper setup: " + ex.getMessage());
    	}
    }	
	
	@Override
	public void map(LongWritable key,Text value, Context context ){
		
		String keyStr = null;
		
		StringTokenizer stringTokenizer = new StringTokenizer(value.toString(), " ");
		while(stringTokenizer.hasMoreTokens()){
			try {
				keyStr = stringTokenizer.nextToken();
				if(!stopWords.contains(keyStr)){
					context.write(new Text(keyStr), new IntWritable(1));
				}				
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
//	private void readFile(URI uri) {
//		try{
//			System.out.println("File name "+ uri);
//			String file  = FileUtils.readFileToString(new File(uri));
//			BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(uri)));
//			String stopWord = null;
//			while((stopWord = bufferedReader.readLine()) != null) {
//				stopWords.add(stopWord.toLowerCase());
//			}
//			bufferedReader.close();
//		} catch(IOException ex) {
//			ex.printStackTrace();
//			System.err.println("Exception while reading stop words file: " + ex.getMessage());
//		}
//	}
	
	private void readFile(URI uri,Context context){
		try {
			FileSystem fs = FileSystem.get(uri,context.getConfiguration());
			Path hdfsreadpath = new Path(uri);
			  //Init input stream
			  FSDataInputStream inputStream = fs.open(hdfsreadpath);
			  //Classical input stream usage
			  String out= IOUtils.toString(inputStream, "UTF-8");
			  stopWords = Arrays.asList(out.split("\n"));
			  inputStream.close();
			  fs.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
}
		


