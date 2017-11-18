package org.testMapReduce.reducer;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;
import org.testMapReduce.customObjects.TemperaturePair;

/**
     Created by imcelibate
     on Sep 17, 2017
*/

public class SecondarySortingExample2Reducer extends Reducer<TemperaturePair, IntWritable, Text, Text>{
	
	@Override
	protected void reduce(TemperaturePair key,Iterable<IntWritable> values,Context context ){
		
		StringBuilder sortedTemp = new StringBuilder();
		for(IntWritable value :values){
			sortedTemp.append(value);
			sortedTemp.append(" >> ");
		}		
		try {
			context.write(new Text(key.getYearMonth()), new Text(sortedTemp.toString()));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}


