package com.softserve.sprint13.sprint13hibernatewithspring;

import static org.mockito.Mockito.doReturn;

import com.softserve.sprint13.entity.Sprint;
import com.softserve.sprint13.repository.SprintRepository;
import com.softserve.sprint13.service.SprintService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class SprintServiceTest {

  @Autowired
  SprintService sprintService;

  @MockBean
  SprintRepository sprintRepository;

  @Test
  public void getSprintByIdTest() throws ParseException {
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Date finishData = dateFormat.parse("12/12/2019");
    Date startData = dateFormat.parse("12/12/2020");
    Sprint expected = new Sprint();
    expected.setTitle("sprintNew");
    expected.setFinishDate(finishData);
    expected.setStartDate(startData);
    expected.setId(1L);
    doReturn(Optional.of(expected)).when(sprintRepository).findById(1L);
    Sprint actual = sprintRepository.findById(1L).get();
    Assertions.assertEquals(expected, actual);
  }

  


}
