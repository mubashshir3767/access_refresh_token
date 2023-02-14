package com.example.refresh_access_token.receive;

import lombok.Data;

@Data
public class UsernamePasswordRequestDto {
  private String username;
  private String password;
}
