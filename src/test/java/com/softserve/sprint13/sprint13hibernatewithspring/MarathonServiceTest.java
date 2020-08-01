package com.softserve.sprint13.sprint13hibernatewithspring;

import com.softserve.sprint13.entity.Marathon;
import com.softserve.sprint13.exception.IncorrectIdException;
import com.softserve.sprint13.repository.MarathonRepository;
import com.softserve.sprint13.service.MarathonService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@SpringBootTest
public class MarathonServiceTest {

  @Autowired
  MarathonService marathonService;

  @MockBean
  MarathonRepository marathonRepository;



  @Test
  public void getMarathonByIdTest() {
    Marathon expected = new Marathon();
    expected.setId(2L);
    expected.setTitle("newMarathon");
    doReturn(Optional.of(expected)).when(marathonRepository).findById(2L);
    Marathon actual = marathonRepository.findById(2L).get();
    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void getAllTest() {
    Marathon marathon1 = new Marathon();
    Marathon marathon2 = new Marathon();
    marathon1.setId(1L);
    marathon1.setTitle("marathon1");
    marathon2.setId(2L);
    marathon2.setTitle("marathon2");
    List<Marathon> expected = new ArrayList<>();
    expected.add(marathon1);
    expected.add(marathon2);
    when(marathonRepository.findAll()).thenReturn(Arrays.asList(marathon1, marathon2));
    List<Marathon> actual = marathonService.getAll();
    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void createOrUpdateMarathonTest() {
    Marathon expected = new Marathon();
    expected.setTitle("marathonNew");
    expected.setId(1L);
    doReturn(expected).when(marathonRepository).save(any());
    Marathon actual = marathonService.createOrUpdateMarathon(expected);
    Assertions.assertEquals(expected, actual);
  }
}
