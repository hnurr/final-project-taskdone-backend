package com.hnurceylan.finalprojecttaskdone.services;


import com.hnurceylan.finalprojecttaskdone.entities.User;
import com.hnurceylan.finalprojecttaskdone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private UserRepository userRepository;

    // Onaylı providerları döndüren metod
    public List<User> findAllCustomers() {
        return userRepository.findAllRoleCustomer();
    }
}
