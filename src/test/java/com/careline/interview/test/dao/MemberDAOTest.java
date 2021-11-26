package com.careline.interview.test.dao;

import com.careline.interview.test.model.Member;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberDAOTest {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private MemberDAO memberDAO;
  @Rule
  public ExpectedException exceptionRule = ExpectedException.none();
  @Autowired
  private PasswordEncoder passwordEncoder;

  @Test
  public void testMemberDAO() {
    logger.info("Run save() ...");
    Member member = new Member();
    member.setEmail("test@email");
    member.setPassword("test password");
    member.setNickname("test nickname");
    String memberId = memberDAO.save(member);
    member.setId(memberId);

    logger.info("Run findById() ... {}", memberId);
    Member memberQueried = memberDAO.findById(memberId);
    Assert.assertEquals(memberId, memberQueried.getId());
    Assert.assertEquals(1, memberDAO.findAll().size());

    logger.info("Run update()");
    member.setEmail("test_1@email");
    memberDAO.update(member);
    memberQueried = memberDAO.findById(memberId);
    Assert.assertEquals(member.getEmail(), memberQueried.getEmail());

    logger.info("Run deleteById()");
    memberDAO.deleteById(memberId);
    Assert.assertEquals(0, memberDAO.findAll().size());

  }

  @Test
  public void testDuplicateEmail() {
    logger.info("test DuplicateKeyException of email");
    Member member = new Member();
    member.setEmail("test@email");
    member.setPassword("test password");
    member.setNickname("test nickname");
    String memberId = memberDAO.save(member);
    member.setId(memberId);
    logger.info("id : {}", memberId);

    exceptionRule.expect(DuplicateKeyException.class);
    member.setNickname("nick2");
    member.setPassword("fake password");
    memberDAO.save(member);
  }
}
