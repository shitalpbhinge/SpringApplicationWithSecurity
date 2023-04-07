package com.example.spring.jwt.mongodb.controllers;


import java.util.Map;
import java.util.Properties;
import java.io.IOException;
import java.util.HashMap;

import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/apifile")
public class SparkController 
{
	private static final Logger logger = LoggerFactory.getLogger(SparkController .class);

    private static final String CSV_PATH = "/home/inferyx/Documents/Files/addresses.csv";
    private static final String PSV_PATH = "/home/inferyx/Documents/Files/output.psv";
    private static final String TSV_PATH = "/home/inferyx/Documents/Files/mlb_players.tsv";
   // private static final String JSON_PATH = "/home/inferyx/Documents/Files/example_2.json";
    private static final String EXCEL_PATH = "/home/inferyx/Documents/Files/file_Example.xls";

    private final JavaSparkContext javaSparkContext;
    private final SparkSession sparkSession;

    public SparkController(JavaSparkContext javaSparkContext, SparkSession sparkSession) 
    {
        this.javaSparkContext = javaSparkContext;
        this.sparkSession = sparkSession;
    }
    @GetMapping("/files")
    public String readFile() {

        // read CSV file
       Dataset<Row> csv = sparkSession.read()
                .option("header", "true")
                .option("inferSchema", "true")
                .csv(CSV_PATH);
        		Dataset<Row> data=csv.toDF();
        	//	data.createOrReplaceTempView("csv_data");
        		data.show();
        logger.info("Reading Csv File");

        // read PSV file
        Dataset<Row> psv = sparkSession.read()
                .option("header", "true")
                .option("inferSchema", "true")
                .option("delimiter", "|")
                .csv(PSV_PATH);
        Dataset<Row> data1=psv.toDF();
      //  data1.createOrReplaceTempView("psv_data");
		data1.show();
		   logger.info("Reading Psv File");
        // read TSV file
        Dataset<Row> tsv = sparkSession.read()
                .option("header", "true")
                .option("inferSchema", "true")
                .option("delimiter", "\t")
                .csv(TSV_PATH);
        Dataset<Row> data2=tsv.toDF();
      //  data2.createOrReplaceTempView("tsv_data");
		data2.show();
		   logger.info("Reading Psv File");
     // read JSON file
		Dataset<Row> jsonData = sparkSession.read()
				.option("header", "true")
				.option("inferSchema", "true")
                .option("multiline", "true")
                .option("dropMalformed", "true") // to drop corrupt rows
                .json("/home/inferyx/Documents/Files/sample.json");
        Dataset<Row> jsonDataFrame = jsonData.toDF();
        jsonDataFrame.show();
        logger.info("Reading Json File");
  
				 

    
        // read Excel file
        
        Dataset<Row> excel = sparkSession.read()
                .format("com.crealytics.spark.excel")
                .option("header", "true")
                .option("inferSchema", "false")
            
                //.option("useHeader", "true")
                //.option("mode", "DROPMALFORMED")
                .load(EXCEL_PATH);
        	Dataset<Row> data4=excel.toDF();
        		data4.show();
        		   logger.info("Reading Excel File");
        // Create a temporary table for each file
        csv.createOrReplaceTempView("csvTable");
        tsv.createOrReplaceTempView("tsvTable");
        psv.createOrReplaceTempView("psvTable");
        jsonData.createOrReplaceTempView("jsonTable");
        excel.createOrReplaceTempView("excelTable");
        
        Properties connectionProperties = new Properties();
        connectionProperties.put("user", "inferyx");
        connectionProperties.put("password", "20Inferyx!9");
        connectionProperties.put("driver", "com.mysql.cj.jdbc.Driver");
        connectionProperties.put("url", "jdbc:mysql://localhost:3306/SparkData");
        
        //Writting csv
        csv.write()
        .mode(SaveMode.Append)
        .option("driver", "com.mysql.cj.jdbc.Driver")
        .jdbc("jdbc:mysql://localhost:3306/SparkData", "CSVTable", connectionProperties);
        Dataset<Row> d1=csv.toDF();
  		d1.show();
  	   logger.info("writting Csv File");
  		
  		//writting psv
  		psv.write()
        .mode(SaveMode.Append)
        .option("driver", "com.mysql.cj.jdbc.Driver")
        .jdbc("jdbc:mysql://localhost:3306/SparkData", "PSVTable", connectionProperties);
        Dataset<Row> d2=psv.toDF();
  		d2.show();
  	   logger.info("writting Psv File");
  	//writting tsv
  		tsv.write()
        .mode(SaveMode.Append)
        .option("driver", "com.mysql.cj.jdbc.Driver")
        .jdbc("jdbc:mysql://localhost:3306/SparkData", "TSVTable", connectionProperties);
        Dataset<Row> d3=tsv.toDF();
  		d3.show();
  	   logger.info("writting Tsv File");
  	//writting json
  	//	jsonData.write()
       // .mode(SaveMode.Append)
       
      //  .option("createTableOptions", "ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci")
      //  .jdbc("jdbc:mysql://localhost:3306/SparkData", "JSONTable", connectionProperties);
  		 //Dataset<Row> d4=jsonData.toDF();
  		// d4.show();
  		Dataset<String> jsonStrings = jsonDataFrame.toJSON();
        jsonStrings.show();
        jsonStrings.write().mode(SaveMode.Append)
        .option("driver", "com.mysql.cj.jdbc.Driver")
        .option("multiline", "true")
        .option("dropMalformed", "true") 
        .jdbc("jdbc:mysql://localhost:3306/SparkData", "JsonTable",connectionProperties);
        Dataset<Row> d4=excel.toDF();
  		 d4.show();
  	   logger.info("writting Json File");
        //writting excel
   			excel.write()
         .mode(SaveMode.Append)
         .option("driver", "com.mysql.cj.jdbc.Driver")
         
         .option("createTableOptions", "ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci")
         .jdbc("jdbc:mysql://localhost:3306/SparkData", "Excel", connectionProperties);
   		 Dataset<Row> d5=excel.toDF();
   		 d5.show();
   	   logger.info("writting Excel File");
  		 
  		 
        return "Reading File And Create Temp table for each file also writting data to MYSQL";
    }
}
