package mate.academy.springbootwebdto.exception;

import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        List<String> errors = ex.getBindingResult().getAllErrors().stream()
                .map(this::getErrorsMassage)
                .toList();
        body.put("errors", errors);

        return new ResponseEntity<>(body, headers, HttpStatus.BAD_REQUEST);
    }

    private String getErrorsMassage(ObjectError e) {
        if (e instanceof FieldError) {
            String fieldError = ((FieldError) e).getField();
            String message = e.getDefaultMessage();
            return fieldError + " " + message;
        }
        return e.getDefaultMessage();
    }

    public ResponseEntity<Object> handleEntityNotFound(
            EntityNotFoundException ex,
            WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("error", "Resource not found");
        body.put("message", ex.getMessage());
        if (request instanceof ServletWebRequest swr) {
            body.put("path", swr.getRequest().getRequestURI());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

}
