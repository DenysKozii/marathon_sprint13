package com.softserve.sprint13.service;

import com.softserve.sprint13.entity.Progress;
import com.softserve.sprint13.entity.Task;
import com.softserve.sprint13.entity.User;

import java.util.List;

public interface ProgressService {
    public Progress getProgressById(Long id);
    public boolean addTaskForStudent(Task task, User user);
    public Progress createOrUpdateProgress(Progress progress);
    public boolean setStatus(Progress.TaskStatus taskStatus, Progress progress);
    public List<Progress> allProgressByUserIdAndMarathonId(Long userId, Long marathonId);
    public List<Progress> allProgressByUserIdAndSprintId(Long userId, Long sprintId);
    public boolean addProgressToUser(Progress progress, com.softserve.sprint13.entity.User user);

    List<Progress> getAll();

    List<Progress> getAllProgressesOfTask(Task task);

    public Progress deleteProgress(Progress progress);
}
