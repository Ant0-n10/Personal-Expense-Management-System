package Ant0_n10.financas.dtos;

import Ant0_n10.financas.enumerations.TypeTransaction;
import Ant0_n10.financas.models.Category;

import java.math.BigDecimal;
import java.time.LocalDate;

public final class TransactionDTO {

    public record Request(
            String description,
            BigDecimal value,
            LocalDate date,
            TypeTransaction typeTransaction,
            Long categoryId
    ){}

    public record Response(
            Long id,
            String description,
            BigDecimal value,
            LocalDate date,
            TypeTransaction typeTransaction,
            Category category
    ){}

    public record Update(
            String description,
            BigDecimal value,
            LocalDate date,
            TypeTransaction typeTransaction,
            Long categoryId
    ){}
}
