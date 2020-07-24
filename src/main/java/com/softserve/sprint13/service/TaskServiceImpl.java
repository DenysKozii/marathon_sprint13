package com.softserve.sprint13.service;

import com.softserve.sprint13.entity.Sprint;
import com.softserve.sprint13.entity.Task;
import com.softserve.sprint13.repository.SprintRepository;
import com.softserve.sprint13.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Override
    public Task addTaskToSprint(Task task, Sprint sprint) {
        return sprint.getTasks().add(task)? task : null;
    }

    @Override
    public Task getTaskById(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent())
            return task.get();
        else throw new EntityNotFoundException("No task for given id");
    }
}
