package com.imr.spark.csv.model;

import com.opencsv.bean.CsvBindByName;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
public class Person {
    @CsvBindByName(column = "이름")
    private String name;
    @CsvBindByName(column = "나이")
    private int age;
    @CsvBindByName(column = "직업")
    private String job;

    @Builder
    public Person(String name, int age ,String job){
        this.name= name;
        this.age = age;
        this.job = job;
    }
}


