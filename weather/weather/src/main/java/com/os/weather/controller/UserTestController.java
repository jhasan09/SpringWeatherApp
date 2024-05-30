package com.os.weather.controller;

import com.os.weather.entity.User;
import com.os.weather.services.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserTestController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public User firstPage(@RequestBody User user) {

        System.out.println("User : " + user);
        userService.saveUser(user);

        return user;
    }

}
