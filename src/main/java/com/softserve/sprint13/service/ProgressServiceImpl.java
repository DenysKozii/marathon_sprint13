package com.softserve.sprint13.service;

import com.softserve.sprint13.entity.Progress;
import com.softserve.sprint13.entity.Sprint;
import com.softserve.sprint13.entity.Task;
import com.softserve.sprint13.entity.User;
import com.softserve.sprint13.repository.ProgressRepository;
import com.softserve.sprint13.repository.SprintRepository;
import com.softserve.sprint13.repository.TaskRepository;
import com.softserve.sprint13.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class ProgressServiceImpl implements ProgressService {

    @Autowired
    ProgressRepository progressRepository;
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    SprintRepository sprintRepository;
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
//        if (user.getRole() == TRAINEE){
//            Task taskEntity = taskRepository.getOne(task.getId());
//            User userEntity = .getOne(marathon.getId());
//            marathonEntity.getSprints().add(sprintEntity);
//            return marathonRepository.save(marathonEntity)!=null;
//        }
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
                newProgress = progressRepository.save(progress);
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
        return progressRepository.allProgressByUserIdAndMarathonId(userId,marathonId);
    }

    @Override
    public List<Progress> allProgressByUserIdAndSprintId(Long userId, Long sprintId) {
//        Sprint sprintEntity = sprintRepository.getOne(sprint.getId());
//        Marathon marathonEntity = marathonRepository.getOne(marathon.getId());
//        marathonEntity.getSprints().add(sprintEntity);
//        return marathonRepository.save(marathonEntity)!=null;
//        User user = userRepository.getOne(userId);
//        Sprint sprint = sprintRepository.getOne(sprintId);
//        List<Progress> progressesInUser = user.getProgressList();
//        List<Task> tasksInSprint = sprint.getTasks();
//        List<Progress> progressesInTasks = Stream.of(tasksInSprint.stream().map(Task::getProgressList)).flatMap(x->x).collect(Collectors.toList());
//        progressesInUser.stream().filter(o->tasksInSprint.stream().map(Task::getProgressList))
        return progressRepository.allProgressByUserIdAndSprintId(userId,sprintId);
    }

    @Override
    public boolean addProgressToUser(Progress progress, User user) {
        com.softserve.sprint13.entity.User userEntity = userRepository.getOne(user.getId());
        Progress progressEntity = progressRepository.getOne(progress.getId());
        userEntity.getProgressList().add(progressEntity);
        return userRepository.save(userEntity) != null;
    }
}
