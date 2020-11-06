package com.clayn.newtral.service;

import com.clayn.newtral.model.User;

public interface IUserService {
    String createUser(User user);
    void deleteUser(String string);
}
