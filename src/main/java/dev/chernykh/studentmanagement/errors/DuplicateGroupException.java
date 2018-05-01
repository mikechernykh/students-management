package dev.chernykh.studentmanagement.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DuplicateGroupException extends RuntimeException {
    public DuplicateGroupException() {
        super();
    }

    public DuplicateGroupException(String message) {
        super(message);
    }
}
