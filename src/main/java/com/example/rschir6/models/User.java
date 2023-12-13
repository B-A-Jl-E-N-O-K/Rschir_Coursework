package com.example.rschir6.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "client")
@NoArgsConstructor
public class User {

    public User(String name, String email, String password, boolean vip, boolean admin){
        this.name = name;
        this.email = email;
        this.password = password;
        this.vip = vip;
        this.admin = admin;
    }
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "vip")
    private boolean vip;
    @Column(name = "admin")
    private boolean admin;


    public boolean getAdmin() {
        return this.admin;
    }
}
