package com.hong.controller;

import com.hong.model.User;
import com.hong.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Cai on 2015/6/11 14:37.
 */
@Controller
public class SessionController {
    @Autowired
    private IUserService userService;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String name, String pass, HttpServletRequest request) {
        request.getSession().setAttribute("name", name);
        request.getSession().setAttribute("pass", pass);

        System.out.println("set username: " + name);
        System.out.println("set password: " + pass);

        return "login";
    }

    @RequestMapping(value = "/getUsername", method = RequestMethod.GET)
    public String getUsername() {
        return "username";
    }

    @RequestMapping("/testCache")
    public String testCache(ModelMap modelMap) {
        User user = userService.getUser("1");
        user = userService.updateUser(user);

        modelMap.addAttribute("user", user);

        return "cache";
    }
}
