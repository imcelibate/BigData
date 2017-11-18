package org.testMapReduce.driver;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.testMapReduce.mapper.UnitUsageMapper;
import org.testMapReduce.reducer.UnitUsageReducer;

public class UnitUsageDriver {
	
	 public static void main(String args[])throws Exception
	   {
			Job job = new Job();
			job.setJarByClass(UnitUsageDriver.class);
			job.setJobName("Unit uasge analyzer");
			
			FileInputFormat.addInputPath(job, new Path("/home/imcelibate/AMMA/tech/BIGData/MapReduce/workspace/SampleData/UnitUsageSample.txt"));
			FileOutputFormat.setOutputPath(job,new Path("/home/imcelibate/AMMA/tech/BIGData/MapReduce/workspace/SampleData/output/UnitUsage-"+(System.currentTimeMillis())));
			
			job.setMapperClass(UnitUsageMapper.class);
			job.setReducerClass(UnitUsageReducer.class);
			job.setNumReduceTasks(1);
			
			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(IntWritable.class);
			
			System.exit(job.waitForCompletion(true) ? 0:1); 
			boolean success = job.waitForCompletion(true);
			int exitCode =  success ? 0 : 1;
			System.exit(exitCode);
	   }

}
