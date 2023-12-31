package com.example.project3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull

    private String accountNumber;
    @NotNull
    @Positive
    private Integer balance;
    @AssertFalse
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "customer_id",referencedColumnName = "id")
    @JsonIgnore
    private Customer customer;
}
