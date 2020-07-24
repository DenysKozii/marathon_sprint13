package com.softserve.sprint13.service;

import com.softserve.sprint13.entity.Marathon;
import com.softserve.sprint13.entity.Task;
import com.softserve.sprint13.entity.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getUserById(Long id) {
        return null;
    }

    @Override
    public User createOrUpdateUser(User user) {
        return null;
    }

    @Override
    public List<User> getAllByRole(String role) {
        return null;
    }

    @Override
    public boolean addUserToMarathon(User user, Marathon marathon) {
        return false;
    }

    @Override
    public boolean addUserToTask(User user, Task task) {
        return false;
    }
}
