package com.hbn.SpringEcom.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeCotroller {

    @GetMapping("/hello")
    public String greeting(){
        return "welcome to the website";
    }
}
