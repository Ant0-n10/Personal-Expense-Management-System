package Ant0_n10.financas.services;

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
import java.util.List;

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

    public TransactionDTO.BalanceResponseDTO getBalance(){
        BigDecimal totalIncome = transactionRepository.sumTypeTransaction(TypeTransaction.INCOME);
        BigDecimal totalExpense = transactionRepository.sumTypeTransaction(TypeTransaction.EXPENSE);

        totalIncome = (totalIncome != null) ? totalIncome : BigDecimal.ZERO;
        totalExpense = (totalExpense != null) ? totalExpense : BigDecimal.ZERO;

        BigDecimal currentBalance = totalIncome.subtract(totalExpense);

        return new TransactionDTO.BalanceResponseDTO(totalIncome, totalExpense, currentBalance);
    }


    public List<TransactionDTO.Response> getAllFiltered(Long categoryId, LocalDate startDate, LocalDate endDate){
        List<Transaction> transactions;

        if (categoryId != null && startDate != null && endDate != null){
            transactions = transactionRepository.findByCategoryIdAndDateBetween(categoryId, startDate, endDate);
        }
        else if (categoryId != null) {transactions = transactionRepository.findByCategoryId(categoryId);
        }
        else if (startDate != null && endDate != null) {
            transactions = transactionRepository.findByDateBetween(startDate,endDate);
        }
        else {
            transactions = transactionRepository.findAll();
        }

        return transactions.stream().map(transactionMapper::toResponseDTO).toList();
    }

    public TransactionDTO.Response updateTransaction(Long id, TransactionDTO.Update update){
       Transaction transaction = transactionRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Id not found " + id));

       if(update.categoryId() != null){
           Category category = categoryRepository.findById(update.categoryId())
                   .orElseThrow(() -> new RuntimeException("Id not found: " + update.categoryId()));
           transaction.setCategory(category);
       }
       transactionMapper.toUpdate(update, transaction);

        if (update.value() != null) {
            transaction.setValue(update.value().abs());
        }

       return transactionMapper.toResponseDTO(transactionRepository.save(transaction));
    }

    public void deleteTransaction(Long id){
        if (!transactionRepository.existsById(id)){
            throw new RuntimeException("Id not Found: " + id);
        }

        transactionRepository.deleteById(id);
    }

}
