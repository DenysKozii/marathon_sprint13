package com.softserve.sprint13.sprint13hibernatewithspring;


import com.softserve.sprint13.entity.User;
import com.softserve.sprint13.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class StudentTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Test
    public void getAllStudentsTest() throws Exception {
        List<User> expected = userService.findByRole(User.Role.TRAINEE);

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
}
