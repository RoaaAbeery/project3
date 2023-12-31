package com.example.project3.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerDTO {
    @NotEmpty(message = "user name should not be empty")
    @Size(min = 4,max = 10,message = "user name should be more than 4 and less than 10")
    private String username;
    @NotEmpty(message = "password should not be empty")
    @Size(min = 6,max = 10,message = "password should be more than 6 and less than 10")
    private String password;
    @NotEmpty(message = "name should not be empty")
    @Size(min = 2,max = 20,message = "name should be more than  2 and less than 20")
    private String name;
    @Email(message = "Enter valid email")
    private String email;
    @Pattern(regexp = "^(CUSTOMER|EMPLOYEE|ADMIN)$")
    private String role;


}
