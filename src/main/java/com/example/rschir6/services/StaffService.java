package com.example.rschir6.services;


import com.example.rschir6.models.Staff;
import com.example.rschir6.repositories.StaffRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StaffService {
    private final StaffRepository staffRepository;

    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public Iterable<Staff> getAll() {
        return staffRepository.findAll();
    }

    public Staff getOne(int id) {
        return staffRepository.findById(id).get();
    }

    public void save(Staff staff) {
        staffRepository.save(staff);
    }

    public String update(int id, Staff newStaff){
        Optional<Staff> optionalStaff = staffRepository.findById(id);
        if (optionalStaff.isEmpty()){
            return "Data not update.";
        }
        newStaff.setId(id);
        staffRepository.save(newStaff);
        return "Data update.";
    }

    public String delete(int id){
        Optional<Staff> optionalBook = staffRepository.findById(id);
        if (optionalBook.isEmpty()){
            return "Data no.";
        }
        staffRepository.delete(optionalBook.get());
        return "Data delete.";
    }
}
