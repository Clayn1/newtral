package com.clayn.newtral.controller;

import com.clayn.newtral.dtos.CommentWithUserInfo;
import com.clayn.newtral.model.Comment;
import com.clayn.newtral.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private ICommentService commentService;

    @RequestMapping(path = "/{postId}", method = RequestMethod.GET)
    public List<CommentWithUserInfo> getComment(@PathVariable Integer postId){
        return commentService.getPostComments(postId);
    }
    @RequestMapping(path = "/{postId}/user/{userId}", method = RequestMethod.POST)
    public Comment sendComment(@RequestBody Comment comment, @PathVariable Integer postId, @PathVariable Integer userId){
        return commentService.insertPostComment(comment, postId, userId);
    }
    @RequestMapping(path = "/{postId}", method = RequestMethod.DELETE)
    public void deletePostComment(@RequestParam Integer commentId, @PathVariable Integer postId){
        commentService.deletePostComment(commentId, postId);
    }
}
