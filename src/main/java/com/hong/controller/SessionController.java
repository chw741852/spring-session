package com.hong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by Cai on 2015/6/11 14:37.
 */
@Controller
public class SessionController {
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String name, String pass, HttpSession session) {
        session.setAttribute("name", name);
        session.setAttribute("pass", pass);

        return "login";
    }
}
