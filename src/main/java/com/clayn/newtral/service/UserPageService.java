package com.clayn.newtral.service;

import com.clayn.newtral.dtos.UserPageDTO;
import com.clayn.newtral.model.UserPage;
import com.clayn.newtral.repository.UserPageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserPageService implements IUserPageService{
    @Autowired
    private UserPageRepository userPageRepository;

    @Override
    public UserPage insertUserPage(UserPage userPage) {
        return userPageRepository.save(userPage);
    }

    @Override
    public UserPage updateUserPage(UserPage userPage, Integer userId) {
        UserPage userPageById = userPageRepository.findUserPageById(userId).get();
        userPageById.setDescription(userPage.getDescription() != null ? userPage.getDescription() : userPageById.getDescription());
        userPageById.setHtmlTemplate(userPage.getHtmlTemplate() != null ? userPage.getHtmlTemplate() : userPageById.getHtmlTemplate());
        userPageById.setImageURL(userPage.getImageURL() != null ? userPage.getImageURL() : userPageById.getImageURL());
        userPageById.setLevel(userPage.getLevel() != null ? userPage.getLevel() : userPageById.getLevel());
        userPageById.setReputation(userPage.getReputation() != null ? userPage.getReputation() : userPageById.getReputation());
        userPageById.setUsername(userPage.getUsername() != null ? userPage.getUsername() : userPageById.getUsername());
        return userPageRepository.save(userPageById);
    }

    @Override
    public void deleteUserPage(Integer id) {
        userPageRepository.deleteById(id);
    }

    @Override
    public UserPage getUserPage(Integer id) {
        return userPageRepository.findById(id).get();
    }

    @Override
    public UserPageDTO getAllUsers(PageRequest pageRequest, String username) {
        Page<UserPage> userPages = userPageRepository.findUserPagesByUsernameContaining(username, pageRequest);
        UserPageDTO userPageDTO = new UserPageDTO();
        userPageDTO.setUserPages(userPages.getContent());
        userPageDTO.setEmpty(userPages.isEmpty());
        userPageDTO.setLast(userPages.isLast());
        userPageDTO.setPagesCount(userPages.getTotalPages());
        userPageDTO.setTotalElements(userPages.getNumberOfElements());
        return userPageDTO;
    }

    @Override
    public void deleteUserPage(String username) {
        userPageRepository.deleteById(userPageRepository.findUserPageByUsername(username).get().getId());
    }

    @Override
    public UserPage getUserPage(String username) {
        return userPageRepository.findUserPageByUsername(username).get();
    }
}
