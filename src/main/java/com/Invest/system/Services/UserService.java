package com.Invest.system.Services;


import com.Invest.system.Objects.Users;
import com.Invest.system.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder encoder;


    @Transactional
    public Users addUser(Users user){
        if(userRepository.findByUsername(user.getUsername()).isPresent())
        {
            throw new UsernameNotFoundException("Acciut already exist");
        }
        Users entity = new Users(user.getUsername(),encoder.encode(user.getPassword()), user.getEmail());

        return userRepository.save(entity);
    }

    @Transactional
    public Users editUser(Users user) {
        Users tempUser = userRepository.findById(user.getUser_id()).get();
        if(tempUser != null) {
            tempUser.setUsername(user.getUsername());
            tempUser.setPassword(encoder.encode(user.getPassword()));
            tempUser.setEnabled(user.isEnabled());
        }
        return null;

    }

    public Set<Users> gettAllUsers() {
        return userRepository.findAll().stream().collect(Collectors.toSet());
    }

    public Optional<Users> getUserByName(String username){
        return userRepository.findByUsername(username);
    }
}
