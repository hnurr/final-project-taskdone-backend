package com.hnurceylan.finalprojecttaskdone.controller;



import com.hnurceylan.finalprojecttaskdone.entities.User;
import com.hnurceylan.finalprojecttaskdone.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
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

    @PutMapping("/{userId}")
    public User updateOneUser(@PathVariable Long userId, @RequestBody User newUser) {

        return userService.updateOneUser(userId, newUser);
    }


    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId){
        userService.deleteById(userId);
    }


}


