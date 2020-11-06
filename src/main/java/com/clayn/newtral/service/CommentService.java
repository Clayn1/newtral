package com.clayn.newtral.service;

import com.clayn.newtral.dtos.CommentWithUserInfo;
import com.clayn.newtral.model.Comment;
import com.clayn.newtral.repository.CommentRepository;
import com.clayn.newtral.repository.PostRepository;
import com.clayn.newtral.repository.UserPageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService implements ICommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserPageRepository userPageRepository;
    @Autowired
    private PostService postService;
    @Autowired
    private PostRepository postRepository;

    @Override
    public Comment insertComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment updateComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Integer id) {
        commentRepository.deleteById(id);
    }

    @Override
    public Comment getComment(Integer id) {
        return commentRepository.findById(id).get();
    }

    @Override
    public List<CommentWithUserInfo> getPostComments(Integer postId) {
        List<Comment> comments = commentRepository.findCommentsByPost_Id(postId).get();
        List<CommentWithUserInfo> commentWithUserInfos = new ArrayList<>();
        for (Comment comment : comments) {
            CommentWithUserInfo commentWithUserInfo = new CommentWithUserInfo();
            commentWithUserInfo.setComment(comment);
            commentWithUserInfo.setUsername(comment.getUserPage().getUsername());
            commentWithUserInfo.setUserLevel(comment.getUserPage().getLevel());
            commentWithUserInfo.setUserReputation(comment.getUserPage().getReputation());
            commentWithUserInfo.setUserImageURL(comment.getUserPage().getImageURL());
            commentWithUserInfo.setUserDescription(comment.getUserPage().getDescription());
            commentWithUserInfos.add(commentWithUserInfo);
        }
        return commentWithUserInfos;
    }

    @Override
    public Comment insertPostComment(Comment comment, Integer postId, Integer userId) {
        postRepository.findById(postId).ifPresent(post -> {
            comment.setPost(post);
            userPageRepository.findUserPageById(userId).ifPresent(userPage -> {
                comment.setUserPage(userPage);
                commentRepository.save(comment);
            });
        });
        return comment;
    }

    @Override
    public void deletePostComment(Integer commentId, Integer postId) {
        postRepository.findById(postId).ifPresent(post -> post.getComments().remove((int) commentId));
    }
}
