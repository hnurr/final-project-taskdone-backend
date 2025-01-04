package com.hnurceylan.finalprojecttaskdone.controller;

import com.hnurceylan.finalprojecttaskdone.entities.User;
import com.hnurceylan.finalprojecttaskdone.enums.Role;
import com.hnurceylan.finalprojecttaskdone.repository.UserRepository;
import com.hnurceylan.finalprojecttaskdone.request.ProviderRegisterRequest;
import com.hnurceylan.finalprojecttaskdone.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody User newUser) {
        User existingUser = userService.findByEmail(newUser.getEmail());
        if (existingUser != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Collections.singletonMap("message", "Bu email ile zaten bir kullanıcı mevcut!"));
        }
        // Role, frontend tarafından gönderilen role göre ayarlanmalı
//        String role = newUser.getRole() != null ? newUser.getRole() : "normal"; // Varsayılan role "normal"
//        newUser.setRole(role);
        newUser.setRole(Role.CUSTOMER);
        userService.save(newUser);
        return ResponseEntity.ok(Collections.singletonMap("message", "Kayıt başarılı!"));
    }

//    @PostMapping("/login")
//    public ResponseEntity<Map<String, String>> login(@RequestBody User loginRequest) {
//        User existingUser = userService.findByEmail(loginRequest.getEmail());
//        if (existingUser == null || !existingUser.getPassword().equals(loginRequest.getPassword())) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                    .body(Collections.singletonMap("message", "Geçersiz email veya şifre!"));
//        }
//
//        Map<String, String> response = new HashMap<>();
//        response.put("message", "Giriş başarılı!");
//       response.put("role", existingUser.getRole().name()); // Rol bilgisini de ekliyoruz
//
//        return ResponseEntity.ok(response);
//    }


    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody User loginRequest, HttpSession session) {
        // Kullanıcıyı email ile bul
        User existingUser = userService.findByEmail(loginRequest.getEmail());

        // Kullanıcı yoksa veya şifre uyuşmuyorsa hata döndür
        if (existingUser == null || !existingUser.getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("message", "Geçersiz email veya şifre!"));
        }

        // Eğer kullanıcı PROVIDER ise ve onaylanmamışsa hata döndür
        if (existingUser.getRole() == Role.PROVIDER && !Boolean.TRUE.equals(existingUser.getIsApproved())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(Collections.singletonMap("message", "Hesabınız henüz onaylanmamış! Lütfen adminin onayını bekleyin."));
        }

        // Giriş başarılı, session'a kullanıcı bilgilerini kaydet
        session.setAttribute("userId", existingUser.getId());  // Kullanıcının ID'sini oturuma kaydet

        // Yanıtı oluştur
        Map<String, String> response = new HashMap<>();
        response.put("message", "Giriş başarılı!");
        response.put("role", existingUser.getRole().name()); // Kullanıcının rolü
        response.put("id" ,String.valueOf(existingUser.getId()));
        return ResponseEntity.ok(response);
    }




