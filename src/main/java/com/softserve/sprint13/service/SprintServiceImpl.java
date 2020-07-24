package com.softserve.sprint13.service;

import com.softserve.sprint13.entity.Marathon;
import com.softserve.sprint13.entity.Sprint;
import com.softserve.sprint13.repository.MarathonRepository;
import com.softserve.sprint13.repository.SprintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;


@Service
public class SprintServiceImpl implements SprintService{
    @Autowired
    SprintRepository sprintRepository;
    @Autowired
    MarathonRepository marathonRepository;
    @Override
    public List<Sprint> getSprintByMarathonId(Long id) {
        Optional<Marathon> marathon = marathonRepository.findById(id);
        if (marathon.isPresent())
            return marathon.get().getSprints();
        else throw new EntityNotFoundException("No marathon for given id");
    }

    @Override
    public boolean addSprintToMarathon(Sprint sprint, Marathon marathon) {
        Sprint sprintEntity = sprintRepository.getOne(sprint.getId());
        Marathon marathonEntity = marathonRepository.getOne(marathon.getId());
        marathonEntity.getSprints().add(sprintEntity);
        return marathonRepository.save(marathonEntity)!=null;
    }


    @Override
    public boolean updateSprint(Sprint sprint) {
        Optional<Sprint> sprintToUpdate = sprintRepository.findById(sprint.getId());
        if(sprintToUpdate.isPresent()){
            Sprint newSprint = sprintToUpdate.get();
            newSprint.setFinishDate(sprint.getFinishDate());
            newSprint.setStartDate(sprint.getStartDate());
            newSprint.setTitle(sprint.getTitle());
            newSprint.setTasks(sprint.getTasks());
            return true;
        }
        return false;
    }

    @Override
    public Sprint getSprintById(Long id) {
        Optional<Sprint> sprint = sprintRepository.findById(id);
        if (sprint.isPresent())
            return sprint.get();
        else throw new EntityNotFoundException("No sprint for given id");
    }
}
