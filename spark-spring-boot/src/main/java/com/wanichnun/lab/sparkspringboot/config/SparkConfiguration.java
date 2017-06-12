package com.wanichnun.lab.sparkspringboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import spark.Filter;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

import static spark.Spark.before;

@Configuration
@EnableTransactionManagement
public class SparkConfiguration {

    @Autowired(required = false)
    private List<Spark> sparks = new ArrayList<>();

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    CommandLineRunner sparkRunner () {
//        DataSource dataSource = dataSource();
//        DB database = new DB("default");
//        Database.setup(database, dataSource);
//
//        before(openDbConnection());
        before(returnAsJson());
//        before(enableCORE());
//        after(closeDbConnection());

        return args -> sparks.stream().forEach(Spark::register);
    }

//    private Filter closeDbConnection() {
//        return (request, response) -> Database.getDB().close();
//    }
//
//    private Filter openDbConnection() {
//        return (request, response) -> {
//            Database.getDB().open(Database.getDataSource());
//        };
//    }
//
    private Filter returnAsJson() {
        return (request, response) -> response.header("Content-Type", "application/json");
    }
//
//    private Filter enableCORE() {
//        return (request, response) -> {
//            response.header("Access-Control-Allow-Origin", "*");
//            response.header("Access-Control-Request-Method", "GET,POST,PUT,DELETE");
//            response.header("Access-Control-Allow-Methods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");
//            response.header("Access-Control-Allow-Headers", "X-Requested-With,Content-Type,Accept,Origin,X-FMS-Token");
//        };
//    }

}
