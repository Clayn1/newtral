package com.clayn.newtral.service;

import com.clayn.newtral.dtos.UserPageDTO;
import com.clayn.newtral.model.UserPage;
import org.springframework.data.domain.PageRequest;

public interface IUserPageService {
    UserPage insertUserPage(UserPage userPage);

    UserPage updateUserPage(UserPage userPage, Integer userId);

    void deleteUserPage(Integer id);

    UserPage getUserPage(Integer id);

    UserPageDTO getAllUsers(PageRequest pageRequest, String username);

    void deleteUserPage(String username);
    UserPage getUserPage(String username);
}
