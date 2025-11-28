package com.skillbank.controller;

import com.skillbank.dto.TransactionDTO;
import com.skillbank.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<String> makeTransfer(@Valid @RequestBody TransactionDTO dto) {
        transactionService.transferHours(dto);
        return ResponseEntity.ok("Przelew wykonany pomyślnie!");
    }
}