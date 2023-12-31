package com.example.project3.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "user name should not be empty")
    @Size(min = 4,max = 10,message = "user name should be more than 4 and less than 10")
    @Column(columnDefinition = "varchar(10) not null unique")
    private String username;
    @NotEmpty(message = "password should not be empty")
    @Size(min = 6,max = 10,message = "password should be more than 6 and less than 10")
    @Column(columnDefinition = "varchar(10) not null ")
    private String password;
    @NotEmpty(message = "name should not be empty")
    @Size(min = 2,max = 20,message = "name should be more than  2 and less than 20")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;
    @Email(message = "Enter valid email")
    @Column(columnDefinition = "varchar(30) unique")
    private String email;
    @Pattern(regexp = "^(CUSTOMER|EMPLOYEE|ADMIN)$")
    @Column(columnDefinition = "varchar(10) Check (role='CUSTOMER' or role='EMPLOYEE' or role='ADMIN')")
    private String role;


    @OneToOne(cascade = CascadeType.ALL,mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Employee employee;
}

