package com.imr.spark;

import com.imr.spark.mapreduce.CcTvMapper;
import java.io.IOException;

import com.imr.spark.mapreduce.CctvReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SparkApplication {

    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();

        Job job = Job.getInstance(conf, "spark");
        job.setJar("spark.jar");

        job.setMapperClass(CcTvMapper.class);
        job.setCombinerClass(CctvReducer.class);
        job.setReducerClass(CctvReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.addInputPath(job, new Path("/user/cctv/"));
        FileOutputFormat.setOutputPath(job, new Path("/user/cctv_output"));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
