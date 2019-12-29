package com.netease.example.models.repositories;

import com.netease.example.models.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

}
