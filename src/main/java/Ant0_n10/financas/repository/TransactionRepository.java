package Ant0_n10.financas.repository;

import Ant0_n10.financas.enumerations.TypeTransaction;
import Ant0_n10.financas.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT SUM(t.value) FROM Transaction t WHERE t.typeTransaction = :type")
    BigDecimal sumTypeTransaction(@Param("type") TypeTransaction typeTransaction);
}
