package com.os.weather.controller;

import com.os.weather.entity.User;
import com.os.weather.repository.UserRepository;
import com.os.weather.services.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Qualifier("userRepository")
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/editprofile")
    public String userProfileEdit(@RequestParam Map<String, Object> parameters, Model model) {
        String url = null;
        String userName = (String) parameters.get("id");
        User user = userService.findUserByUserName(userName, true);
        model.addAttribute("user", user);
        url = "view/userprofile/show";
        return url;
    }

    @RequestMapping(value = "/user/updateuserprofile", method = RequestMethod.POST)
    @ResponseBody
    public String updateUserProfile(@RequestParam Map<String, Object> parameters) throws IOException {
        String return_status = "";
        String user_name = (String) parameters.get("userName");

        try {
            if (parameters.get("id") != null && !Objects.equals(user_name, "")) {
                User user = userService.findUserByUserName(user_name.trim(), true);

                user.setEmail((String) parameters.get("email"));
                user.setFirstName((String) parameters.get("first_name"));
                user.setLastName((String) parameters.get("last_name"));

                userRepository.save(user);
                return_status = "success";
            } else {
                return_status = "error";
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
        return return_status;
    }

}
