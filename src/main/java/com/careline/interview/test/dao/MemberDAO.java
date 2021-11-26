package com.careline.interview.test.dao;

import com.careline.interview.test.model.Member;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public List<Member> findAll() {
    return jdbcTemplate.query("SELECT * FROM member", new BeanPropertyRowMapper<>(Member.class));
  }

  public Member findById(String id) {
    return jdbcTemplate.queryForObject("SELECT * FROM member WHERE id=?", new BeanPropertyRowMapper<Member>(Member.class), id);
  }

  public int deleteById(String id) {
    return jdbcTemplate.update("DELETE FROM member WHERE id=?", id);
  }

  public String save(Member member) {
    final String uuid = UUID.randomUUID().toString();
    jdbcTemplate.update("INSERT INTO member (id , email, password, nickname) VALUES (?, ?, ?, ?)", new Object[] {uuid, member.getEmail(), member.getPassword(), member.getNickname()});
    member.setId(uuid);
    return uuid;
  }

  public int update(Member member) {
    return jdbcTemplate.update("UPDATE member SET email = ?, password = ?, nickname = ? WHERE id = ?", new Object[] {member.getEmail(), member.getPassword(), member.getNickname(), member.getId()});
  }
}
