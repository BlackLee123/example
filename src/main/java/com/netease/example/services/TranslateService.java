package com.netease.example.services;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStreamReader;
import java.util.Hashtable;

@Service
public class TranslateService {
    private Hashtable<String, String> zhmap = GetConf("zh.json");
    private Hashtable<String, String> enmap = GetConf("en.json");
    private Hashtable<String, String> jpmap = GetConf("jp.json");

    public Hashtable<String,String> GetConf(String path){
        Hashtable<String,String> map = new Hashtable<>();
        try {
            ClassPathResource classPathResource = new ClassPathResource(path);
            String str = IOUtils.toString(new InputStreamReader(classPathResource.getInputStream(),"UTF-8"));
            map = JSONObject.parseObject(str, Hashtable.class);
        }
        catch (Exception e) {
            System.out.println("#################### translate file " + path + " init error");
            return map;
        }
        return map;
    }

    public String translate(String lang, String key, String defaultValue) {
        String value = "";
        try {
            switch (lang){
                case "zh":
                    value = zhmap.get(key);
                    break;
                case "en":
                    value = enmap.get(key);
                    break;
                case "jp":
                    value = jpmap.get(key);
                    break;
                default:
                    return defaultValue;
            }
        }
        catch (Exception e) {
            return defaultValue;
        }
        if (value == null){
            return defaultValue;
        }
        return value;
    }

}

