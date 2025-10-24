package mate.academy.springbootwebdto.mapper;

import mate.academy.springbootwebdto.dto.BookDto;
import mate.academy.springbootwebdto.dto.CreateBookRequestDto;
import mate.academy.springbootwebdto.model.Book;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(target = "deleted", source = "deleted")
    BookDto toDto(Book book);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", constant = "false")
    Book toModel(CreateBookRequestDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateBookFromDto(CreateBookRequestDto dto, @MappingTarget Book book);
}
