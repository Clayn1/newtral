package com.clayn.newtral.controller;

import com.clayn.newtral.dtos.PostDTO;
import com.clayn.newtral.dtos.PostWithUserInfo;
import com.clayn.newtral.model.Post;
import com.clayn.newtral.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;

    @RequestMapping(path = "/{postId}", method = RequestMethod.GET)
    public PostWithUserInfo getPost(@PathVariable Integer postId) {
        return postService.getPost(postId);
    }

    @RequestMapping(method = RequestMethod.GET)
    public PostDTO getAllPosts(@RequestParam(defaultValue = "1") int page,
                               @RequestParam(defaultValue = "100") int size,
                               @RequestParam(defaultValue = "id") String orderBy,
                               @RequestParam(defaultValue = "Desc") String direction,
                               @RequestParam(defaultValue = "") String section,
                               @RequestParam(defaultValue = "") String title) {
        return postService.getAllPosts(PageRequest.of(page - 1, size,
                Sort.by(Sort.Direction.fromString(direction), orderBy, "id")), section, title);
    }

    @RequestMapping(path = "/user/{userId}", method = RequestMethod.POST)
    public Post sendPost(@RequestBody Post post, @PathVariable Integer userId) {
        return postService.insertPost(post, userId);
    }

    @RequestMapping(path = "/{postId}", method = RequestMethod.DELETE)
    public void deletePost(@PathVariable Integer postId) {
        postService.deletePost(postId);
    }
}
