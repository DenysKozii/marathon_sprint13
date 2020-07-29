package com.softserve.sprint13.controller;

import com.softserve.sprint13.entity.Marathon;
import com.softserve.sprint13.entity.User;
import com.softserve.sprint13.service.MarathonService;
import com.softserve.sprint13.service.UserService;
import lombok.Data;
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

    @Autowired
    private UserService userService;

    @Autowired
    private MarathonService marathonService;

    @GetMapping({ "/students"})
    public String getAllStudents(Model model) {
        List<User> students = userService.getAllByRole("TRAINEE");
        model.addAttribute("students", students);
        model.addAttribute("add", false);
        return "students";
    }

    @GetMapping("/students/{marathon_id}")
    public String studentsFromMarathon(@PathVariable("marathon_id") Long marathon_id,Model model) {
        List<User> students = userService.studentsFromMarathon(marathon_id);
        model.addAttribute("students", students);
        model.addAttribute("add", false);
        return "students";
    }

    @GetMapping("/students/register")
    public String registerStudentForm(Model model) {
        return "register";
    }

    @PostMapping("/students/register")
    public String registerStudent(@ModelAttribute("user") User user) {
        userService.createOrUpdateUser(user);
        return "redirect:/students";
    }

    @GetMapping("/students/{marathon_id}/delete/{student_id}")
    public String deleteStudentByIdFromMarathon(@PathVariable("marathon_id") Long marathon_id,
                                 @PathVariable("student_id") Long student_id) {

        userService.deleteUserByIdFromMarathon(student_id,marathon_id);
        //{marathon_id}
        return "redirect:/students";
    }
    @GetMapping("/students/delete/{student_id}")
    public String deleteStudents(@PathVariable("student_id") Long student_id) {

        userService.deleteUser(userService.getUserById(student_id));
        //{marathon_id}
        return "redirect:/students";
    }

    @GetMapping({"/students/edit/{student_id}","/students/{marathon_id}/edit/{student_id}"})
    public String editStudentForm(@PathVariable("student_id") Long student_id, Model model) {
        model.addAttribute("user",userService.getUserById(student_id));
        return "editUser";
    }

    @PostMapping("/students/edit")
    public String editStudent(@ModelAttribute("user") User user) {
        userService.createOrUpdateUser(user);
        //{marathon_id}
        return "redirect:/students";
    }


    @GetMapping("/students/{marathon_id}/add/{student_id}")
    public String addStudents(@PathVariable("marathon_id") Long marathon_id,
                               @PathVariable("student_id") Long student_id) {
        User student = userService.getUserById(student_id);
        Marathon marathon = marathonService.getMarathonById(marathon_id);
        userService.addUserToMarathon(student, marathon);
        //{marathon_id}
        return "redirect:/students";
    }
    @GetMapping("/students/{marathon_id}/add")
    public String findStudentForAdd(@PathVariable("marathon_id") Long marathon_id, Model model) {
        List<User> students = userService.studentsNotFromMarathon(marathon_id);
        model.addAttribute("students",students);
        model.addAttribute("marathon_id", marathon_id);
        model.addAttribute("add", true);
        //{marathon_id}
        return "students";
    }


    @GetMapping("/students/{student_id}/addMarathon")
    public String findMarathonForAdd(@PathVariable("student_id") Long student_id, Model model) {
        User student = userService.getUserById(student_id);
        List<Marathon> marathons = userService.marathonsWithoutStudent(student_id);
        model.addAttribute("marathons", marathons);
        model.addAttribute("add", true);
        //{marathon_id}
        return "marathons";
    }
    @GetMapping("/students/{student_id}/addMarathon/{marathon_id}")
    public String addMarathon(@PathVariable("marathon_id") Long marathon_id,
                              @PathVariable("student_id") Long student_id, Model model) {
        User student = userService.getUserById(student_id);
        Marathon marathon = marathonService.getMarathonById(marathon_id);
        userService.addUserToMarathon(student, marathon);
        model.addAttribute("add", false);
        return "students";
    }

    @GetMapping("/students/studentInfo/{student_id}")
    public String studentInfo(@PathVariable("student_id") Long student_id, Model model) {
        model.addAttribute("user",userService.getUserById(student_id));
        return "studentInfo";
    }
}
