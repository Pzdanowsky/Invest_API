package com.Invest.system.Dto;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserRegisterDto {

    private long id;

    private String username;

    private String email;
}
