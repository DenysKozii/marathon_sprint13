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
import java.util.*;

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
        User expected = new User();
        expected.setRole(User.Role.TRAINEE);
        expected.setEmail("newUser@email.com");
        expected.setFirstName("firstName");
        expected.setLastName("lastName");
        expected.setPassword("pass123123");
        expected.setId(1L);
        doReturn(Optional.of(expected)).when(userRepository).findById(1L);
        User actual = userRepository.findById(1L).get();
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void getAllTest(){
        User user1 = new User();
        User user2 = new User();
        user1.setId(1L);
        user2.setId(2L);
        List<User> expected = new ArrayList<>();
        expected.add(user1);
        expected.add(user2);
        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));
        List<User> actual = userService.getAll();
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void createOrUpdateUserTest() {
        User expected = new User();
        expected.setRole(User.Role.TRAINEE);
        expected.setEmail("newUser@email.com");
        expected.setFirstName("firstName");
        expected.setLastName("lastName");
        expected.setPassword("pass123123");
        expected.setId(1L);
        doReturn(expected).when(userRepository).save(any());
        User actual = userService.createOrUpdateUser(expected);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void findByRoleTest(){
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();
        user1.setId(1L);
        user1.setRole(User.Role.TRAINEE);
        user2.setId(2L);
        user2.setRole(User.Role.TRAINEE);
        user3.setId(3L);
        user3.setRole(User.Role.MENTOR);
        List<User> expected1 = new ArrayList<>();
        expected1.add(user1);
        expected1.add(user2);
        List<User> expected2 = new ArrayList<>();
        expected2.add(user3);

        when(userRepository.findByRole(User.Role.TRAINEE)).thenReturn(Arrays.asList(user1, user2));
        when(userRepository.findByRole(User.Role.MENTOR)).thenReturn(Collections.singletonList(user3));
        List<User> actual1 = userService.findByRole(User.Role.TRAINEE);
        List<User> actual2 = userService.findByRole(User.Role.MENTOR);
        Assertions.assertEquals(expected1, actual1);
        Assertions.assertEquals(expected2, actual2);
    }


    @Test
    public void studentsFromMarathonTest(){
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();
        user1.setId(1L);
        user1.setRole(User.Role.TRAINEE);
        user2.setId(2L);
        user2.setRole(User.Role.TRAINEE);
        user3.setId(3L);
        user3.setRole(User.Role.MENTOR);
        List<User> students = new ArrayList<>();
        students.add(user1);
        students.add(user2);
        Marathon marathon = new Marathon();
        marathon.setUsers(students);
        marathon.setId(1L);
        user1.setMarathons(Arrays.asList(marathon));
        user2.setMarathons(Arrays.asList(marathon));

//        doReturn(students).when(userRepository).findByRole(User.Role.TRAINEE);
        when(userRepository.findByRole(User.Role.TRAINEE)).thenReturn(students);
        List<User> actual1 = userService.studentsFromMarathon(1L);
        Assertions.assertEquals(students, actual1);
    }

    @Test
    public void deleteTest(){
        User user1 = new User();
        user1.setId(100L);
        user1.setRole(User.Role.TRAINEE);
        User actual1 = userService.deleteUser(user1);
        Assertions.assertEquals(user1, actual1);
    }
//    @Test
//    public void addUserToMarathonTest() {
//        User user = new User();
//        user.setRole(User.Role.TRAINEE);
//        user.setEmail("newUser@email.com");
//        user.setFirstName("firstName");
//        user.setLastName("lastName");
//        user.setPassword("pass123123");
//        user.setId(1L);
//        Marathon marathon = new Marathon();
//        marathon.setId(1L);
////        marathon.setUsers(Arrays.asList(user));
//
//        Assertions.assertTrue(userService.addUserToMarathon(user,marathon));
//    }
}
