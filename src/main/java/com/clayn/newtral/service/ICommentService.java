package com.clayn.newtral.service;

import com.clayn.newtral.dtos.CommentWithUserInfo;
import com.clayn.newtral.model.Comment;

import java.util.List;

public interface ICommentService {
    Comment insertComment(Comment comment);

    Comment updateComment(Comment comment);

    void deleteComment(Integer id);

    Comment getComment(Integer id);

    List<CommentWithUserInfo> getPostComments(Integer postId);

    Comment insertPostComment(Comment comment, Integer postId, Integer userId);

    void deletePostComment(Integer commentId, Integer postId);
}
