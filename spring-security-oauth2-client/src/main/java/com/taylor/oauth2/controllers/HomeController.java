/*
 * File: src\main\java\com\taylor\oauth2\controllers\HomeController.java
 * Project: spring-security-oauth2-client
 * Created Date: Monday, March 18th 2024, 4:43:05 pm
 * Author: Rui Yu (yurui_113@hotmail.com)
 * -----
 * Last Modified: Monday, 18th March 2024 4:43:40 pm
 * Modified By: Rui Yu (yurui_113@hotmail.com>)
 * -----
 * Copyright (c) 2024 Rui Yu
 * -----
 * HISTORY:
 * Date                     	By       	Comments
 * -------------------------	---------	----------------------------------------------------------
 * Monday, March 18th 2024	Rui Yu		Initial version
 */
package com.taylor.oauth2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String homePage() {
        return "redirect:index";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/anonymous")
    public String anonymous(Model model) {
        model.addAttribute("helloText", "匿名页面是匿名用户可以访问的公共内容。");
        return "anonymous";
    }

    @GetMapping("/accessDenied")
    public String accessDenied() {
        return "accessDenied";
    }
}