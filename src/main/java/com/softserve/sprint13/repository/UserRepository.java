package com.softserve.sprint13.repository;

import com.softserve.sprint13.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmail(String email);
    List<User> findByRole(User.Role role);

}
