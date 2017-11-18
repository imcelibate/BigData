package org.testMapReduce.driver;


import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.testMapReduce.mapper.MaxTemperatureMapper;
import org.testMapReduce.reducer.MaxTemperatureReducer;

/*This class is responsible for running map reduce job*/
public class MaxTemperatureDriver extends Configured implements Tool{

	public int run(String[] args) throws Exception
	{
	
		if(false) {
			System.err.println("Usage: MaxTemperatureDriver <input path> <outputpath>");
			System.exit(-1);
		}
		
		Job job = new Job();
		job.setJarByClass(MaxTemperatureDriver.class);
		job.setJobName("Max Temperature");
		
		FileInputFormat.addInputPath(job, new Path("/home/imcelibate/AMMA/tech/BIGData/MapReduce/workspace/SampleData/MaxTemperature/sample.txt"));
		FileOutputFormat.setOutputPath(job,new Path("/home/imcelibate/AMMA/tech/BIGData/MapReduce/workspace/SampleData/MaxTemperature/output"));
		
		job.setMapperClass(MaxTemperatureMapper.class);
		job.setReducerClass(MaxTemperatureReducer.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		System.exit(job.waitForCompletion(true) ? 0:1); 
		boolean success = job.waitForCompletion(true);
		return success ? 0 : 1;
	}

	public static void main(String[] args) throws Exception {
		MaxTemperatureDriver driver = new MaxTemperatureDriver();
		int exitCode = ToolRunner.run(driver, args);
		System.exit(exitCode);
	}
}
