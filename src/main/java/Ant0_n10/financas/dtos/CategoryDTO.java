package Ant0_n10.financas.dtos;

public final class CategoryDTO {

    public record Request(
            String name
    ) {}

    public record Response(
            Long id,
            String name
    ){}

    public record Update(
            String name
    ){}
}
