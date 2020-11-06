package com.clayn.newtral.dtos;

import com.clayn.newtral.model.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostWithUserInfo {
    private Post post;
    private int userLevel;
    private int userReputation;
    private String username;
    private String userDescription;
    private String userImageURL;
}
