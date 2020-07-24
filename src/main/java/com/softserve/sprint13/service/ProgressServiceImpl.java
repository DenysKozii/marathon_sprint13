package com.softserve.sprint13.service;

import com.softserve.sprint13.entity.Progress;
import com.softserve.sprint13.entity.Task;
import org.apache.catalina.User;

import java.util.List;

public class ProgressServiceImpl implements ProgressService {
    @Override
    public Progress getProgressById(Long id) {
        return null;
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
