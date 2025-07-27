package com.mobility.user_service.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Entity
@Table(name = "drivers")
public class Driver {

    @Getter
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column (name = "licence_number")
    private String licenceNumber;

    public Driver(){

    }

    public String getLicence_number() {
        return licenceNumber;
    }

}
