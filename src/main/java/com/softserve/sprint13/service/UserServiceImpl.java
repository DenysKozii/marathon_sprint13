package com.softserve.sprint13.service;

import com.softserve.sprint13.entity.Marathon;
import com.softserve.sprint13.entity.Progress;
import com.softserve.sprint13.entity.Task;
import com.softserve.sprint13.entity.User;
import com.softserve.sprint13.repository.ProgressRepository;
import com.softserve.sprint13.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProgressRepository progressRepository;

    @Override
    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        return users.isEmpty() ? new ArrayList<>() : users;
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent())
            return user.get();
        else throw new EntityNotFoundException("No user for given id");
    }

    @Override
    public User createOrUpdateUser(User user) {
        if (user.getId() != null) {
            Optional<User> userToUpdate = userRepository.findById(user.getId());
            if (userToUpdate.isPresent()) {
                User newUser = userToUpdate.get();
                newUser.setEmail(user.getEmail());
                newUser.setFirstName(user.getFirstName());
                newUser.setLastName(user.getLastName());
                newUser.setPassword(user.getPassword());
                newUser.setRole(user.getRole());
                return newUser;
            }
        }
        user = userRepository.save(user);
        return user;
    }

    @Override
    public List<User> getAllByRole(String role) {
        List<User> users = userRepository.findAll()
                .stream()
                .filter(user -> user.getRole().toString().equals(role))
                .collect(Collectors.toList());
        return users.isEmpty() ? new ArrayList<>() : users;
    }

    @Override
    public boolean addUserToMarathon(User user, Marathon marathon) {
        return marathon.getUsers().add(user);
    }

    @Override
    public boolean addUserToTask(User user, Task task) {
        Progress progress = new Progress();
        progress.setTrainee(user);
        progress.setStatus(Progress.TaskStatus.PENDING);
        return task.getProgressList().add(progress);
    }
}
