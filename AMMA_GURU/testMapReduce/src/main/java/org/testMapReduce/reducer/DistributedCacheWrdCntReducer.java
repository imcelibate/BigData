package org.testMapReduce.reducer;

import java.io.IOException;
import java.util.Collection;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

/**
     Created by imcelibate
     on Sep 18, 2017
*/

public class DistributedCacheWrdCntReducer extends Reducer<Text, IntWritable , Text, IntWritable>{
	
	int count = 0;

	
	
	@Override
	public void reduce(Text key, Iterable<IntWritable> values, Context context)
			throws IOException, InterruptedException {
		try {
			System.out.println("AMMA at reducer");
			for(IntWritable value : values){
				count = count + value.get();
			}
			context.write(key, new IntWritable(count));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {			
			e.printStackTrace();
	 }	
	}
}


