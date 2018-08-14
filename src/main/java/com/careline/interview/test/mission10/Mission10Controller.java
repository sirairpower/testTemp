package com.careline.interview.test.mission10;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("mission10")
public class Mission10Controller {

    private int count = 0;
    
    @RequestMapping("call")
    @ResponseBody
    public int call() {
        return ++count;
    }
}
