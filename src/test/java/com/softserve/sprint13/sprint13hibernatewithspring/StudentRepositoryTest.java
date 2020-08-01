package com.softserve.sprint13.sprint13hibernatewithspring;


import com.softserve.sprint13.entity.User;
import com.softserve.sprint13.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Arrays;
import java.util.List;

@DataJpaTest
public class StudentRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void findUserByEmailTest(){
        User user = new User();
        user.setRole(User.Role.TRAINEE);
        user.setEmail("newUser@email.com");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setPassword("pass123123");
        userRepository.save(user);
        User actual = userRepository.findUserByEmail("newUser@email.com");
        Assertions.assertEquals("firstName", actual.getFirstName());
    }
    @Test
    public void findUserByRoleTest(){
        User user = new User();
        user.setRole(User.Role.TRAINEE);
        user.setEmail("newUser@email.com");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setPassword("pass123123");
        userRepository.save(user);
        User user2 = new User();
        user2.setRole(User.Role.TRAINEE);
        user2.setEmail("newUser2@email.com");
        user2.setFirstName("firstName2");
        user2.setLastName("lastName2");
        user2.setPassword("pass1231232");
        userRepository.save(user2);
        List<User> actual = userRepository.findByRole(User.Role.TRAINEE);
        Assertions.assertEquals(Arrays.asList(user,user2), actual);
    }
}
