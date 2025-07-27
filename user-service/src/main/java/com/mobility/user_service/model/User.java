package com.mobility.user_service.model;

import com.mobility.user_service.enums.RoleType;
import jakarta.persistence.*;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Getter
    @Setter
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;

    @Setter
    @Getter
    @Column(nullable = false)
    private String name;

    @Setter
    @Getter
    @Column(nullable = false, unique = true)
    private String email;

    @Setter
    @Getter
    @Column(nullable = false)
    private String password;

    @Setter
    @Getter
    @Column(name = "phone_numer", nullable = false, unique = true)
    private String phoneNumber;

    @Setter
    @Getter
    @Enumerated(EnumType.STRING)
    private RoleType role;

    @Setter
    @Getter
    private LocalDateTime createdAt;

    public User(){

    }

}
