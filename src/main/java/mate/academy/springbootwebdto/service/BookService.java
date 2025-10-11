package mate.academy.springbootwebdto.service;

import java.util.List;
import mate.academy.springbootwebdto.dto.BookDto;
import mate.academy.springbootwebdto.dto.CreateBookRequestDto;
import org.springframework.data.domain.Pageable;

public interface BookService {

    public BookDto save(CreateBookRequestDto dto);

    public List<BookDto> findAll(Pageable pageable);

    public BookDto findById(long id);

    public void deleteById(long id);
}
