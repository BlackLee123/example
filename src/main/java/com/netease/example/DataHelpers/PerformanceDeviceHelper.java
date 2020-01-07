package com.netease.example.DataHelpers;

import com.netease.example.models.repositories.UserRepository;
import com.netease.example.models.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PerformanceDeviceHelper {

    private static UserRepository userRepository;

    @Autowired
    protected PerformanceDeviceHelper(UserRepository userRepository) {
        PerformanceDeviceHelper.userRepository = userRepository;
    }

    public static User map(String name) {
        User user = userRepository.findByName(name);
        if (user != null) {
            return user;
        } else {
            return emptyUser(name);
        }
    }

    private static User emptyUser(String name) {
        User device = new User();
        device.setName(name);
        return device;
    }

}
