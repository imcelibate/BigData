package org.testMapReduce.mapper;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class UnitUsageMapper extends
		Mapper<LongWritable, /* Input key Type */
				Text, /* Input value Type */
				Text, /* Output key Type */
				IntWritable> /* Output value Type */
{
	@Override
	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {

		System.out.println("AMMA HERE in UnitUasgeMapper...........");
		String line = value.toString();
		StringTokenizer stringTokenizer = new StringTokenizer(line, "\t");
		String year = stringTokenizer.nextToken();
		String averageUsageUnitStr = null;
		int averageUsageUnit = 0;
		while(stringTokenizer.hasMoreTokens()){
			averageUsageUnitStr = stringTokenizer.nextToken();
			if(StringUtils.isNotEmpty(averageUsageUnitStr) && StringUtils.isNumeric(averageUsageUnitStr)){
				averageUsageUnit = Integer.parseInt(averageUsageUnitStr);
			}			
		}
		
		context.write(new Text(year), new IntWritable(averageUsageUnit));
	}
	

}
