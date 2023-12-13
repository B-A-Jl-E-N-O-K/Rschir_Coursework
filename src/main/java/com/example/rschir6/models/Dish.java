package com.example.rschir6.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "dish")
@NoArgsConstructor
public class Dish {

    public Dish(String name, int price, TypeDish type, boolean is_available, String structure, String image){
        this.name = name;
        this.price = price;
        this.type = type;
        this.is_available = is_available;
        this.structure = structure;
        this.image = image;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable=false, updatable=false)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TypeDish type;

    @Column(name = "price")
    private int price;

    @Column(name = "is_available")
    private boolean is_available;

    @Column(name = "structure")
    private String structure;

    @Column(name = "image")
    private String image;
}
