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

        return null;
    }

    @Override
    public boolean setStatus(Progress.TaskStatus taskStatus, Progress progress) {
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
