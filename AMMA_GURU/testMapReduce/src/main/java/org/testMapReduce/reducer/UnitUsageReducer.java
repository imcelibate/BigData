package org.testMapReduce.reducer;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class UnitUsageReducer extends Reducer<Text, IntWritable, Text, Text> {
	
	private int maxValue = Integer.MIN_VALUE;
	private int minValue = Integer.MAX_VALUE;
	private String maxValueKey = "";
	private String minValueKey = "";
	
	@Override
	public void reduce(Text key, Iterable<IntWritable> values, Context context)
			throws IOException, InterruptedException {
		
		System.out.println("AMMA reached UnitUsageReducer1");	
		System.out.println("AMMA reached UnitUsageReducer2");
		for (IntWritable value : values) {
			if(value.get() > maxValue){
				maxValue = value.get();
				maxValueKey = key.toString();
			}
			if(value.get() < minValue){
				minValue = value.get();
				minValueKey = key.toString();
			}
		}
	}

	@Override
	public void cleanup(Context context){
		try{ 
			String output = "Max usage : "+maxValueKey+" - "+maxValue+"\nMin usage : "+minValueKey+" - "+minValue;
			context.write(new Text(maxValueKey+"-"+minValueKey), new Text(output));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
