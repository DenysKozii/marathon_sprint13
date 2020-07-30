package com.softserve.sprint13.sprint13hibernatewithspring;


import com.softserve.sprint13.entity.User;
import com.softserve.sprint13.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class StudentRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void newUserTest(){
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
}
