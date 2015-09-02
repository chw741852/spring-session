package com.hong.controller;

import com.hong.model.User;
import com.hong.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Set;

/**
 * Created by cai on 2015/9/2 11:18.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("index")
    public String index() {
        return "/user/index";
    }

    @RequestMapping("/get")
    public String get(String id, ModelMap modelMap) {
        User user = userService.getUser(id);
        modelMap.addAttribute("user", user);

        return "/user/detail";
    }

    @RequestMapping("/add")
    public String add() {
        return "/user/add";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(User user) {
        userService.saveUser(user);

        return "redirect:/user/get?id=" + user.getId();
    }

    @RequestMapping("/edit")
    public String edit(String id, ModelMap modelMap) {
        User user = userService.getUser(id);
        modelMap.addAttribute("user", user);

        return "/user/edit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(User user) {
        userService.updateUser(user);

        return "redirect:/user/get?id=" + user.getId();
    }

    @RequestMapping(value = "/addRedisValue", method = RequestMethod.POST)
    public String key(String value) {
        stringRedisTemplate.opsForValue().set("vv", value);
        stringRedisTemplate.opsForHash().put("vh", "test", value);
        stringRedisTemplate.opsForList().leftPush("vl", value);
        stringRedisTemplate.opsForSet().add("vs", value);
        stringRedisTemplate.opsForZSet().add("vzs", value, 1);

        return "/user/index";
    }

    @RequestMapping("/getRedisValue")
    public String getRedisValue(ModelMap modelMap) {
        String vv = stringRedisTemplate.opsForValue().get("vv");
        String vh = (String) stringRedisTemplate.opsForHash().get("vh", "test");
        String vl = stringRedisTemplate.opsForList().leftPop("vl");
        String vs = stringRedisTemplate.opsForSet().pop("vs");
        Set<String> vzs = stringRedisTemplate.opsForZSet().range("vzs", 0, -1);

        modelMap.addAttribute("vv", vv);
        modelMap.addAttribute("vh", vh);
        modelMap.addAttribute("vl", vl);
        modelMap.addAttribute("vs", vs);
        modelMap.addAttribute("vzss", vzs);

        return "/user/getRedisValue";
    }
}
