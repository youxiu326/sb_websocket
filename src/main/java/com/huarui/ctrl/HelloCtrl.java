package com.huarui.ctrl;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloCtrl {


    @GetMapping("/")
    public String index(){
        return "websocket";
    }

    @GetMapping("/byZero")
    public String byZero(){
        int a =5/0;
        return "websocket";
    }

} 