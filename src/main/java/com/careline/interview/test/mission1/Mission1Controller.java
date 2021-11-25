package com.careline.interview.test.mission1;

import com.careline.interview.test.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("mission1")
public class Mission1Controller {

  @Autowired
  private AnswerService answerService;

  @GetMapping("/hello")
  @ResponseBody
  public String hello() {
    return "Hello! (" + answerService.formatLocalDateTime() + ")";
  }

}
