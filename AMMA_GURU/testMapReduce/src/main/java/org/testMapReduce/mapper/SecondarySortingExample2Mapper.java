package org.testMapReduce.mapper;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.testMapReduce.customObjects.TemperaturePair;

import java.io.IOException;


public class SecondarySortingExample2Mapper extends Mapper<LongWritable, Text, TemperaturePair, IntWritable> {

    private TemperaturePair temperaturePair = new TemperaturePair();
    private static final int MISSING = 9999;

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String yearMonth = line.substring(15, 21);

        int tempStartPosition = 87;

        if (line.charAt(tempStartPosition) == '+') {
            tempStartPosition += 1;
        }

        int temp = Integer.parseInt(line.substring(tempStartPosition, 92));

        if (temp != MISSING) {
            temperaturePair.setYearMonth(yearMonth);
            temperaturePair.setTemperature(temp);
            context.write(temperaturePair, new IntWritable(temp));
        }
    }
}
