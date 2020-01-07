package com.netease.example.controllers;

import com.netease.example.models.MyResponseContent;
import com.netease.example.models.repositories.UserRepository;
import com.netease.example.models.user.User;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;

    @ApiOperation(value = "通过id获取用户信息", notes="返回用户信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public MyResponseContent getUserById(@PathVariable String id) {
        User user = userRepository.findById(id).get();
        logger.info("获取成功");
        return new MyResponseContent<>(true, user, "获取成功");
    }

    @ApiOperation(value = "创建用户", notes="创建用户")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public MyResponseContent addUser(@RequestBody User user) {
        userRepository.insert(user);
        logger.info("创建成功");
        return new MyResponseContent<>(true, user, "创建成功");
    }

    @ApiOperation(value = "获取所有用户", notes="返回所有用户信息")
    @RequestMapping(value = "/allUser", method = RequestMethod.GET)
    public MyResponseContent getAllUser() {
        List<User> users = userRepository.findAll();
        logger.info("获取所有用户成功");
        return new MyResponseContent<>(true, users, "获取所有用户成功");
    }

    @ApiOperation(value = "删除用户", notes="删除用户")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public MyResponseContent deleteUser(@PathVariable String id) {
        userRepository.deleteById(id);
        logger.info("删除成功");
        return new MyResponseContent<>(true, null, "删除成功");
    }

    @ApiOperation(value = "根据id更新用户", notes="更新用户信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public MyResponseContent updateUser(@RequestBody User user) {
        userRepository.save(user);
        logger.info("更新成功");
        return new MyResponseContent<>(true, user, "更新成功");
    }

}
