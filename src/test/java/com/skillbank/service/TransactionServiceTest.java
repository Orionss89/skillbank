package com.skillbank.service;

import com.skillbank.dto.TransactionDTO;
import com.skillbank.model.User;
import com.skillbank.model.Wallet;
import com.skillbank.repository.TransactionRepository;
import com.skillbank.repository.UserRepository;
import com.skillbank.repository.WalletRepository; // Nowy mock
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Mock private UserRepository userRepository;
    @Mock private TransactionRepository transactionRepository;
    @Mock private WalletRepository walletRepository;

    @InjectMocks private TransactionService transactionService;

    @Test
    public void testTransferHours_Success() {
        User sender = new User(); sender.setId(1L);
        Wallet senderWallet = new Wallet(); senderWallet.setBalance(10);
        sender.setWallet(senderWallet);

        User receiver = new User(); receiver.setId(2L);
        Wallet receiverWallet = new Wallet(); receiverWallet.setBalance(5);
        receiver.setWallet(receiverWallet);

        when(userRepository.findById(1L)).thenReturn(Optional.of(sender));
        when(userRepository.findById(2L)).thenReturn(Optional.of(receiver));

        TransactionDTO dto = new TransactionDTO();
        dto.setSenderId(1L);
        dto.setReceiverId(2L);
        dto.setAmount(2);

        // ACT
        transactionService.transferHours(dto);

        // ASSERT
        assertEquals(8, senderWallet.getBalance());
        assertEquals(7, receiverWallet.getBalance());

        verify(walletRepository, times(1)).save(senderWallet);
        verify(walletRepository, times(1)).save(receiverWallet);
    }
}