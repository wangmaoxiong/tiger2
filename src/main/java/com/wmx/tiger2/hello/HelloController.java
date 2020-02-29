package com.wmx.tiger2.hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

/**
 * @author wangmaoxiong
 */
@Controller
public class HelloController {

    /**
     * http://localhost:8080/hello/world?message=蚩尤后裔
     *
     * @param message :接收页面参数
     * @param model   ：往页面返回参数
     * @return
     */
    @GetMapping("hello/world")
    public String world(@RequestParam String message, Model model) {
        //设置返回参数
        model.addAttribute("message", message);
        model.addAttribute("token", UUID.randomUUID().toString());

        //跳转到 jsp 页面
        //return "/WEB-INF/pages/hello/world.jsp";
        return "hello/world";
    }

}
