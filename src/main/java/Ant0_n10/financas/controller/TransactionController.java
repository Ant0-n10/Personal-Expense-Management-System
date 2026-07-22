package Ant0_n10.financas.controller;

import Ant0_n10.financas.dtos.TransactionDTO;
import Ant0_n10.financas.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionDTO.Response> createTransaction(@RequestBody TransactionDTO.Request request){
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.createTransaction(request));
    }

}
