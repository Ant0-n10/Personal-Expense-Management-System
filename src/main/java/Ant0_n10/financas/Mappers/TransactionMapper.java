package Ant0_n10.financas.Mappers;

import Ant0_n10.financas.dtos.TransactionDTO;
import Ant0_n10.financas.models.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TransactionMapper {

    Transaction toEntity(TransactionDTO.Request request);

    TransactionDTO.Response toResponseDTO(Transaction transaction);

    void toUpdate(TransactionDTO.Update update, @MappingTarget Transaction transaction);
}
