package com.softserve.sprint13.controller;

import com.softserve.sprint13.entity.Marathon;
import com.softserve.sprint13.entity.User;
import com.softserve.sprint13.service.MarathonService;
import com.softserve.sprint13.service.UserService;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@Data
public class StudentController {

    Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private MarathonService marathonService;


    @GetMapping({ "/students"})
    public String getAllStudents(Model model) {
        logger.info("getAllStudents");
        List<User> students = userService.findByRole(User.Role.TRAINEE);
        model.addAttribute("students", students);
        logger.info("students " + students);
        model.addAttribute("add", false);
        return "students";
    }

    @GetMapping("/students/{marathon_id}")
    public String studentsFromMarathon(@PathVariable("marathon_id") Long marathon_id,Model model) {
        logger.info("studentsFromMarathon");
        List<User> students = userService.studentsFromMarathon(marathon_id);
        model.addAttribute("students", students);
        logger.info("students " + students);
        model.addAttribute("add", false);
        return "students";
    }

    @GetMapping("/students/register")
    public String registerStudentForm(Model model) {
        logger.info("registerStudentForm");
        return "register";
    }

    @PostMapping("/students/register")
    public String registerStudent(@ModelAttribute("user") User user) {
        logger.info("registerStudent");
        userService.createOrUpdateUser(user);
        return "redirect:/students";
    }

    @GetMapping("/students/{marathon_id}/delete/{student_id}")
    public String deleteStudentByIdFromMarathon(@PathVariable("marathon_id") Long marathon_id,
                                 @PathVariable("student_id") Long student_id) {
        logger.info("deleteStudentByIdFromMarathon");
        userService.deleteUserByIdFromMarathon(student_id,marathon_id);
        //{marathon_id}
        return "redirect:/students";
    }
    @GetMapping("/students/delete/{student_id}")
    public String deleteStudents(@PathVariable("student_id") Long student_id) {
        logger.info("deleteStudents");
        userService.deleteUser(userService.getUserById(student_id));
        //{marathon_id}
        return "redirect:/students";
    }

    @GetMapping({"/students/edit/{student_id}","/students/{marathon_id}/edit/{student_id}"})
    public String editStudentForm(@PathVariable("student_id") Long student_id, Model model) {
        logger.info("editStudentForm");
        model.addAttribute("user",userService.getUserById(student_id));
        logger.info("user " + userService.getUserById(student_id));
        return "editUser";
    }

    @PostMapping("/students/edit")
    public String editStudent(@ModelAttribute("user") User user) {
        logger.info("editStudent");
        userService.createOrUpdateUser(user);
        //{marathon_id}
        return "redirect:/students";
    }


    @GetMapping("/students/{marathon_id}/add/{student_id}")
    public String addStudents(@PathVariable("marathon_id") Long marathon_id,
                               @PathVariable("student_id") Long student_id) {
        logger.info("addStudents");
        User student = userService.getUserById(student_id);
        Marathon marathon = marathonService.getMarathonById(marathon_id);
        userService.addUserToMarathon(student, marathon);
        //{marathon_id}
        return "redirect:/students/{marathon_id}/add";
    }
    @GetMapping("/students/{marathon_id}/add")
    public String findStudentForAdd(@PathVariable("marathon_id") Long marathon_id, Model model) {
        logger.info("findStudentForAdd");
        List<User> students = userService.studentsNotFromMarathon(marathon_id);
        model.addAttribute("students",students);
        logger.info("students " + students);
        model.addAttribute("marathon_id", marathon_id);
        model.addAttribute("add", true);
        //{marathon_id}
        return "students";
    }


    @GetMapping("/students/{student_id}/addMarathon")
    public String findMarathonForAdd(@PathVariable("student_id") Long student_id, Model model) {
        logger.info("findMarathonForAdd");
        System.out.println("findMarathonForAdd");
        List<Marathon> marathons = userService.marathonsWithoutStudent(student_id);
        model.addAttribute("marathons", marathons);
        logger.info("marathons " + marathons);
        model.addAttribute("add", true);
        return "marathons";
    }
    @GetMapping("/students/{student_id}/addMarathon/{marathon_id}")
    public String addMarathon(@PathVariable("marathon_id") Long marathon_id, @PathVariable("student_id") Long student_id, Model model) {
        logger.info("addMarathon");
        System.out.println("addMarathon");

        User student = userService.getUserById(student_id);
        Marathon marathon = marathonService.getMarathonById(marathon_id);
        userService.addUserToMarathon(student, marathon);
        List<User> students = userService.findByRole(User.Role.TRAINEE);
        model.addAttribute("students",students);
        model.addAttribute("add", false);
        logger.info("students " + students);
        return "redirect:/students/{student_id}/addMarathon";
    }

    @GetMapping("/students/studentInfo/{student_id}")
    public String studentInfo(@PathVariable("student_id") Long student_id, Model model) {
        logger.info("studentInfo");
        model.addAttribute("user",userService.getUserById(student_id));
        return "studentInfo";
    }
}
