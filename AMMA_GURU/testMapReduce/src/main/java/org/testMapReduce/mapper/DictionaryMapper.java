package org.testMapReduce.mapper;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class DictionaryMapper extends Mapper<Text, Text, Text, Text>{
	
	private Text word = new Text();
	
	@Override
	public void map(Text key, Text value, Context context) throws IOException, InterruptedException{
	       StringTokenizer itr = new StringTokenizer(value.toString(),",");
	        while (itr.hasMoreTokens())
	        {
	            word.set(itr.nextToken());
	            context.write(key, word);
	        }
	   
   }

}
