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
        log.info("Próba przelewu: {}h od ID {} do ID {}", dto.getAmount(), dto.getSenderId(), dto.getReceiverId());

        User sender = userRepository.findById(dto.getSenderId())
                .orElseThrow(() -> new ResourceNotFoundException("Nie znaleziono nadawcy o ID: " + dto.getSenderId()));

        User receiver = userRepository.findById(dto.getReceiverId())
                .orElseThrow(() -> new ResourceNotFoundException("Nie znaleziono odbiorcy o ID: " + dto.getReceiverId()));

        if (sender.getId().equals(receiver.getId())) {
            throw new BusinessException("Nie można przelać środków samemu sobie");
        }

        Wallet senderWallet = sender.getWallet();
        if (senderWallet.getBalance() < dto.getAmount()) {
            log.warn("Brak środków u użytkownika: {}", sender.getUsername());
            throw new BusinessException("Niewystarczające środki w portfelu");
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

        log.info("Przelew zakończony sukcesem.");
    }
}