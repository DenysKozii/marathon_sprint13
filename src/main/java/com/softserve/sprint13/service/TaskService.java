package com.softserve.sprint13.service;

import com.softserve.sprint13.entity.Sprint;
import com.softserve.sprint13.entity.Task;

public interface TaskService {

    public Task addTaskToSprint(Task task, Sprint sprint);

    public Task getTaskById(Long id);
}
