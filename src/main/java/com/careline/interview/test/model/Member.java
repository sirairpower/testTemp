package com.careline.interview.test.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;


public class Member implements Serializable {

  private static final long serialVersionUID = 1L;
  @JsonProperty(value = "memberId")
  private String id;

  private String email;
  @JsonIgnore
  private String password;
  @JsonProperty(value = "name")
  private String nickname;

  public void setId(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getEmail() {
    return email;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPassword() {
    return password;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getNickname() {
    return nickname;
  }

  @Override
  public String toString() {
    return "MEMBER{" +
        "ID=" + id + '\'' +
        "EMAIL=" + email + '\'' +
        "PASSWORD=" + password + '\'' +
        "NICKNAME=" + nickname + '\'' +
        '}';
  }
}
