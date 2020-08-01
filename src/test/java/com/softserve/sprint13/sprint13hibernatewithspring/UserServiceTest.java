package com.softserve.sprint13.sprint13hibernatewithspring;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import com.softserve.sprint13.entity.Marathon;
import com.softserve.sprint13.entity.Sprint;
import com.softserve.sprint13.entity.User;
import com.softserve.sprint13.exception.IncorrectIdException;
import com.softserve.sprint13.repository.MarathonRepository;
import com.softserve.sprint13.repository.SprintRepository;
import com.softserve.sprint13.repository.UserRepository;
import com.softserve.sprint13.service.SprintService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.softserve.sprint13.service.UserService;
import com.softserve.sprint13.service.UserServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.test.context.TestExecutionListeners;

import javax.persistence.EntityNotFoundException;

@SpringBootTest
//@RunWith(MockitoJUnitRunner.class)
//@TestExecutionListeners(MockitoTestExecutionListener.class)
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @MockBean
    private UserRepository userRepository;

//    @Before
//    public void setUp(){
//        System.out.println("before");
//        userRepository = Mockito.mock(UserRepository.class);
//        System.out.println(userRepository);
//        userService = new UserServiceImpl(userRepository);
//    }
    @Test
    public void getUserByIdNullTest(){
        long id = 1;
        System.out.println(userRepository);
        doReturn(Optional.empty()).when(userRepository).findById(id);
        Assertions.assertThrows(IncorrectIdException.class,()->userService.getUserById(id));
    }
    @Test
    public void getUserByIdTest(){
        long id = 1;
        User expected = new User();
        expected.setRole(User.Role.TRAINEE);
        expected.setEmail("newUser@email.com");
        expected.setFirstName("firstName");
        expected.setLastName("lastName");
        expected.setPassword("pass123123");
        expected.setId(1L);
        doReturn(Optional.of(expected)).when(userRepository).findById(id);
        User actual = userRepository.findById(1L).get();
        Assertions.assertEquals(expected, actual);
    }

}
