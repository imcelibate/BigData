package org.testMapReduce.driver;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.testMapReduce.customObjects.TemperaturePair;
import org.testMapReduce.customObjects.TemperaturePartitioner;
import org.testMapReduce.customObjects.YearMonthGroupingComparator;
import org.testMapReduce.mapper.SecondarySortingTemperatureMapper;
import org.testMapReduce.reducer.SecondarySortingTemperatureReducer;

/**
 * User: Bill Bejeck
 * Date: 1/9/13
 * Time: 10:22 PM
 */
public class SecondarySortDriver {

    public static void main(String[] args) throws Exception {


        Job job = Job.getInstance(new Configuration());
        FileInputFormat.addInputPath(job, new Path("/home/imcelibate/AMMA/tech/BIGData/MapReduce/workspace/SampleData/SecondarySort.txt")); 
        FileOutputFormat.setOutputPath(job, new Path("/home/imcelibate/AMMA/tech/BIGData/MapReduce/workspace/SampleData/output/SecondarySort-"+(System.currentTimeMillis())));
        job.setJarByClass(SecondarySortDriver.class);
        job.setOutputKeyClass(TemperaturePair.class);
        job.setOutputValueClass(NullWritable.class);
        job.setMapperClass(SecondarySortingTemperatureMapper.class);
        job.setPartitionerClass(TemperaturePartitioner.class);
        job.setGroupingComparatorClass(YearMonthGroupingComparator.class);
        job.setReducerClass(SecondarySortingTemperatureReducer.class);
        System.exit(job.waitForCompletion(true) ? 0 : 1);

    }
}
