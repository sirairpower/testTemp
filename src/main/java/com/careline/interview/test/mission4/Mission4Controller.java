package com.careline.interview.test.mission4;

import com.careline.interview.test.model.Member;
import com.careline.interview.test.service.AnswerService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("mission4")
public class Mission4Controller {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private AnswerService answerService;

  @GetMapping("/getAllMembers")
  @ResponseBody
  public Map<String, List<Member>> getAllMembers(){
    logger.debug("call getAllMembers()");
    Map<String, List<Member>> resultMap = new HashMap<>();
    resultMap.put("memberList", answerService.listAllMember());
    return resultMap;
  }

}
