package com.careline.interview.test.mission2;

import com.careline.interview.test.model.input.ComputeIn;
import com.careline.interview.test.model.output.ComputeOut;
import com.careline.interview.test.service.AnswerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("mission2")
public class Mission2Controller {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private AnswerService answerService;

  @PostMapping("/compute")
  @ResponseBody
  public ComputeOut compute(@RequestBody ComputeIn computeIn) {
    logger.debug("compute() received : {}", computeIn.getNumList());
    return answerService.dealWithCompute(computeIn);
  }
}
