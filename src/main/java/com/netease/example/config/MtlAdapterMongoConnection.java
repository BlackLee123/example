//package com.netease.example.config;
//
//import com.mongodb.MongoClient;
//import com.mongodb.MongoClientURI;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.mongodb.MongoDbFactory;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
//import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
//
//@Configuration
//@EnableMongoRepositories(
//        basePackages = {"com.netease.example"},
//        mongoTemplateRef = "mtlAdapterMongoTemplate"
//)
//public class MtlAdapterMongoConnection {
//
//    //Mongo DB Properties
//    @Value("${mtlAdapter.mongodb.host}")
//    private String host;
//    @Value("${mtlAdapter.mongodb.port}")
//    private int port;
//    @Value("${mtlAdapter.mongodb.database}")
//    private String database;
//    @Value("${mtlAdapter.mongodb.uri}")
//    private String uri;
//
//    /*
//     * Method that creates MongoDbFactory
//     * Common to both of the MongoDb connections
//     */
//    public MongoDbFactory mongoDbFactory() {
//        return new SimpleMongoDbFactory(getMongoClient(), database);
//    }
//
//    /*
//     * Method that creates MongoClient
//     */
//    private MongoClient getMongoClient() {
////        return new MongoClient(host, port);
//        return new MongoClient(new MongoClientURI(this.uri));
//    }
//
//    public
//    @Bean(name = "mtlAdapterMongoTemplate")
//    MongoTemplate getMongoTemplate() {
//        return new MongoTemplate(mongoDbFactory());
//    }
//
//}
