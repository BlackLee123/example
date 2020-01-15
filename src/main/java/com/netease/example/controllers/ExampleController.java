package com.netease.example.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ExampleController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String index(@RequestParam String name) {
        log.info("hello "+name+"，this is first messge");
        return "hello "+name+"，this is first messge";
    }
}
