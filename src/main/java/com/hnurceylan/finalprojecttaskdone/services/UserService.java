package com.hnurceylan.finalprojecttaskdone.services;


import com.hnurceylan.finalprojecttaskdone.dto.ProviderCreateProfileDto;
import com.hnurceylan.finalprojecttaskdone.entities.User;
import com.hnurceylan.finalprojecttaskdone.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
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
        // Mevcut kullanıcıyı ID'ye göre buluyoruz
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Mevcut kullanıcı verilerini güncelliyoruz
        existingUser.setName(newUser.getName());
        existingUser.setPhoneNumber(newUser.getPhoneNumber());
        existingUser.setCity(newUser.getCity());
        existingUser.setDistrict(newUser.getDistrict());
        existingUser.setNeighborhood(newUser.getNeighborhood());
        existingUser.setDescription(newUser.getDescription());

        // Güncellenmiş kullanıcıyı veritabanına kaydediyoruz
        return userRepository.save(existingUser);
    }


    // Yeni metot: findByEmail
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);

    }


    public List<User> getApprovedProviders() {
        return userRepository.findByRoleAndStatus();
    }

    public void deleteById(Long userId) {
    }

    public List<ProviderCreateProfileDto> getAllServiceProviders() {

        List<User> users = userRepository.findAllByRoleAndIsApproved("PROVIDER", true);

        // Kullanıcıları DTO'ya dönüştürerek dinamik response oluşturuyoruz
        return users.stream().map(user -> {
            ProviderCreateProfileDto response = new ProviderCreateProfileDto();
            response.setId(user.getId());
            response.setName(user.getName());
            response.setSurname(user.getSurname());
            response.setCity(user.getCity());
            response.setDistrict(user.getDistrict());
            response.setNeighborhood(user.getNeighborhood());
            response.setDescription(user.getDescription());
            response.setPhoneNumber(user.getPhoneNumber());
            response.setServiceArea(user.getServiceArea());

            // Eğer şirketse, şirket adını dahil et
            if (Boolean.TRUE.equals(user.getIsCompony())) {
                response.setCompanyName(user.getCompanyName());
            }

            return response;
        }).toList();
    }
    }


