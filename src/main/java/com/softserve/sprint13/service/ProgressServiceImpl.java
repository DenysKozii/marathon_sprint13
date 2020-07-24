package com.softserve.sprint13.service;

import com.softserve.sprint13.entity.Progress;
import com.softserve.sprint13.entity.Task;
import com.softserve.sprint13.repository.ProgressRepository;
import com.softserve.sprint13.repository.UserRepository;
import com.softserve.sprint13.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

public class ProgressServiceImpl implements ProgressService {

    @Autowired
    ProgressRepository progressRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Progress getProgressById(Long id) {
        Optional<Progress> progress = progressRepository.findById(id);
        if (progress.isPresent())
            return progress.get();
        else throw new EntityNotFoundException("No progress for given id");
    }

    @Override
    public Progress addTaskForStudent(Task task, User user) {
        return null;
    }

    @Override
    public Progress addOrUpdateProgress(Progress progress) {
        if (progress.getId() != null) {
            Optional<Progress> progressToUpdate = progressRepository.findById(progress.getId());
            if (progressToUpdate.isPresent()) {
                Progress newProgress = progressToUpdate.get();
                newProgress.setStatus(progress.getStatus());
                newProgress.setStartDate(progress.getStartDate());
                newProgress.setTask(progress.getTask());
                newProgress.setTrainee(progress.getTrainee());
                newProgress.setUpdateDate(progress.getUpdateDate());
                return newProgress;
            }
        }
        progress = progressRepository.save(progress);
        return progress;
    }

    @Override
    public boolean setStatus(Progress.TaskStatus taskStatus, Progress progress) {
        Optional<Progress> progressEntity = progressRepository.findById(progress.getId());
        if(progressEntity.isPresent()){
            progress.setStatus(taskStatus);
            return true;
        }
        return false;
    }

    @Override
    public List<Progress> allProgressByUserIdAndMarathonId(Long userId, Long marathonId) {
        return null;
    }

    @Override
    public List<Progress> allProgressByUserIdAndSprintId(Long userId, Long sprintId) {
        return null;
    }

    @Override
    public boolean addProgressToUser(Progress progress, User user) {
        com.softserve.sprint13.entity.User userEntity = userRepository.getOne(user.getId());
        Progress progressEntity = progressRepository.getOne(progress.getId());
        userEntity.getProgressList().add(progressEntity);
        return userRepository.save(userEntity) != null;
    }
}