//    @PostMapping("/register/admin")
//    public ResponseEntity<?> registerAdmin(@RequestBody ProviderRegisterRequest providerRegisterRequest) {
//        try {
//            User provider = new User();
//            provider.setEmail(providerRegisterRequest.getEmail());
//            provider.setPassword(providerRegisterRequest.getPassword());
//            provider.setCity(providerRegisterRequest.getCity());
//            provider.setCountry(providerRegisterRequest.getCountry());
//            provider.setDistrict(providerRegisterRequest.getDistrict());
//            provider.setNeighborhood(providerRegisterRequest.getNeighborhood());
//            provider.setPhoneNumber(providerRegisterRequest.getPhoneNumber());
//            provider.setCompanyName(providerRegisterRequest.getCompanyName());
//            provider.setIsCompony(providerRegisterRequest.getIsCompony());
//            provider.setServiceArea(providerRegisterRequest.getServiceArea());
//            provider.setSurname(providerRegisterRequest.getSurname());
//            provider.setName(providerRegisterRequest.getName());
//            provider.setRole(Role.PROVIDER);
//
//            // Admin kaydını kaydet
//            userRepository.save(provider);
//            return ResponseEntity.ok("Admin registered successfully");
//        } catch (Exception e) {
//            // Konsolda hatayı gör
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Hesap oluşturulamadı: " + e.getMessage());
//        }
//    }



    @PostMapping("/register/provider")
    public ResponseEntity<?> registerAdmin(@RequestBody ProviderRegisterRequest providerRegisterRequest) {
        try {
            // Zorunlu alanlar için kontrol
            if (providerRegisterRequest.getEmail() == null || providerRegisterRequest.getEmail().isEmpty()) {
                return ResponseEntity.badRequest().body("E-posta adresi zorunludur.");
            }
            if (providerRegisterRequest.getPassword() == null || providerRegisterRequest.getPassword().isEmpty()) {
                return ResponseEntity.badRequest().body("Şifre zorunludur.");
            }
            if (providerRegisterRequest.getCity() == null || providerRegisterRequest.getCity().isEmpty()) {
                return ResponseEntity.badRequest().body("Şehir zorunludur.");
            }
            if (providerRegisterRequest.getCountry() == null || providerRegisterRequest.getCountry().isEmpty()) {
                return ResponseEntity.badRequest().body("Ülke zorunludur.");
            }
            if (providerRegisterRequest.getDistrict() == null || providerRegisterRequest.getDistrict().isEmpty()) {
                return ResponseEntity.badRequest().body("İlçe zorunludur.");
            }
            if (providerRegisterRequest.getNeighborhood() == null || providerRegisterRequest.getNeighborhood().isEmpty()) {
                return ResponseEntity.badRequest().body("Mahalle zorunludur.");
            }
            if (providerRegisterRequest.getPhoneNumber() == null || providerRegisterRequest.getPhoneNumber().isEmpty()) {
                return ResponseEntity.badRequest().body("Telefon numarası zorunludur.");
            }

            if (providerRegisterRequest.getServiceArea() == null || providerRegisterRequest.getServiceArea().isEmpty()) {
                return ResponseEntity.badRequest().body("Hizmet alanı zorunludur.");
            }
            if (providerRegisterRequest.getSurname() == null || providerRegisterRequest.getSurname().isEmpty()) {
                return ResponseEntity.badRequest().body("Soyadı zorunludur.");
            }
            if (providerRegisterRequest.getName() == null || providerRegisterRequest.getName().isEmpty()) {
                return ResponseEntity.badRequest().body("Ad zorunludur.");
            }

            // E-posta adresi daha önce kullanılmış mı?
            User existingUser = userRepository.findByEmail(providerRegisterRequest.getEmail());
            if (existingUser != null) {
                return ResponseEntity.badRequest().body("Bu e-posta adresi ile zaten kayıtlı bir hesap bulunmaktadır.");
            }

            // Yeni kullanıcı oluşturma
            User provider = new User();
            provider.setEmail(providerRegisterRequest.getEmail());
            provider.setPassword(providerRegisterRequest.getPassword());
            provider.setCity(providerRegisterRequest.getCity());
            provider.setCountry(providerRegisterRequest.getCountry());
            provider.setDistrict(providerRegisterRequest.getDistrict());
            provider.setNeighborhood(providerRegisterRequest.getNeighborhood());
            provider.setPhoneNumber(providerRegisterRequest.getPhoneNumber());
            provider.setCompanyName(providerRegisterRequest.getCompanyName());
            provider.setIsCompony(providerRegisterRequest.getIsCompony());
            provider.setServiceArea(providerRegisterRequest.getServiceArea());
            provider.setSurname(providerRegisterRequest.getSurname());
            provider.setName(providerRegisterRequest.getName());
            provider.setRole(Role.PROVIDER);
            provider.setIsApproved(false);


            // Admin kaydını kaydet
            userRepository.save(provider);
            return ResponseEntity.ok("Provider registered successfully,Approval awaited.");
        } catch (Exception e) {
            // Konsolda hatayı gör
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Hesap oluşturulamadı: " + e.getMessage());
        }
    }

}