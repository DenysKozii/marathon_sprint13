package com.softserve.sprint13.service;

import com.softserve.sprint13.entity.Marathon;
import com.softserve.sprint13.entity.Progress;
import com.softserve.sprint13.entity.User;
import com.softserve.sprint13.exception.IncorrectIdException;
import com.softserve.sprint13.repository.MarathonRepository;
import com.softserve.sprint13.repository.ProgressRepository;
import com.softserve.sprint13.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {


    UserRepository userRepository;

    @Autowired
    ProgressRepository progressRepository;


    MarathonRepository marathonRepository;

    public UserServiceImpl(UserRepository userRepository, MarathonRepository marathonRepository) {
        this.userRepository = userRepository;
        this.marathonRepository = marathonRepository;
    }

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
        else throw new IncorrectIdException("No user for given id");
    }

    @Override
    public void deleteUserByIdFromMarathon(Long user_id, Long marathon_id) {
        Optional<Marathon> marathonFromBd = marathonRepository.findById(marathon_id);
        Optional<User> user = userRepository.findById(user_id);
        if (user.isPresent() && marathonFromBd.isPresent()) {
            marathonFromBd.get().getUsers().remove(user);
            marathonRepository.save(marathonFromBd.get());
            user.get().getMarathons().remove(marathonFromBd);
            userRepository.save(user.get());
        } else throw new EntityNotFoundException("No user exist for given id");
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
                newUser = userRepository.save(newUser);
                return newUser;
            }
        }
        user = userRepository.save(user);
        return user;
    }

    @Override
    public User deleteUser(User user) {
        Long id = user.getId();
        if (id != null) {
            userRepository.deleteById(id);
            return user;
        }
        return null;
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
        User userEntity = userRepository.getOne(user.getId());
        Marathon marathonEntity = marathonRepository.getOne(marathon.getId());
        marathonEntity.getUsers().add(userEntity);
        return marathonRepository.save(marathonEntity) != null;
    }

    @Override
    public boolean addUserToProgress(User user, Progress progress) {
        User userEntity = userRepository.getOne(user.getId());
        Progress progressEntity = progressRepository.getOne(progress.getId());
        progressEntity.setTrainee(userEntity);
        return progressRepository.save(progressEntity) != null;
    }

    @Override
    public List<User> studentsFromMarathon(Long marathon_id) {
        List<User> users = getAllByRole("TRAINEE");
        Marathon marathon = marathonRepository.getOne(marathon_id);
        return users.isEmpty() ? new ArrayList<>() : users.stream().filter(o->o.getMarathons().contains(marathon)).collect(Collectors.toList());
    }

    @Override
    public List<User> studentsNotFromMarathon(Long marathon_id) {
        List<User> users = getAllByRole("TRAINEE");
        Marathon marathon = marathonRepository.getOne(marathon_id);
        return users.isEmpty() ? new ArrayList<>() : users.stream().filter(o->!o.getMarathons().contains(marathon)).collect(Collectors.toList());
    }

    @Override
    public List<Marathon> marathonsWithoutStudent(Long student_id) {
        User student = userRepository.getOne(student_id);
        List<Marathon> marathons = marathonRepository.findAll();
        return marathons.isEmpty() ? new ArrayList<>() : marathons.stream().filter(o->!o.getUsers().contains(student))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> findByRole(User.Role role) {
        return userRepository.findByRole(role);
    }
}
