package com.softserve.sprint13.service;

import com.softserve.sprint13.entity.Marathon;
import com.softserve.sprint13.entity.Progress;
import com.softserve.sprint13.entity.User;

import java.util.List;

public interface UserService {

    public List<User> getAll();

    public User getUserById(Long id);

    void deleteUserByIdFromMarathon(Long user_id, Long marathon_id);

    public User createOrUpdateUser(User user);

    public List<User> getAllByRole(String role);

    public boolean addUserToMarathon(User user, Marathon marathon);

    public boolean addUserToProgress(User user, Progress progress);

    public User deleteUser(User user);

    List<User> studentsFromMarathon(Long marathon_id);

    List<User> studentsNotFromMarathon(Long marathon_id);

    List<Marathon> marathonsWithoutStudent(Long student_id);
}
