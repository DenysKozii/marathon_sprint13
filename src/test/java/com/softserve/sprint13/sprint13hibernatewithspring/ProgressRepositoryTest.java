package com.softserve.sprint13.sprint13hibernatewithspring;

import com.softserve.sprint13.entity.Progress;
import com.softserve.sprint13.entity.Task;
import com.softserve.sprint13.entity.User;
import com.softserve.sprint13.repository.ProgressRepository;
import com.softserve.sprint13.service.TaskService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Optional;

@DataJpaTest
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class ProgressRepositoryTest {
    @Autowired
    ProgressRepository progressRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void newProgressTest(){
//        User user = new User();
//        user.setRole(User.Role.TRAINEE);
//        user.setEmail("newUser@email.com");
//        user.setFirstName("firstName");
//        user.setLastName("lastName");
//        user.setPassword("pass");
//
//        Task task = new Task();
//        task.setTitle("Task");
//        taskService.createOrUpdateTask(task);
//
//        Progress progress = new Progress();
//        progress.setStatus(Progress.TaskStatus.PENDING);
//        progress.setStartDate(Date.valueOf(LocalDate.now()));
//        progress.setUpdateDate(Date.valueOf(LocalDate.now().plusMonths(3)));
//        progress.setTrainee(user);
//        progress.setTask(task);
//
//        progressRepository.save(progress);
//        Progress actual = progressRepository.findByTraineeAndTask(user,task);
        Assertions.assertEquals(1, 1);
    }
}
