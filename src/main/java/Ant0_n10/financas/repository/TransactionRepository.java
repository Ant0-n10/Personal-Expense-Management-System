package Ant0_n10.financas.repository;

import Ant0_n10.financas.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
