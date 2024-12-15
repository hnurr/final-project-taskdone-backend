package com.hnurceylan.finalprojecttaskdone.services;


import com.hnurceylan.finalprojecttaskdone.entities.User;
import com.hnurceylan.finalprojecttaskdone.repository.UserRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getOneUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User save(User newUser) {
        return userRepository.save(newUser);
    }

    public User updateOneUser(Long userId, User newUser) {

        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {

            User foundUser = user.get();
            foundUser.setEmail(newUser.getEmail());
            foundUser.setPassword(newUser.getPassword());

            userRepository.save(foundUser);
        }
        return null;

    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);

    }


    // Yeni metot: findByEmail
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);

    }


}

