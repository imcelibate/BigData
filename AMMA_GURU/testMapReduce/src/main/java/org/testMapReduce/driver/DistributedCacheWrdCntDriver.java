package org.testMapReduce.driver;

import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.testMapReduce.mapper.DistributedCacheWrdCntMapper;
import org.testMapReduce.reducer.DistributedCacheWrdCntReducer;

/**
     Created by imcelibate
     on Sep 18, 2017
*/

public class DistributedCacheWrdCntDriver {

	public static void main(String[] args) {
		try {
			//Initialize the Hadoop job and set the jar as well as the name of the Job
			        JobConf jobconf  = new JobConf();
					Job job = new Job(jobconf);
					job.setJarByClass(DistributedCacheWrdCntDriver.class);
					job.setJobName("Word Counter With Stop Words Removal");
					
					//Add input and output file paths to job based on the arguments passed
					 FileInputFormat.addInputPath(job, new Path("/home/imcelibate/AMMA/tech/BIGData/MapReduce/workspace/SampleData/DistributedCacheWrdCnt.txt")); 
				     FileOutputFormat.setOutputPath(job, new Path("/home/imcelibate/AMMA/tech/BIGData/MapReduce/workspace/SampleData/output/DistributedCacheWrdCnt"));
				 
				
					job.setOutputKeyClass(Text.class);
					job.setOutputValueClass(IntWritable.class);
					
					//Set the MapClass and ReduceClass in the job
					job.setMapperClass(DistributedCacheWrdCntMapper.class);
					job.setReducerClass(DistributedCacheWrdCntReducer.class);
					System.out.println("AMMA");
//					jobconf.set("fs.hdfs.impl", 
//					        org.apache.hadoop.hdfs.DistributedFileSystem.class.getName()
//					    );
//					jobconf.set("fs.file.impl",
//					        org.apache.hadoop.fs.LocalFileSystem.class.getName()
//					    );
					
					//DistributedCache.addCacheFile(new URI("hdfs://localhost:54310/data/mapreduce/sampleData/DistributedCacheStopWords.txt"), job.getConfiguration());
			        
					
					job.addCacheFile(new URI("hdfs://localhost:54310/data/mapreduce/sampleData/DistributedCacheStopWords.txt"));
					//Wait for the job to complete and print if the job was successful or not
					int returnValue = job.waitForCompletion(true) ? 0:1;
					
					if(job.isSuccessful()) {
						System.out.println("Job was successful");
					} else if(!job.isSuccessful()) {
						System.out.println("Job was not successful");			
					}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}


