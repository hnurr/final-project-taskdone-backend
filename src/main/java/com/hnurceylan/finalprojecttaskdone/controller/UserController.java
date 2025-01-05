package com.hnurceylan.finalprojecttaskdone.controller;



import com.hnurceylan.finalprojecttaskdone.dto.ProviderCreateProfileDto;
import com.hnurceylan.finalprojecttaskdone.entities.User;
import com.hnurceylan.finalprojecttaskdone.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {


    private final UserService userService;

   public UserController(UserService userService){
       this.userService=userService;
   }


    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers() ;
    }




    @GetMapping("/{userId}")
    public User getOneUser(@PathVariable Long userId) {
        //exceptionn
        return userService.getOneUserById(userId);
    }

    @PostMapping
    public User createUser(@RequestBody User newUser) {

        return userService.save(newUser);

    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<String> updateProfile(@RequestBody User updatedUser, @PathVariable Long userId) {
        //Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Güncelleme için giriş yapmanız gerekiyor!");
        }

        userService.updateOneUser(userId, updatedUser);
        return ResponseEntity.ok("Profil başarıyla güncellendi!");
    }

    @GetMapping("/service-providers")
    public ResponseEntity<List<ProviderCreateProfileDto>> getServiceProviders() {
        List<ProviderCreateProfileDto> serviceProviders = userService.getAllServiceProviders();
        return ResponseEntity.ok(serviceProviders);
    }



    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId){
        userService.deleteById(userId);
    }


}


