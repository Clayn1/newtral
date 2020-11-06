package com.clayn.newtral.service;

import com.clayn.newtral.dtos.PostDTO;
import com.clayn.newtral.dtos.PostWithUserInfo;
import com.clayn.newtral.model.Post;
import org.springframework.data.domain.PageRequest;

public interface IPostService {
    Post insertPost(Post post, Integer userId);

    Post updatePost(Post post);

    void deletePost(Integer id);

    PostWithUserInfo getPost(Integer id);

    PostDTO getAllPosts(PageRequest pageRequest, String section, String title);
}
