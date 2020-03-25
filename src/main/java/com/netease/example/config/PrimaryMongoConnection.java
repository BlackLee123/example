package com.netease.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@Configuration
@EnableMongoRepositories
public class PrimaryMongoConnection {

    @Value("${primary.mongodb.uri}")
    private String uri;

    /*
     * Method that creates MongoDbFactory
     * Common to both of the MongoDb connections
     */
    public MongoDbFactory mongoDbFactory() {
        return new SimpleMongoClientDbFactory(this.uri);
    }

    @Bean(name = "primaryReviewMongoTemplate")
    MongoTemplate getMongoTemplate() {
        return new MongoTemplate(mongoDbFactory());
    }

}
