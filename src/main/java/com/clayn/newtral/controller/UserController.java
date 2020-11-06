package com.clayn.newtral.controller;

import com.clayn.newtral.dtos.AuthenticationRequest;
import com.clayn.newtral.dtos.AuthenticationResponse;
import com.clayn.newtral.model.User;
import com.clayn.newtral.service.JwtService;
import com.clayn.newtral.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public String registerUser(@RequestBody User user) {
        return userService.createUser(user);
    }
    @RequestMapping(path = "/{username}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable String username){
        userService.deleteUser(username);
    }

    @RequestMapping(path = "/auth", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        String token = jwtService.generateToken(authenticationRequest.getUsername());
        return new AuthenticationResponse(token);
    }
}
