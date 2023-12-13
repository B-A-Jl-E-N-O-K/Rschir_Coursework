package com.example.rschir6.services;

import com.example.rschir6.models.Dish;
import com.example.rschir6.models.TypeDish;
import com.example.rschir6.repositories.DishRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class DishService {
    private final DishRepository dishRepository;

    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public Iterable<Dish> getAll() {
        return dishRepository.findAll();
    }

    public Iterable<Dish> getFilteredMenu(String type) {
        Iterable<Dish> allDishes = dishRepository.findAll();
        List<Dish> dishesList = new ArrayList<>();
        for (Dish dish: allDishes){
            if(dish.getType().equals(TypeDish.valueOf(type))){
                dishesList.add(dish);
            }
        }
        return dishesList;
    }

    public Dish getOne(int id) {
        return dishRepository.findById(id).get();
    }

    public void save(Dish dish) {
        dishRepository.save(dish);
    }

    public String update(int id, Dish newDish){
        Optional<Dish> optionalDish = dishRepository.findById(id);
        if (optionalDish.isEmpty()){
            return "Data not update.";
        }
        newDish.setId(id);
        dishRepository.save(newDish);
        return "Data update.";
    }

    public String delete(int id){
        Optional<Dish> optionalDish = dishRepository.findById(id);
        if (optionalDish.isEmpty()){
            return "Data not found";
        }
        dishRepository.delete(optionalDish.get());
        return "Data delete.";
    }
}
