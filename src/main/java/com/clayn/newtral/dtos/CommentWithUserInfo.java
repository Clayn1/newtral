package com.clayn.newtral.dtos;

import com.clayn.newtral.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentWithUserInfo {
    private Comment comment;
    private int userLevel;
    private int userReputation;
    private String username;
    private String userDescription;
    private String userImageURL;
}
