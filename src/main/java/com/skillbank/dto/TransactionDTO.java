package com.skillbank.dto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TransactionDTO {
    @NotNull(message = "Sender is required")
    private Long senderId;

    @NotNull(message = "Recipient is required")
    private Long receiverId;

    @Min(value = 1, message = "Amount must be greater than 0")
    private int amount;
}