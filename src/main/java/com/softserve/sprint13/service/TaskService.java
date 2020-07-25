package com.softserve.sprint13.service;

import com.softserve.sprint13.entity.Sprint;
import com.softserve.sprint13.entity.Task;

public interface TaskService {

    public boolean addTaskToSprint(Task task, Sprint sprint);

    public Task getTaskById(Long id);

    public Task deleteTask(Task task);

    public Task createOrUpdateTask(Task task);
}
