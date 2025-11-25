package com.skillbank.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "wallets")
@Data
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int balance;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}