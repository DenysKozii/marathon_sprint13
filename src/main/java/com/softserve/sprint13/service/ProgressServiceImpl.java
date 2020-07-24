package com.softserve.sprint13.service;

import com.softserve.sprint13.entity.Marathon;
import com.softserve.sprint13.entity.Progress;
import com.softserve.sprint13.entity.Sprint;
import com.softserve.sprint13.entity.Task;
import com.softserve.sprint13.repository.ProgressRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

public class ProgressServiceImpl implements ProgressService {

    @Autowired
    ProgressRepository progressRepository;

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
}
