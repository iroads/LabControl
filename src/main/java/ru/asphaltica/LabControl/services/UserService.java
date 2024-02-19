package ru.asphaltica.LabControl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.asphaltica.LabControl.models.Unit;
import ru.asphaltica.LabControl.models.User;
import ru.asphaltica.LabControl.repositories.UnitRepository;
import ru.asphaltica.LabControl.repositories.UserRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> finaAll() {
        List<User> users = userRepository.findAll();
        return users;
    }

    public List<User> findByUnitId(int id) {
        return userRepository.findByUnitId(id);
    }

    public User findById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    @Transactional
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    @Transactional
    public void update(int id, User updatedUser) {
        updatedUser.setId(id);
        userRepository.save(updatedUser);
    }

    @Transactional
    public void free(int id) {
        User user = userRepository.findById(id).orElse(null);
        user.setUnit(null);
        userRepository.save(user);
    }

    @Transactional
    public void addOwnUnit(int id, Unit unit) {
        User user = userRepository.findById(id).orElse(null);
        user.setUnit(unit);
        userRepository.save(user);
    }

}
