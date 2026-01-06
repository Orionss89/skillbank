package com.skillbank.service;

import com.skillbank.dto.TransactionDTO;
import com.skillbank.exception.BusinessException;
import com.skillbank.exception.ResourceNotFoundException;
import com.skillbank.model.*;
import com.skillbank.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionService {

    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;

    @Transactional
    public void transferHours(TransactionDTO dto) {
        log.info("Initiating transfer: {}h from ID {} to ID {}", dto.getAmount(), dto.getSenderId(), dto.getReceiverId());

        User sender = userRepository.findById(dto.getSenderId())
                .orElseThrow(() -> new ResourceNotFoundException("Sender not found with ID: " + dto.getSenderId()));

        User receiver = userRepository.findById(dto.getReceiverId())
                .orElseThrow(() -> new ResourceNotFoundException("Recipient not found with ID:: " + dto.getReceiverId()));

        if (sender.getId().equals(receiver.getId())) {
            throw new BusinessException("Cannot transfer funds to yourself");
        }

        Wallet senderWallet = sender.getWallet();
        if (senderWallet.getBalance() < dto.getAmount()) {
            log.warn("User has no funds: {}", sender.getUsername());
            throw new BusinessException("Insufficient funds in wallet");
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

        log.info("Transfer successful");
    }
}