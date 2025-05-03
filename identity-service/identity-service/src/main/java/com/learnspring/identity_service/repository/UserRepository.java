package com.learnspring.identity_service.repository;

import com.learnspring.identity_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByUsername(String username);  //Spring will automatically generate a new query to fit this new method we declared
}
