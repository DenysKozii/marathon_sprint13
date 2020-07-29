package com.softserve.sprint13.service;

import com.softserve.sprint13.entity.Marathon;
import com.softserve.sprint13.entity.Sprint;
import com.softserve.sprint13.entity.User;
import com.softserve.sprint13.exception.IncorrectIdException;
import com.softserve.sprint13.repository.MarathonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class MarathonServiceImpl implements MarathonService {

    @Autowired
    MarathonRepository marathonRepository;

    @Override
    public List<Marathon> getAll() {
        List<Marathon> marathons = marathonRepository.findAll();
        return marathons.isEmpty() ? new ArrayList<>() : marathons;
    }

    @Override
    public Marathon getMarathonById(Long id) {
        Optional<Marathon> marathon = marathonRepository.findById(id);
        if (marathon.isPresent())
            return marathon.get();
        else throw new IncorrectIdException("No marathon for given id");
    }


    @Override
    public Marathon createOrUpdateMarathon(Marathon marathon) {
        if (marathon.getId() != null) {
            Optional<Marathon> marathonToUpdate = marathonRepository.findById(marathon.getId());
            if (marathonToUpdate.isPresent()) {
                Marathon newMarathon = marathonToUpdate.get();
                newMarathon.setTitle(marathon.getTitle());
                newMarathon = marathonRepository.save(marathon);
                return newMarathon;
            }
        }
        marathon = marathonRepository.save(marathon);
        return marathon;
    }

    @Override
    public void deleteMarathonById(Long id) {
        Optional<Marathon> marathon = marathonRepository.findById(id);
        if (marathon.isPresent())
            marathonRepository.deleteById(id);
        else throw new IncorrectIdException("No marathon for given id");
    }

}
