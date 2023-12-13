package com.example.rschir6.models;

public enum TypeDish {
    NONE("Все"),
    MAIN("Основные блюда"),
    DESSERT("Десерты"),
    DRINK("Напитки");
    String name;

    TypeDish(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
