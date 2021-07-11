// package com.netease.example.models.repositories;

// import com.mongodb.client.result.DeleteResult;
// import com.netease.example.models.user.User;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Qualifier;
// import org.springframework.data.mongodb.core.MongoTemplate;
// import org.springframework.data.mongodb.core.query.Criteria;
// import org.springframework.data.mongodb.core.query.Query;
// import org.springframework.data.mongodb.repository.MongoRepository;

// import java.util.List;

// public class UserRepository {
// @Autowired
// @Qualifier(value = "primaryReviewMongoTemplate")
// private MongoTemplate mongoTemplate;

// public User findById(String id) {
// User user = mongoTemplate.findById(id, User.class);
// return user;
// }

// public User findByName(String name) {
// Query query = new Query(Criteria.where("name").is(name));
// User user = mongoTemplate.findOne(query, User.class);
// return user;
// }

// public void insert(User user) {
// mongoTemplate.insert(user);
// }

// public User save(User user) {
// user = mongoTemplate.save(user);
// return user;
// }

// public long deleteById(String id) {
// Query query = new Query(Criteria.where("_id").is(id));
// DeleteResult result = mongoTemplate.remove(query, User.class);
// return result.getDeletedCount();
// }

// public List<User> findAll() {
// return mongoTemplate.findAll(User.class);
// }
// }
