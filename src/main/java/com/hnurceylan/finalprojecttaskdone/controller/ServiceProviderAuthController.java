package com.hnurceylan.finalprojecttaskdone.controller;

//
//import com.hnurceylan.finalprojecttaskdone.entities.Provider;
//import com.hnurceylan.finalprojecttaskdone.services.ProviderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
//@Controller
//@RequestMapping("/service")
//@CrossOrigin(origins = "http://localhost:3000")
//public class ServiceProviderAuthController {
//
//    private final ProviderService providerService;
//
//    public ServiceProviderAuthController(ProviderService providerService) {
//        this.providerService = providerService;
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity<Map<String, String>> register(@RequestBody Provider newProvider) {
//        Provider existingProvider = providerService.findByEmail(newProvider.getEmail());
//        if (existingProvider != null) {
//            return ResponseEntity.status(HttpStatus.CONFLICT)
//                    .body(Collections.singletonMap("message", "Bu email ile zaten bir kullanıcı mevcut!"));
//        }
//
//        providerService.save(newProvider);
//        return ResponseEntity.ok(Collections.singletonMap("message", "Kayıt başarılı!"));
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<Map<String, Object>> login(@RequestBody Provider loginRequest) {
//        // Email'e göre kullanıcıyı bul
//        Provider existingProvider = providerService.findByEmail(loginRequest.getEmail());
//        if (existingProvider == null || !existingProvider.getPassword().equals(loginRequest.getPassword())) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                    .body(Collections.singletonMap("message", "Geçersiz email veya şifre!"));
//        }
//
//        // Kullanıcıyı doğruladıktan sonra rol bilgisini döndür
//        Map<String, Object> response = new HashMap<>();
//        response.put("message", "Giriş başarılı!");
//        response.put("role", existingProvider.getRole()); // Burada role bilgisi döndürülüyor
//
//        return ResponseEntity.ok(response);
//    }
//}
