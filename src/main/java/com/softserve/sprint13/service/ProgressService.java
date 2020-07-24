package com.softserve.sprint13.service;

import com.softserve.sprint13.entity.Progress;
import com.softserve.sprint13.entity.Task;
import org.apache.catalina.User;

import java.util.List;

public interface ProgressService {
    public Progress getProgressById(Long id);
    public Progress addTaskForStudent(Task task, User user);
    public Progress addOrUpdateProgress(Progress progress);
    public boolean setStatus(Progress.TaskStatus taskStatus, Progress progress);
    public List<Progress> allProgressByUserIdAndMarathonId(Long userId, Long marathonId);
    public List<Progress> allProgressByUserIdAndSprintId(Long userId, Long sprintId);
}
