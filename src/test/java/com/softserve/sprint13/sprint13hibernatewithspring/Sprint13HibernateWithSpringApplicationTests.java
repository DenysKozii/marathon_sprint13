package com.softserve.sprint13.sprint13hibernatewithspring;

import com.softserve.sprint13.entity.Marathon;
import com.softserve.sprint13.entity.Sprint;
import com.softserve.sprint13.entity.Task;
import com.softserve.sprint13.entity.User;
import com.softserve.sprint13.service.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Sprint13HibernateWithSpringApplicationTests {

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
    }

    @Test
    void contextLoads() {
        Assertions.assertNotNull(userService);
        Assertions.assertNotNull(taskService);
        Assertions.assertNotNull(progressService);
        Assertions.assertNotNull(sprintService);
        Assertions.assertNotNull(marathonService);
    }

    @BeforeTestClass
    private void fillDataBase() {
        Marathon marathon = new Marathon();
        marathon.setTitle("Marathon1");
        marathonService.createOrUpdateMarathon(marathon);

        for (int i = 0; i < 2; i++) {
            User mentor = new User();
            mentor.setEmail("mentoruser" + i + "@dh.com");
            mentor.setFirstName("MentorName" + i);
            mentor.setLastName("MentorSurname" + i);
            mentor.setPassword("qwertyqwerty" + i);
            mentor.setRole(User.Role.MENTOR);
            userService.createOrUpdateUser(mentor);
            userService.addUserToMarathon(mentor, marathon);

            User trainee = new User();
            trainee.setEmail("traineeUser" + i + "@dh.com");
            trainee.setFirstName("TraineeName" + i);
            trainee.setLastName("TraineeSurname" + i);
            trainee.setPassword("qwerty^qwerty" + i);
            trainee.setRole(User.Role.TRAINEE);
            userService.createOrUpdateUser(trainee);
            userService.addUserToMarathon(trainee, marathon);
        }

        for (int i = 0; i < 2; i++) {
            Sprint sprint = new Sprint();
            sprint.setTitle("Sprint" + i);
            sprint.setStartDate(Date.valueOf(LocalDate.now()));
            sprint.setFinishDate(Date.valueOf(LocalDate.now().plusMonths(3 + 3 * i)));
            sprintService.createOrUpdateSprint(sprint);
            sprintService.addSprintToMarathon(sprint, marathon);

            for (int j = 0; j < 2; j++) {
                Task task = new Task();
                task.setTitle("Task" + i + j);
                taskService.createOrUpdateTask(task);
                taskService.addTaskToSprint(task, sprint);

                List<User> trainees = userService.getAllByRole("TRAINEE");
                for (User trainee :
                        trainees) {
                    progressService.addTaskForStudent(task, trainee);
                }
            }
        }
    }

    @Test
    @Order(1)
    public void checkGetAllUsersByRole() {
        List<User> actualMentors = userService.getAllByRole(User.Role.MENTOR.toString());
        List<User> actualTrainees = userService.getAllByRole(User.Role.TRAINEE.toString());

        actualMentors.sort(Comparator.comparing(User::getId));
        actualTrainees.sort(Comparator.comparing(User::getId));

        List<User> expectedMentors = new ArrayList<>();
        List<User> expectedTrainees = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            User mentor = new User();
            mentor.setEmail("mentoruser" + i + "@dh.com");
            mentor.setFirstName("MentorName" + i);
            mentor.setLastName("MentorSurname" + i);
            mentor.setPassword("qwertyqwerty" + i);
            mentor.setRole(User.Role.MENTOR);
            expectedMentors.add(mentor);

            User trainee = new User();
            trainee.setEmail("traineeUser" + i + "@dh.com");
            trainee.setFirstName("TraineeName" + i);
            trainee.setLastName("TraineeSurname" + i);
            trainee.setPassword("qwerty^qwerty" + i);
            trainee.setRole(User.Role.TRAINEE);
            expectedTrainees.add(trainee);
        }

        Assertions.assertEquals(expectedMentors, actualMentors, "checkGetAllMentors()");
        Assertions.assertEquals(expectedTrainees, actualTrainees, "checkGetAllTrainees()");
    }

    @Test
    @Order(2)
    public void checkUpdateUsers() {
        User mentor1 = userService.getUserById(1L);
        User mentor2 = userService.getUserById(3L);
        User trainee1 = userService.getUserById(2L);
        List<User> actual = new ArrayList<>();
        actual.add(mentor1);
        actual.add(mentor2);
        actual.add(trainee1);
        mentor1.setFirstName("ChangedMentor1");
        mentor2.setFirstName("ChangedMentor2");
        trainee1.setFirstName("ChangedTrainee1");
        userService.createOrUpdateUser(mentor1);
        userService.createOrUpdateUser(mentor2);
        userService.createOrUpdateUser(trainee1);

        List<User> expected = new ArrayList<>();

        User user1 = new User();
        user1.setEmail("mentoruser0@dh.com");
        user1.setFirstName("ChangedMentor1");
        user1.setLastName("MentorSurname0");
        user1.setPassword("qwertyqwerty0");
        user1.setRole(User.Role.MENTOR);
        expected.add(user1);

        User user2 = new User();
        user2.setEmail("mentoruser1@dh.com");
        user2.setFirstName("ChangedMentor2");
        user2.setLastName("MentorSurname1");
        user2.setPassword("qwertyqwerty1");
        user2.setRole(User.Role.MENTOR);
        expected.add(user2);

        User user3 = new User();
        user3.setEmail("traineeUser0@dh.com");
        user3.setFirstName("ChangedTrainee1");
        user3.setLastName("TraineeSurname0");
        user3.setPassword("qwerty^qwerty0");
        user3.setRole(User.Role.TRAINEE);
        expected.add(user3);

        Assertions.assertEquals(expected, actual, "checkUpdateUsers()");
    }
}
