package com.bezkoder.spring.security.postgresql.models;

import org.springframework.security.core.GrantedAuthority;

public enum ERole implements GrantedAuthority {
  ROLE_USER, ROLE_MODERATOR, ROLE_ADMIN;

  @Override
  public String getAuthority() {
    System.out.println(name());
    return name();
  }
}
