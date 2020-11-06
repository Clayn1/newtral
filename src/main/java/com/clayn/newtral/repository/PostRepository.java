package com.clayn.newtral.repository;

import com.clayn.newtral.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    Page<Post> findPostsBySectionAndTitleContaining(String section, String title, Pageable pageable);
    Page<Post> findPostsByTitleContaining(String title, Pageable pageable);
}
