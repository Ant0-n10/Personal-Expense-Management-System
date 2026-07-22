package Ant0_n10.financas.services;

import Ant0_n10.financas.Mappers.CategoryMapper;
import Ant0_n10.financas.Mappers.TransactionMapper;
import Ant0_n10.financas.dtos.TransactionDTO;
import Ant0_n10.financas.enumerations.TypeTransaction;
import Ant0_n10.financas.models.Transaction;
import Ant0_n10.financas.repository.CategoryRepository;
import Ant0_n10.financas.repository.TransactionRepository;
import Ant0_n10.financas.models.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final CategoryRepository categoryRepository;

    public TransactionDTO.Response createTransaction(TransactionDTO.Request transactionDTO){
        //negative values treatment
        BigDecimal positive = transactionDTO.value().abs();
        boolean isFutureDate = transactionDTO.date().isAfter(LocalDate.now());
        boolean isINCOME = transactionDTO.typeTransaction() == TypeTransaction.INCOME;

        //validation for date
        if( isFutureDate && isINCOME){
            throw new IllegalArgumentException("Future income projections do not apply");
        }
        Category category = categoryRepository.findById(transactionDTO.categoryId())
                .orElseThrow(() -> new IllegalArgumentException("ID Not Found: " + transactionDTO.categoryId()));

        Transaction transaction = transactionMapper.toEntity(transactionDTO);
        transaction.setValue(positive);
        transaction.setCategory(category);

        Transaction savedTransaction = transactionRepository.save(transaction);
        return transactionMapper.toResponseDTO(savedTransaction);
    }
}
