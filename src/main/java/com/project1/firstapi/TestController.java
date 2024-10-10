package com.project1.firstapi;

import  org.springframework.web.bind.annotation.GetMapping;
import  org.springframework.web.bind.annotation.RequestMapping;
import  org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rest")
public class TestController {
    @GetMapping("test")
    public String getTest() {
        return "To żyje!";
    }
}
