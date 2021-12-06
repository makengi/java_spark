package com.imr.spark;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.Job;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import scala.Tuple2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class SparkTests {

    @Autowired
    private ResourceLoader resourceLoader;

    @Test
    public void 스파크_테스트() throws Exception {


        SparkConf sparkConf = new SparkConf().setMaster("local[2]").setAppName("JD Word Counter");
        JavaSparkContext context = new JavaSparkContext(sparkConf);
        Resource resource = resourceLoader.getResource("classpath:files/input.txt");
        JavaRDD<String> inputFile = context.textFile(resource.getFile().getAbsolutePath());
        JavaRDD<String> dictionary = inputFile.flatMap(content -> Arrays.asList(content.split(" ")).iterator());
        JavaPairRDD countData = dictionary.mapToPair(t -> new Tuple2(t, 1)).reduceByKey((x, y) -> (int) x + (int) y);
        countData.saveAsTextFile("CountData");
    }

    @Test
    public void 맵리듀스() throws IOException,ClassNotFoundException,InterruptedException {
        Configuration configuration = new Configuration();

        Job job = Job.getInstance(configuration, "cctv");

    }
}
