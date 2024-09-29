package com.aston.home.lab.clientservicelab.entity;

import java.util.UUID;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "clients")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Column(name = "address")
    String address;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "second_name")
    String secondName;

    @Column(name = "phone_number", nullable = false, unique = true)
    String phoneNumber;
}