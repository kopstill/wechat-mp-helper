package com.kopever.wechat.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Lullaby on 2018/1/11
 */
@RestController
public class RootController {

    @GetMapping
    public String four0four() {
        return "404";
    }

    @GetMapping("/")
    public String root() {
        return "Hello, world.";
    }

}
