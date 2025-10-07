package mate.academy.springbootwebdto.service;

import java.util.List;
import mate.academy.springbootwebdto.dto.BookDto;
import mate.academy.springbootwebdto.dto.CreateBookRequestDto;

public interface BookService {

    public BookDto save(CreateBookRequestDto dto);

    public List<BookDto> findAll();

    public BookDto findById(long id);
}
