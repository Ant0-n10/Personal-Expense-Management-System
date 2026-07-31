package Ant0_n10.financas.controller;

import Ant0_n10.financas.dtos.TransactionDTO;
import Ant0_n10.financas.services.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionDTO.Response> createTransaction(@Valid @RequestBody TransactionDTO.Request request){
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.createTransaction(request));
    }

    @GetMapping("/balance")
    public ResponseEntity<TransactionDTO.BalanceResponseDTO> getBalance(){
        return ResponseEntity.status(HttpStatus.OK).body(transactionService.getBalance());
    }

    @GetMapping
    public ResponseEntity<List<TransactionDTO.Response>> getAllFiltered(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate){
        var response = transactionService.getAllFiltered(categoryId,startDate,endDate);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping
    public ResponseEntity<TransactionDTO.Response> updateTransaction(
            @Valid @PathVariable Long id, @Valid @RequestBody TransactionDTO.Update update){
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.updateTransaction(id, update));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteTransaction(Long id){
        transactionService.deleteTransaction(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
