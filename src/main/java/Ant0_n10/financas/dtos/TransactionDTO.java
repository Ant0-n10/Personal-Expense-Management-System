package Ant0_n10.financas.dtos;

import Ant0_n10.financas.enumerations.TypeTransaction;
import Ant0_n10.financas.models.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public final class TransactionDTO {

    public record Request(
            @NotBlank(message = "Description cannot be blank")
            String description,

            @NotNull
            @Positive
            BigDecimal value,

            @NotNull(message = "date cannot be blank")
            LocalDate date,

            @NotNull(message = "type cannot be blank")
            TypeTransaction typeTransaction,

            @NotNull(message = "ID is Mandatory.")
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

    public record BalanceResponseDTO(
            BigDecimal totalIncome,
            BigDecimal totalExpense,
            BigDecimal currentBalance
    ){}
}
