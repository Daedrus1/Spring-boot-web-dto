package mate.academy.springbootwebdto.service;

import mate.academy.springbootwebdto.dto.BookDto;
import mate.academy.springbootwebdto.dto.CreateBookRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {

    public BookDto save(CreateBookRequestDto dto);

    public Page<BookDto> findAll(Pageable pageable);

    public BookDto findById(long id);

    public void deleteById(long id);
}
