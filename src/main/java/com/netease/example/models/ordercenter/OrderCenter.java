package com.netease.example.models.ordercenter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderCenter {

    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private String processId;

    @Getter
    @Setter
    private String createTime;

    @Getter
    @Setter
    private String processKey;

    @Getter
    @Setter
    private String testType;

    @Getter
    @Setter
    private String subType;

    @Getter
    @Setter
    private boolean isPeriod = false;

    @Getter
    @Setter
    private String projectId;

    @Getter
    @Setter
    private String projectName;

    @Getter
    @Setter
    private String studio;

    @Getter
    @Setter
    private String applyerPopo;

    @Getter
    @Setter
    private String applyerName;

    @Getter
    @Setter
    private String authId;

    @Getter
    @Setter
    private String parentOrderId;

    @Getter
    @Setter
    private boolean delete = false;

}
