package com.youxiu326.ctrl;


import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloCtrl {

    /**
     * 动态代码布局
     * @return
     */
    @GetMapping("/view")
    public String view(){
        return "view";
    }

    /**
     * 静态代码布局
     * @return
     */
    @GetMapping("/index")
    public String layout(){
        return "index";
    }

    @GetMapping("/vue")
    public String vue(){
        return "vue";
    }

    @GetMapping("/")
    public String index(){
        return "websocket";
    }

    @GetMapping("/menus/data")
    public JSONPObject menusData(){


        return null;
    }

    @GetMapping("/byZero")
    public String byZero(){
        int a =5/0;
        return "websocket";
    }

} 