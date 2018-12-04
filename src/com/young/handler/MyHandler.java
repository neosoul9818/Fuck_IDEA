package com.young.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ${NeoSoul} on 2017/10/11.
 */
@Controller
@RequestMapping("/handler")
public class MyHandler {

    @RequestMapping("/hello")
    public String helloSpringmvc(){
        return "success";
    }
}
