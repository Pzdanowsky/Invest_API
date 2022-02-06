package com.Invest.system.Controllers;


import com.Invest.system.Dto.Mappers.UserMapper;
import com.Invest.system.Dto.UserRegisterDto;
import com.Invest.system.Objects.Users;
import com.Invest.system.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;



    @PostMapping("/registration")
    public UserRegisterDto registrationUser(@RequestBody Users user){
        return UserMapper.mapToUserRegisterDto(userService.addUser(user));
    }


}
