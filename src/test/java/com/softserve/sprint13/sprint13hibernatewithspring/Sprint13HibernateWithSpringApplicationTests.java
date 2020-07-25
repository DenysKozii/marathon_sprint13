package com.softserve.sprint13.sprint13hibernatewithspring;

<<<<<<< HEAD
import com.softserve.sprint13.entity.User;
import com.softserve.sprint13.service.*;
=======
import com.softserve.sprint13.entity.Progress;
import com.softserve.sprint13.service.ProgressService;
>>>>>>> 7223f4bcab9a5a83b748ca06af78258c99ffa2a1
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
=======
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;
>>>>>>> 7223f4bcab9a5a83b748ca06af78258c99ffa2a1

@SpringBootTest
class Sprint13HibernateWithSpringApplicationTests {

<<<<<<< HEAD
    @Autowired
    UserService userService;
    @Autowired
    TaskService taskService;
    @Autowired
    ProgressService progressService;
    @Autowired
    SprintService sprintService;
    @Autowired
    MarathonService marathonService;

    @Autowired
    public Sprint13HibernateWithSpringApplicationTests(UserService userService, TaskService taskService,
                                                       ProgressService progressService, SprintService sprintService,
                                                       MarathonService marathonService) {
        this.userService = userService;
        this.taskService = taskService;
        this.progressService = progressService;
        this.sprintService = sprintService;
        this.marathonService = marathonService;
        fillDataBase();
    }

    @Test
    void contextLoads() {
        Assertions.assertNotNull(userService);
        Assertions.assertNotNull(taskService);
        Assertions.assertNotNull(progressService);
        Assertions.assertNotNull(sprintService);
        Assertions.assertNotNull(marathonService);
    }

    private void fillDataBase() {
        for (User user : userService.getAll()) {
            userService.deleteUser(user);
        }
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setEmail("mentoruser" + i + "@dh.com");
            user.setFirstName("MentorName" + i);
            user.setLastName("MentorSurname" + i);
            user.setPassword("qwertyqwerty" + i);
            user.setRole(User.Role.MENTOR);
            userService.createOrUpdateUser(user);
        }
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setEmail("traineeUser" + i + "@dh.com");
            user.setFirstName("TraineeName" + i);
            user.setLastName("TraineeSurname" + i);
            user.setPassword("qwerty^qwerty" + i);
            user.setRole(User.Role.TRAINEE);
            userService.createOrUpdateUser(user);
        }
    }

    @Test
    public void checkUpdateUsers() {
        User mentor4 = userService.getUserById(4L);
        User mentor7 = userService.getUserById(7L);
        User trainee1 = userService.getUserById(11L);
        List<User> actual = new ArrayList<>();
        actual.add(mentor4);
        actual.add(mentor7);
        actual.add(trainee1);
        mentor4.setFirstName("ChangedMentor4");
        mentor7.setFirstName("ChangedMentor7");
        trainee1.setFirstName("ChangedTrainee1");
        userService.createOrUpdateUser(mentor4);
        userService.createOrUpdateUser(mentor7);
        userService.createOrUpdateUser(trainee1);

        List<User> expected = new ArrayList<>();

        User user1 = new User();
        user1.setEmail("mentoruser3@dh.com");
        user1.setFirstName("ChangedMentor4");
        user1.setLastName("MentorSurname3");
        user1.setPassword("qwertyqwerty3");
        user1.setRole(User.Role.MENTOR);
        expected.add(user1);

        User user2 = new User();
        user2.setEmail("mentoruser6@dh.com");
        user2.setFirstName("ChangedMentor7");
        user2.setLastName("MentorSurname6");
        user2.setPassword("qwertyqwerty6");
        user2.setRole(User.Role.MENTOR);
        expected.add(user2);

        User user3 = new User();
        user3.setEmail("traineeUser0@dh.com");
        user3.setFirstName("ChangedTrainee1");
        user3.setLastName("TraineeSurname0");
        user3.setPassword("qwertyqwerty0");
        user3.setRole(User.Role.TRAINEE);
        expected.add(user3);

        Assertions.assertEquals(expected, actual, "checkUpdateUsers()");
    }

    @Test
    public void checkGetAllUsersByRole() {
        List<User> actualMentors = userService.getAllByRole(User.Role.MENTOR.toString());
        List<User> actualTrainees = userService.getAllByRole(User.Role.TRAINEE.toString());

        actualMentors.sort(Comparator.comparing(User::getId));
        actualTrainees.sort(Comparator.comparing(User::getId));

        List<User> expectedMentors = new ArrayList<>();
        List<User> expectedTrainees = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setEmail("mentoruser" + i + "@dh.com");
            user.setFirstName("MentorName" + i);
            user.setLastName("MentorSurname" + i);
            user.setPassword("qwertyqwerty" + i);
            user.setRole(User.Role.MENTOR);
            expectedMentors.add(user);
        }
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setEmail("traineeUser" + i + "@dh.com");
            user.setFirstName("TraineeName" + i);
            user.setLastName("TraineeSurname" + i);
            user.setPassword("qwerty^qwerty" + i);
            user.setRole(User.Role.TRAINEE);
            expectedTrainees.add(user);
        }


        Assertions.assertEquals(expectedMentors, actualMentors, "checkGetAllMentors()");
        Assertions.assertEquals(expectedTrainees, actualTrainees, "checkGetAllTrainees()");
    }
=======

    @Test
    void contextLoads() {

    }


>>>>>>> 7223f4bcab9a5a83b748ca06af78258c99ffa2a1
}
