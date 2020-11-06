package com.clayn.newtral.repository;

import com.clayn.newtral.model.UserPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserPageRepository extends JpaRepository<UserPage, Integer> {
    Optional<UserPage> findUserPageByUsername(String username);
    Page<UserPage> findUserPagesByUsernameContaining(String username, Pageable pageable);
    Optional<UserPage> findUserPageById(int id);
    UserPage findUserPageByUserUsername(String user_username);
}
