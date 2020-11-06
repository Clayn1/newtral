package com.clayn.newtral.service;

import com.clayn.newtral.dtos.PostDTO;
import com.clayn.newtral.dtos.PostWithUserInfo;
import com.clayn.newtral.model.Post;
import com.clayn.newtral.repository.PostRepository;
import com.clayn.newtral.repository.UserPageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class PostService implements IPostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserPageRepository userPageRepository;

    Logger logger = LoggerFactory.getLogger("App");

    @Override
    public Post insertPost(Post post, Integer userId) {
        userPageRepository.findUserPageById(userId).ifPresent(userPage -> {
            post.setUser(userPage);
            postRepository.save(post);
        });
        return post;
    }

    @Override
    public Post updatePost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public void deletePost(Integer id) {
        postRepository.deleteById(id);
    }

    @Override
    public PostWithUserInfo getPost(Integer id) {
        Post post = postRepository.findById(id).get();
        PostWithUserInfo postWithUserInfo = new PostWithUserInfo();
        postWithUserInfo.setPost(post);
        postWithUserInfo.setUsername(post.getUser().getUsername());
        postWithUserInfo.setUserLevel(post.getUser().getLevel());
        postWithUserInfo.setUserReputation(post.getUser().getReputation());
        postWithUserInfo.setUserImageURL(post.getUser().getImageURL());
        postWithUserInfo.setUserDescription(post.getUser().getDescription());
        return postWithUserInfo;
    }

    @Override
    public PostDTO getAllPosts(PageRequest pageRequest, String section, String title) {
        Page<Post> posts;
        if (section != null && !section.equals("")) {
            posts = postRepository.findPostsBySectionAndTitleContaining(section, title, pageRequest);
        }else {
            posts = postRepository.findPostsByTitleContaining(title, pageRequest);
        }
        Page<PostWithUserInfo> postsWithUserInfo = posts.map(post -> getPost(post.getId()));
        PostDTO postDTO = new PostDTO();
        postDTO.setPosts(postsWithUserInfo.getContent());
        postDTO.setEmpty(posts.isEmpty());
        postDTO.setLast(posts.isLast());
        postDTO.setPagesCount(posts.getTotalPages());
        postDTO.setTotalElements(posts.getNumberOfElements());
        return postDTO;
    }
}
