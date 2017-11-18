package org.testMapReduce.driver;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.testMapReduce.mapper.DictionaryMapper;
import org.testMapReduce.reducer.DictionaryReducer;

public class DictionaryDriver {
	
	public static void main(String[] args) throws Exception
    {
        Configuration conf = new Configuration();
        Job job = new Job(conf, "dictionary");
        job.setJarByClass(DictionaryDriver.class);
        job.setMapperClass(DictionaryMapper.class);
        job.setReducerClass(DictionaryReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        job.setInputFormatClass(KeyValueTextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);
        FileInputFormat.addInputPath(job, new Path("/home/imcelibate/AMMA/tech/BIGData/MapReduce/workspace/SampleData/dictionary.txt")); 
        FileOutputFormat.setOutputPath(job, new Path("/home/imcelibate/AMMA/tech/BIGData/MapReduce/workspace/SampleData/output/dictionary-"+(System.currentTimeMillis())));
        boolean result = job.waitForCompletion(true);
        System.exit(result ? 0 : 1);
    }

}
