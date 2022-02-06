package com.Invest.system.Controllers;


import com.Invest.system.Objects.Users;
import com.Invest.system.Services.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("/users")
    public Set<Users> gettAllUsers(){
        return userService.gettAllUsers();
    }


    @PutMapping("/users")
    public Users editUser(@RequestBody Users user){
        return userService.editUser(user);
    }

}
