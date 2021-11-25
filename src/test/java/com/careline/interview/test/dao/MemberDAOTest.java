package com.careline.interview.test.dao;

import com.careline.interview.test.model.Member;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberDAOTest {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private MemberDAO memberDAO;

  @Test
  public void testMemberDAO() {
    logger.info("Run save() ...");
    Member member = new Member();
    member.setId("test id");
    member.setEmail("test email");
    member.setPassword("test password");
    member.setNickname("test nickname");
    memberDAO.save(member);

    logger.info("Run findAll() ... ");
    memberDAO.findAll().forEach(m -> {
      logger.info("In h2.member : {}", ToStringBuilder.reflectionToString(m));
    });
  }
}
