package com.skillbank.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterDTO {
    @NotBlank(message = "Login jest wymagany")
    @Size(min = 3, message = "Login musi mieć min. 3 znaki")
    private String username;

    @NotBlank(message = "Hasło jest wymagane")
    @Size(min = 6, message = "Hasło musi mieć min. 6 znaków")
    private String password;
}