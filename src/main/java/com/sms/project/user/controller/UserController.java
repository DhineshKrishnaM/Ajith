package com.sms.project.user.controller;

import com.sms.project.user.entity.Login;
import com.sms.project.user.entity.User;
import com.sms.project.user.entity.UserDto;
import com.sms.project.user.repo.UserRepo;
import com.sms.project.user.security.jwt.JwtUtils;
import com.sms.project.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserRepo userRepo;
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> listOfUser = userService.getAllUsersList();
        return new ResponseEntity<>(listOfUser, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/deleteUserById")
    public ResponseEntity<String> deleteUserById(@RequestParam int id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>("User details has been deleted", HttpStatus.OK);
    }

    @PostMapping("/save")
    public UserDto createUser(@RequestBody UserDto user) {
        return userService.saveUser(user);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PutMapping("/update")
    public UserDto updateUserDetails(@RequestParam int userId, @RequestBody UserDto userDto) {
        return userService.updateUserDetails(userId, userDto);
    }

    /***
     * @param userId
     * @return
     */
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/getUserById")
    public UserDto getUserDetailsById(@RequestParam int userId) {
        return userService.getUserById(userId);
    }

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody Login authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        return jwtUtils.generateJwtToken(authentication);
    }

    public User get() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        System.out.println(email);
        User loginUser = userRepo.findByEmail(email).get();
        return loginUser;
    }
}
