package com.softserve.sprint13.sprint13hibernatewithspring;


import com.softserve.sprint13.entity.Marathon;
import com.softserve.sprint13.entity.Sprint;
import com.softserve.sprint13.entity.Task;
import com.softserve.sprint13.entity.User;
import com.softserve.sprint13.service.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.ConstraintViolationException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class StudentTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;
    @Autowired
    private MarathonService marathonService;
    @Autowired
    private SprintService sprintService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private ProgressService progressService;

    private void fillDataBase() {
        try {
            Marathon marathon = new Marathon();
            marathon.setTitle("Marathon1");
            marathonService.createOrUpdateMarathon(marathon);

            for (int i = 0; i < 4; i++) {
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

            for (int i = 0; i < 3; i++) {
                Sprint sprint = new Sprint();
                sprint.setTitle("Sprint" + i);
                sprint.setStartDate(Date.valueOf(LocalDate.now()));
                sprint.setFinishDate(Date.valueOf(LocalDate.now().plusMonths(3 + 3 * i)));
                sprintService.createOrUpdateSprint(sprint);
                sprintService.addSprintToMarathon(sprint, marathon);

                for (int j = 0; j < 3; j++) {
                    Task task = new Task();
                    task.setTitle("Task" + i + j);
                    taskService.createOrUpdateTask(task);
                    taskService.addTaskToSprint(task, sprint);

                    List<User> trainees = userService.findByRole(User.Role.TRAINEE);
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

    @Test
    public void getAllStudentsTest() throws Exception {
//        fillDataBase();
        List<User> expected = userService.findByRole(User.Role.TRAINEE);
        System.out.println(expected);
        mockMvc.perform(MockMvcRequestBuilders.get("/students"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("students"))
                .andExpect(MockMvcResultMatchers.model().attribute("students", expected));
    }

    @Test
    public void testAddStudent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/students/register")
                .param("email", "test@email.com")
                .param("firstName", "fName")
                .param("password", "123123123")
                .param("lastName", "lName")
                .param("role", "TRAINEE"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    public void studentsFromMarathonTest() throws Exception {
//        fillDataBase();
        List<User> expected = userService.studentsFromMarathon(1L);
        System.out.println(expected);
        mockMvc.perform(MockMvcRequestBuilders.get("/students/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("students"))
                .andExpect(MockMvcResultMatchers.model().attribute("students", expected));
    }
    @Test
    public void registerStudentFormGetTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/students/register"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void deleteStudentByIdFromMarathonTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/students/1/delete/1"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    public void editStudentFormTest() throws Exception {
        User expected = userService.getUserById(1L);
        mockMvc.perform(MockMvcRequestBuilders.get("/students/edit/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("user"))
                .andExpect(MockMvcResultMatchers.model().attribute("user", expected));
    }


    @Test
    public void findMarathonForAddTest() throws Exception {
        List<Marathon> expected = userService.marathonsWithoutStudent(2L);
        mockMvc.perform(MockMvcRequestBuilders.get("/students/2/addMarathon"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("marathons"))
                .andExpect(MockMvcResultMatchers.model().attribute("marathons", expected));
    }

    @Test
    public void studentInfoTest() throws Exception {
        fillDataBase();
        User expected = userService.getUserById(1L);
        mockMvc.perform(MockMvcRequestBuilders.get("/students/studentInfo/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("user"))
                .andExpect(MockMvcResultMatchers.model().attribute("user", expected));
    }

}
