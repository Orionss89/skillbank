package com.skillbank.service;

import com.skillbank.dto.TransactionDTO;
import com.skillbank.model.*;
import com.skillbank.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class TransactionService {
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;

    public TransactionService(UserRepository userRepository, TransactionRepository transactionRepository, WalletRepository walletRepository) {
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
        this.walletRepository = walletRepository;
    }

    @Transactional
    public void transferHours(TransactionDTO dto) {
        User sender = userRepository.findById(dto.getSenderId())
                .orElseThrow(() -> new RuntimeException("Brak nadawcy"));
        User receiver = userRepository.findById(dto.getReceiverId())
                .orElseThrow(() -> new RuntimeException("Brak odbiorcy"));

        if (sender.getId().equals(receiver.getId())) {
            throw new RuntimeException("Nie możesz przelewać sam sobie!");
        }

        Wallet senderWallet = sender.getWallet();
        if (senderWallet.getBalance() < dto.getAmount()) {
            throw new RuntimeException("Brak wystarczających środków!");
        }

        senderWallet.setBalance(senderWallet.getBalance() - dto.getAmount());
        receiver.getWallet().setBalance(receiver.getWallet().getBalance() + dto.getAmount());

        walletRepository.save(senderWallet);
        walletRepository.save(receiver.getWallet());

        Transaction transaction = new Transaction();
        transaction.setSender(sender);
        transaction.setReceiver(receiver);
        transaction.setAmount(dto.getAmount());
        transaction.setTimestamp(LocalDateTime.now());

        transactionRepository.save(transaction);
    }
}