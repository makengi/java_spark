package com.imr.spark;

import com.imr.spark.csv.model.Person;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;


import java.io.*;
import java.util.List;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
class SparkApplicationTests {

    @Autowired
    ResourceLoader resourceLoader;

    @Test
    void contextLoads() {
    }

    @Test
    void nullTest(){
        String str = null;
        assertThat(str,is(nullValue()));
    }

    @Test
    void 사람_CSV_테스트() throws IOException {

        Resource resource = resourceLoader.getResource("classpath:/files/person.csv");
        System.out.println(resource.exists());
        assertThat(resource.exists(),is(true));


        List<Person> persons = new CsvToBeanBuilder<Person>(new FileReader(resource.getFile()))
                .withType(Person.class)
                .build()
                .parse();
        assertThat(persons.size(),greaterThan(0));

        persons.forEach(person->System.out.println(person.toString()));

    }
    @Test
    void 맵리듀스_입력파일_읽기(){


    }

}
