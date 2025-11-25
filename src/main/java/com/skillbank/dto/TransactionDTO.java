package com.skillbank.dto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TransactionDTO {
    @NotNull(message = "Nadawca jest wymagany")
    private Long senderId;

    @NotNull(message = "Odbiorca jest wymagany")
    private Long receiverId;

    @Min(value = 1, message = "Kwota musi być większa od 0")
    private int amount;
}