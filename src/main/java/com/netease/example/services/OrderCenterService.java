package com.netease.example.services;

import com.netease.example.models.OrderOperation;
import com.netease.example.models.ordercenter.OrderCenter;
import com.netease.example.models.ordercenter.OrderCenterResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderCenterService {

    private final Logger logger = LoggerFactory.getLogger(OrderCenterService.class);

    @Value("${ordercenter.url}")
    private String url;


    public OrderCenterResponse syncOrderToOrderCenter(String orderId, OrderCenter orderCenter, HttpHeaders headers, OrderOperation operation){
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<OrderCenter> entity = new HttpEntity<>(orderCenter, headers);
        ResponseEntity<OrderCenterResponse> resp;
        OrderCenterResponse ret;
        try {
            switch (operation) {
                case ADD:
                    resp = restTemplate.exchange(url, HttpMethod.POST, entity, OrderCenterResponse.class);
                    ret = resp.getBody();
                    break;
                case DELETE:
                    resp = restTemplate.exchange(url + "/" + orderId, HttpMethod.DELETE, entity, OrderCenterResponse.class);
                    ret = resp.getBody();
                    break;
                case UPDATE:
                    resp = restTemplate.exchange(url + "/" + orderId, HttpMethod.POST, entity, OrderCenterResponse.class);
                    ret = resp.getBody();
                    break;
                case SILENTLY:
                    resp = restTemplate.exchange(url + "/" + orderId + "/silently", HttpMethod.POST, entity, OrderCenterResponse.class);
                    ret = resp.getBody();
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }catch (HttpServerErrorException ex){
            logger.error("fail to sync order with order center: " + ex.getMessage());
            ex.printStackTrace();
            return new OrderCenterResponse(false, "fail to sync order with order center: " + ex.getMessage());
        }catch (RestClientException ex){
            logger.error("fail to connect to order center: " + ex.getMessage());
            ex.printStackTrace();
            return new OrderCenterResponse(false, "fail to connect to order center: " + ex.getMessage());
        }
        return ret;
    }

    public OrderCenterResponse getOrderCenterOrder(String orderId){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url + "/" + orderId, OrderCenterResponse.class);
    }

}
