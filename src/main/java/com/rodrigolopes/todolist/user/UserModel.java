package com.rodrigolopes.todolist.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "tb_users")
public class UserModel {

  @Id
  @GeneratedValue(generator = "UUID")
  private UUID id;

  private String name;
  private String username;
  private String password;

  @CreationTimestamp
  private LocalDateTime createdAt;

  // public String getName() {
  // return name;
  // }

  // public void setName(String name) {
  // this.name = name;
  // }

  // public String getUsername() {
  // return username;
  // }

  // public void setUsername(String username) {
  // this.username = username;
  // }

  // public String getPassword() {
  // return password;
  // }

  // public void setPassword(String password) {
  // this.password = password;
  // }

}
