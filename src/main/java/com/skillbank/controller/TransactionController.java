package com.skillbank.controller;

import com.skillbank.dto.TransactionDTO;
import com.skillbank.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<String> makeTransfer(@RequestBody TransactionDTO dto) {
        try {
            transactionService.transferHours(dto);
            return ResponseEntity.ok("Przelew wykonany pomyślnie!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}