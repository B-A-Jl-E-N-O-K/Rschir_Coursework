package com.example.rschir6.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "personal")
@NoArgsConstructor
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "post")
    @Enumerated(EnumType.STRING)
    private TypeStaff post;

    @Column(name = "salary")
    private int salary;

    @Column(name = "achievements")
    private String achievements;

    public Staff(String name, int salary, TypeStaff post, String achievements){
        this.name = name;
        this.salary = salary;
        this.post = post;
        this.achievements = achievements;
    }
}
