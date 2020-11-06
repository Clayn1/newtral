package com.clayn.newtral.repository;

import com.clayn.newtral.model.Comment;
import com.clayn.newtral.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    Optional<List<Comment>> findCommentsByPost(Post post);
    Optional<List<Comment>> findCommentsByPost_Id(int post_id);
}
