package com.Invest.system.Dto;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginCredentials {

    private String username;
    private String password;
}
