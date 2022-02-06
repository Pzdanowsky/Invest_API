package com.Invest.system.Dto.Mappers;

import com.Invest.system.Dto.UserRegisterDto;
import com.Invest.system.Dto.WalletDto;
import com.Invest.system.Objects.Users;

public class UserMapper {

    private UserMapper() {
    }


    public static UserRegisterDto mapToUserRegisterDto(Users user){
        return UserRegisterDto.builder()
                .id(user.getUser_id())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }
}
