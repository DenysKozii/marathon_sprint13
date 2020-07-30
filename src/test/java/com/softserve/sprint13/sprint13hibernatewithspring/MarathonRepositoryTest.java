package com.softserve.sprint13.sprint13hibernatewithspring;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

import com.softserve.sprint13.entity.Marathon;
import com.softserve.sprint13.repository.MarathonRepository;
import com.softserve.sprint13.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class MarathonRepositoryTest {

  @Autowired
  private MarathonRepository marathonRepository;

  @Autowired
  private UserService userService;

  @Autowired
  private TestEntityManager entityManager;

  @Test
  public void newMarathonTest(){
//    System.out.println("Marathon Test");

//    Marathon marathon = new Marathon();
//    marathon.setTitle("newMarathon");
//    marathon.setId((long) 2);
//    marathonRepository.save(marathon);

//    Marathon actual = marathonRepository.findById(Long.valueOf(2)).get();

//    Assertions.assertEquals("firstName", actual.getFirstname());

//    Assertions.assertEquals("newMarathon", actual.getTitle());

  }

}
