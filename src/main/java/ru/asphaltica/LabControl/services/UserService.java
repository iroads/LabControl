package ru.asphaltica.LabControl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.asphaltica.LabControl.models.Unit;
import ru.asphaltica.LabControl.models.User;
import ru.asphaltica.LabControl.repositories.UserRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> findAll() {
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
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Transactional
    public void update(int id, User updatedUser) {
        User userToBeUpdated = userRepository.findById(id).get();

        updatedUser.setId(id);
        updatedUser.setUnit(userToBeUpdated.getUnit()); //чтобы не терялась связь при обновлении
        //Проверяем если пароль в форме пришел пустой то не меняем его
        if (!updatedUser.getPassword().isEmpty()) {
            updatedUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        } else {
            updatedUser.setPassword(userToBeUpdated.getPassword());
        }
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
