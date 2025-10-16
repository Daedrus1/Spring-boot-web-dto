package mate.academy.springbootwebdto.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mate.academy.springbootwebdto.dto.BookDto;
import mate.academy.springbootwebdto.dto.CreateBookRequestDto;
import mate.academy.springbootwebdto.mapper.BookMapper;
import mate.academy.springbootwebdto.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Books")
@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    @Operation(
            summary = "List books (paginated & sorted)",
            description = "Returns a paginated and optionally sorted list of books. "
                    + "Use query params: 'page', 'size', and 'sort'. "
                    + "Example: /books?page=0&size=20&sort=title,asc"
    )
    @GetMapping
    public Page<BookDto> getAll(Pageable pageable) {
        return bookService.findAll(pageable);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get book by ID",
            description = "Fetch a single book by its identifier. "
                    + "Returns 404 if the book does not exist."
    )
    public BookDto getById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @PostMapping
    @Operation(
            summary = "Create a new book",
            description = "Creates a new book from the "
                    + "provided payload and returns the created resource. "
                    + "Returns 400 when validation fails."
    )
    public BookDto create(@RequestBody @Valid CreateBookRequestDto createBookRequestDto) {
        return bookService.save(createBookRequestDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete book by ID",
            description = "Deletes a book by its identifier. "
                    + "The operation is idempotent and returns 204 even "
                    + "if the resource did not exist."
    )
    public void deleteById(@PathVariable Long id) {
        bookService.deleteById(id);
    }

}
