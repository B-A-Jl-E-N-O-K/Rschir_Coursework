package com.example.rschir6.services;

import com.example.rschir6.models.Dish;
import com.example.rschir6.models.User;
import com.example.rschir6.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository clientRepository;

    public UserService(UserRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Iterable<User> getAll() {
        return clientRepository.findAll();
    }

    public User getOne(int id) {
        return clientRepository.findById(id).get();
    }

    public boolean[] login(String name, String pass) {
        Iterable<User> allUsers = clientRepository.findAll();
        for (User user: allUsers){
            if((user.getName().equals(name) || user.getEmail().equals(name)) && user.getPassword().equals(pass)){
                return new boolean[] {true, user.getAdmin()};
            }

        }

        return new boolean[] {false, false};
    }

    public void save(User client) {
        clientRepository.save(client);
    }

    public String update(int id, User newClient){
        Optional<User> optionalTelephone = clientRepository.findById(id);
        if (optionalTelephone.isEmpty()){
            return "Data not update.";
        }
        newClient.setId(id);
        clientRepository.save(newClient);
        return "Data update.";
    }

    public String delete(int id){
        Optional<User> optionalClient = clientRepository.findById(id);
        if (optionalClient.isEmpty()){
            return "Data no.";
        }
        clientRepository.delete(optionalClient.get());
        return "Data delete.";
    }
}
