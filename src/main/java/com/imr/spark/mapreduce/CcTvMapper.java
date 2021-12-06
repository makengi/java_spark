package com.imr.spark.mapreduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CcTvMapper extends Mapper<Object, Text,Text, IntWritable> {

    private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        List<String> adminStrs = Arrays.asList(value.toString().split("\t"));
        word.set(adminStrs.get(0));
        context.write(word, one);
    }
}
