package com.netease.example.controllers;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//@Log4j2
@RestController
public class ExampleController {

    private final Logger log = LogManager.getLogger(this.getClass());

    @SneakyThrows
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String index(@RequestParam String name) {
        log.info("hello "+name+"，this is first messge");
        log.error("hello "+name+"，this is first messge");
        if (name.equals("libai")){
            throw new Exception("libai");
        }
        return "hello "+name+"，this is first messge";
    }
}
