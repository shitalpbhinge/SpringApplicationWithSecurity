package com.example.spring.jwt.mongodb.config;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//spark class
@Configuration
public class SparkConfig {


  private static final String APP_NAME = "MyAppName";
    private static final String MASTER = "local[*]";
    @Bean
    public SparkConf sparkConf() {
        return new SparkConf()
                .setAppName(APP_NAME)
                .setMaster(MASTER);
    }
    @Bean
    public JavaSparkContext javaSparkContext() 
    {
        JavaSparkContext javaSparkContext = new JavaSparkContext(sparkConf());
        return javaSparkContext;
    }

    @Bean
    public SparkSession sparkSession() 
    {
        SparkSession sparkSession = SparkSession.builder()
                .sparkContext(javaSparkContext().sc())
                .appName(APP_NAME)
                .getOrCreate();
        return sparkSession;
    }
}
