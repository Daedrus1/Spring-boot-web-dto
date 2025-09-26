package mate.academy.springbootwebdto.service;

import mate.academy.springbootwebdto.dto.BookDto;
import mate.academy.springbootwebdto.dto.CreateBookRequestDto;

import java.util.List;

public interface BookService {

    public BookDto save(CreateBookRequestDto dto);

    public List<BookDto> findAll();

    public BookDto findById(long id);
}
