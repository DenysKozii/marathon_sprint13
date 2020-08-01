package com.softserve.sprint13.sprint13hibernatewithspring;

import com.softserve.sprint13.entity.Marathon;
import com.softserve.sprint13.service.MarathonService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class MarathonTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private MarathonService marathonService;

  @Test
  public void getAllMarathonTest() throws Exception {
    List<Marathon> expected = marathonService.getAll();

    mockMvc.perform(MockMvcRequestBuilders.get("/marathons"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.model().attributeExists("marathons"))
        .andExpect(MockMvcResultMatchers.model().attribute("marathons", expected));

    mockMvc.perform(MockMvcRequestBuilders.get("/"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.model().attributeExists("marathons"))
        .andExpect(MockMvcResultMatchers.model().attribute("marathons", expected));
  }

  @Test
  public void createMarathonTest() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/marathons/create")
        .param("title","newMarathon"))
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
  }


}
