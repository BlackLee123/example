package com.netease.example.controllers;

import com.netease.example.models.repositories.UserRepository;
import com.netease.example.models.user.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @ApiOperation(value = "通过id获取用户信息", notes="返回用户信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getUserById(@PathVariable String id) {
        User user = userRepository.findById(id).get();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @ApiOperation(value = "通过id获取用户信息", notes="返回用户信息")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Object> addUser(@RequestBody User user) {
        userRepository.insert(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @ApiOperation(value = "获取所有用户", notes="返回用户信息")
    @RequestMapping(value = "/allUser", method = RequestMethod.GET)
    public ResponseEntity<Object> getAllUser() {
        List<User> users = userRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @ApiOperation(value = "获取所有用户", notes="返回用户信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteUser(@PathVariable String id) {
        userRepository.deleteById(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @ApiOperation(value = "获取所有用户", notes="返回用户信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<Object> updateUser(@RequestBody User user) {
        userRepository.save(user);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

}
