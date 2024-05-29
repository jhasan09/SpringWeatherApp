package com.os.weather.controller;

import com.os.weather.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public User firstPage() {

        User user = new User();

        user.setId(1);
        user.setUserName("user1");
        user.setFirstName("mr");
        user.setLastName("user");

        return user;
    }

}
