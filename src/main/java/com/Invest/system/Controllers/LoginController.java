package com.Invest.system.Controllers;


import com.Invest.system.Dto.LoginCredentials;
import com.Invest.system.Dto.UserRegisterDto;
import com.Invest.system.Objects.Users;
import com.Invest.system.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;


    @PostMapping("/login")
    public void loginUser(@RequestBody LoginCredentials credentials){
    }


}
