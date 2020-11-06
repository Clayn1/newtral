package com.clayn.newtral.controller;

import com.clayn.newtral.dtos.UserPageDTO;
import com.clayn.newtral.model.UserPage;
import com.clayn.newtral.service.UserPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserPageController {
    @Autowired
    private UserPageService userPageService;

    @RequestMapping(method = RequestMethod.GET)
    public UserPageDTO getUsers(@RequestParam(defaultValue = "1") int page,
                                @RequestParam(defaultValue = "10") int size,
                                @RequestParam(defaultValue = "id") String orderBy,
                                @RequestParam(defaultValue = "Desc") String direction,
                                @RequestParam(defaultValue = "") String username){
        return userPageService.getAllUsers(PageRequest.of(page - 1, size,
                Sort.by(Sort.Direction.fromString(direction), orderBy, "id")), username);
    }
    @RequestMapping(path = "/{username}", method = RequestMethod.GET)
    public UserPage getUser(@PathVariable String username){
        return userPageService.getUserPage(username);
    }
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public UserPage createUser(@RequestBody UserPage user){
        return userPageService.insertUserPage(user);
    }
    @RequestMapping(path = "/{userId}", method = RequestMethod.PUT)
    public UserPage updateUser(@RequestBody UserPage user, @PathVariable Integer userId){
        return userPageService.updateUserPage(user, userId);
    }
    @RequestMapping(path = "/{username}", method = RequestMethod.DELETE)
    public void deletePost(@PathVariable String username){
        userPageService.deleteUserPage(username);
    }
}
