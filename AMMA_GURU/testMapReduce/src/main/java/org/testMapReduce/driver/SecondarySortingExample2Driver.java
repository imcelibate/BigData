package org.testMapReduce.driver;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.testMapReduce.customObjects.TemperaturePair;
import org.testMapReduce.customObjects.TemperaturePartitioner;
import org.testMapReduce.customObjects.YearMonthGroupingComparator;
import org.testMapReduce.mapper.SecondarySortingExample2Mapper;
import org.testMapReduce.reducer.SecondarySortingExample2Reducer;



public class SecondarySortingExample2Driver {

    public static void main(String[] args) throws Exception {


        Job job = Job.getInstance(new Configuration());
        FileInputFormat.addInputPath(job, new Path("/home/imcelibate/AMMA/tech/BIGData/MapReduce/workspace/SampleData/SecondarySort.txt")); 
        FileOutputFormat.setOutputPath(job, new Path("/home/imcelibate/AMMA/tech/BIGData/MapReduce/workspace/SampleData/output/SecondarySortExample2-"+(System.currentTimeMillis())));
        job.setJarByClass(SecondarySortingExample2Driver.class);
        job.setOutputKeyClass(TemperaturePair.class);
        job.setOutputValueClass(IntWritable.class);
        job.setMapperClass(SecondarySortingExample2Mapper.class);
        job.setPartitionerClass(TemperaturePartitioner.class);
        job.setGroupingComparatorClass(YearMonthGroupingComparator.class);
        job.setReducerClass(SecondarySortingExample2Reducer.class);        
        System.exit(job.waitForCompletion(true) ? 0 : 1);

    }
}
