package me.emou.xworker.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangdao
 */
@RestController
@RequestMapping("test")
public class TestController {

    @RequestMapping(method = RequestMethod.GET)
    public String method() {
        return "test";
    }
}
