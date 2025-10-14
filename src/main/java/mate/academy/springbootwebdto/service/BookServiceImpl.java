package mate.academy.springbootwebdto.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import mate.academy.springbootwebdto.dto.BookDto;
import mate.academy.springbootwebdto.dto.CreateBookRequestDto;
import mate.academy.springbootwebdto.mapper.BookMapper;
import mate.academy.springbootwebdto.model.Book;
import mate.academy.springbootwebdto.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDto save(CreateBookRequestDto dto) {
        Book book = bookMapper.toModel(dto);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public Page<BookDto> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable)
                .map(bookMapper::toDto);
    }

    @Override
    public BookDto findById(long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found: id=" + id));
        return bookMapper.toDto(book);
    }

    @Override
    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }
}
