package com.netease.example.controllers;

import com.netease.example.dao.UserDao;
import com.netease.example.models.MyResponseContent;
import com.netease.example.models.repositories.UserRepository;
import com.netease.example.models.user.User;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDao userDao;

    @ApiOperation(value = "通过id获取用户信息", notes="返回用户信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public MyResponseContent getUserById(@PathVariable String id) {
        User user = userRepository.findById(id);
        com.netease.example.domain.User user1 = userDao.selectByPrimaryKey(Integer.parseInt(id));
        log.info("获取成功");
        return new MyResponseContent<>(true, user, "获取成功");
    }

    @ApiOperation(value = "创建用户", notes="创建用户")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public MyResponseContent addUser(@RequestBody User user) {
        userRepository.insert(user);
        log.info("创建成功");
        return new MyResponseContent<>(true, user, "创建成功");
    }

    @ApiOperation(value = "获取所有用户", notes="返回所有用户信息")
    @RequestMapping(value = "/allUser", method = RequestMethod.GET)
    public MyResponseContent getAllUser() {
        List<User> users = userRepository.findAll();
        log.info("获取所有用户成功");
        return new MyResponseContent<>(true, users, "获取所有用户成功");
    }

    @ApiOperation(value = "删除用户", notes="删除用户")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public MyResponseContent deleteUser(@PathVariable String id) {
        userRepository.deleteById(id);
        log.info("删除成功");
        return new MyResponseContent<>(true, null, "删除成功");
    }

    @ApiOperation(value = "根据id更新用户", notes="更新用户信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public MyResponseContent updateUser(@RequestBody User user) {
        userRepository.save(user);
        log.info("更新成功");
        return new MyResponseContent<>(true, user, "更新成功");
    }

}
