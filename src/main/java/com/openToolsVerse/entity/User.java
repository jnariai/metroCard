package com.openToolsVerse.entity;

import com.openToolsVerse.shared.PassagerType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String email;
    private String password;
    private PassagerType passagerType;

    public User(String name, String email, String password, PassagerType passagerType) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.passagerType = passagerType;
    }
}
