package com.example.rschir6.models;

public enum TypeStaff {
    NONE("Все"),
    COOK("Повар"),
    WAITER("Официант"),
    ADMIN("Администратор");
    String name;

    TypeStaff(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
