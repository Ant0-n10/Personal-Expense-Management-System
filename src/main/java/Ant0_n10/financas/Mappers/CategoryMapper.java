package Ant0_n10.financas.Mappers;

import Ant0_n10.financas.dtos.CategoryDTO;
import Ant0_n10.financas.models.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CategoryMapper {

    Category toEntity(CategoryDTO.Request request);

    CategoryDTO.Response toResponseDTO(Category response);

    void toUpdate(CategoryDTO.Update update, @MappingTarget Category category);
}
