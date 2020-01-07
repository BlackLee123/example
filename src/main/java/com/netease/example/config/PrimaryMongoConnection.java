package com.netease.example.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@Configuration
@EnableMongoRepositories(
        basePackages = {"com.netease.sensitivity.models"},
        mongoTemplateRef = "primaryReviewMongoTemplate")
public class PrimaryMongoConnection {

    //Mongo DB Properties
    @Value("${primary.mongodb.host}")
    private String host;
    @Value("${primary.mongodb.port}")
    private int port;
    @Value("${primary.mongodb.database}")
    private String database;
    @Value("${primary.mongodb.uri}")
    private String uri;

    /*
     * Method that creates MongoDbFactory
     * Common to both of the MongoDb connections
     */
    public MongoDbFactory mongoDbFactory() {
        return new SimpleMongoDbFactory(getMongoClient(), database);
    }

    /*
     * Method that creates MongoClient
     */
    private MongoClient getMongoClient() {
//        return new MongoClient(host, port);
        return new MongoClient(new MongoClientURI(this.uri));
    }

    @Primary
    public
    @Bean(name = "primaryReviewMongoTemplate")
    MongoTemplate getMongoTemplate() {
        return new MongoTemplate(mongoDbFactory());
    }

}
