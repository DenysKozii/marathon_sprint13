package com.softserve.sprint13.service;

import com.softserve.sprint13.entity.Marathon;
import com.softserve.sprint13.repository.MarathonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
//@Transactional
public class MarathonServiceImpl implements MarathonService{

    @Autowired
    MarathonRepository marathonRepository;

    @Override
    public List<Marathon> getAll() {
        return marathonRepository.findAll();
    }

    @Override
    public Marathon getMarathonById(Long id) {
        return marathonRepository.getOne(id);
    }

    @Override
    public Marathon createOrUpdate(Marathon marathon) {
        return null;
    }

//    @Override
//    public Marathon createOrUpdate(Marathon marathon) {
//        if(marathon.getId() != null){
//            // change to long
//            Optional<Marathon> marathonToUpdate = marathonRepository.findById(marathon.getId());
//            if(marathonToUpdate.isPresent()){
//                Marathon newMarathon = marathonToUpdate.get();
//                newMarathon.setTitle(marathon.getTitle());
//                return newMarathon;
//            }
//        }
//        marathon = marathonRepository.save(marathon);
//        return marathon;
//    }

    @Override
    public void deleteMarathonById(Long id) {
        marathonRepository.deleteById(id);
    }
}
