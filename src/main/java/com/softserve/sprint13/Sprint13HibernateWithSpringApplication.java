package com.softserve.sprint13;

import com.softserve.sprint13.entity.Marathon;
import com.softserve.sprint13.entity.Sprint;
import com.softserve.sprint13.entity.Task;
import com.softserve.sprint13.entity.User;
import com.softserve.sprint13.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.validation.ConstraintViolationException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class Sprint13HibernateWithSpringApplication implements CommandLineRunner {

    @Autowired
    UserService userService;
    @Autowired
    TaskService taskService;
    @Autowired
    MarathonService marathonService;
    @Autowired
    SprintService sprintService;
    @Autowired
    ProgressService progressService;

    public static void main(String[] args) {
        SpringApplication.run(Sprint13HibernateWithSpringApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Running Spring Boot Application");
        fillDataBase();
    }

    private void fillDataBase() {
        try {
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
        } catch (ConstraintViolationException e) {
            System.out.println(e.getMessage());
        }
    }
}
