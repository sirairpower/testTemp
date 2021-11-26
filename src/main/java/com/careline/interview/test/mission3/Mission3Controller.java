package com.careline.interview.test.mission3;

import com.careline.interview.test.model.Member;
import com.careline.interview.test.service.AnswerService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("mission3")
public class Mission3Controller {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private AnswerService answerService;

  @PostMapping("/register")
  @ResponseBody
  public Map<String, String> register(Member member) {
    logger.debug("register() received : {}", ToStringBuilder.reflectionToString(member, ToStringStyle.JSON_STYLE));
    List<String> invalid = new ArrayList<>();
    if (validate(member, invalid)) {
      try {
        answerService.register(member);

      } catch (DuplicateKeyException e) {
        invalid.add("email duplicated");
      } catch (Exception e) {
        invalid.add(e.getMessage());
      }
    }
    Map<String, String> returnMap = new HashMap<>();
    if (invalid.size() > 0) {
      returnMap.put("msg", String.join(", ", invalid));
    } else {
      returnMap.put("memberId", member.getId());
    }
    return returnMap;
  }

  private boolean validate(Member member, List<String> invalid) {
    if (StringUtils.isBlank(member.getEmail())) {
      invalid.add("Email can't be blank");
    }
    if (StringUtils.isBlank(member.getPassword())) {
      invalid.add("Password can't be blank");
    }
    if (StringUtils.isBlank(member.getNickname())) {
      invalid.add("Name can't be blank");
    }
    return invalid.size() == 0;
  }


}
