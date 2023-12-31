package com.example.project3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
public class Customer {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty
    @Pattern(regexp = "^05\\d{8}$", message = "Invalid phone number format")
    private String phoneNumber;

    @OneToOne
    @MapsId
    @JsonIgnore
    private User user;

    @OneToMany(cascade =CascadeType.ALL,mappedBy = "customer")
    private Set<Account> accounts;


}
