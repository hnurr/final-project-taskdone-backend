package com.hnurceylan.finalprojecttaskdone.services;



//import com.hnurceylan.finalprojecttaskdone.entities.Provider;
//import com.hnurceylan.finalprojecttaskdone.repository.ProviderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//
//@Service
//public class ProviderService {
//
//
//    final private ProviderRepository providerRepository;
//
//    public ProviderService(ProviderRepository providerRepository) {
//        this.providerRepository = providerRepository;
//    }
//
//    public List<Provider> getAllServiceProvider() {
//
//        return providerRepository.findAll();
//    }
//
//    public Provider getOneServiceProviderById(Long serviceProviderId) {
//
//        return providerRepository.findById(serviceProviderId).orElse(null);
//    }
//
//    public Provider save(Provider newProvider) {
//
//        return providerRepository.save(newProvider);
//    }
//
//    public Provider updateOneServiceProvider(Long serviceProviderId, Provider newProvider) {
//
//        Optional<Provider> serviceProvider = providerRepository.findById(serviceProviderId);
//
//        if (serviceProvider.isPresent()) {
//
//            Provider foundProvider = serviceProvider.get();
//            foundProvider.setName(newProvider.getName());
//            foundProvider.setSurname(newProvider.getSurname());
//            foundProvider.setServiceArea(newProvider.getServiceArea());
//            foundProvider.setCity(newProvider.getCity());
//            foundProvider.setCountry(newProvider.getCountry());
//            foundProvider.setEmail(newProvider.getEmail());
//            foundProvider.setDistrict(newProvider.getDistrict());
//            foundProvider.setIsCompony(newProvider.getIsCompony());
//            foundProvider.setCompanyName(newProvider.getCompanyName());
//            foundProvider.setPhoneNumber(newProvider.getPhoneNumber());
//            foundProvider.setNeighborhood(newProvider.getNeighborhood());
//
//
//            providerRepository.save(newProvider);
//        }
//        return null;
//    }
//
//    public void deleteById(Long serviceProviderId) {
//        providerRepository.deleteById(serviceProviderId);
//
//    }
//
//    // Yeni metot: findByEmail
//
//
//    public Provider findByEmail(String email) {
//        return providerRepository.findByEmail(email);
//    }
//}
