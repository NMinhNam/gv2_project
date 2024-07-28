package com.xepgiangvien2.xepgiangvien2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @RequestMapping(value = "/**/{path:[^\\.]*}")
    public String redirect() {
        // Forward to home page so that route is preserved.
        return "forward:/index.html";
    }
}
