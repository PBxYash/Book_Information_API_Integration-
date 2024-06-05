package com.Book_Information.Controller;

import com.Book_Information.Entity.ErrorResponse;
import com.Book_Information.Entity.BookRequest;
import com.Book_Information.Entity.BookResponse;
import com.Book_Information.Exception.CustomException;
import com.Book_Information.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<?> getBookDetails(@RequestBody BookRequest bookRequest) {
        try {
            BookResponse bookResponse = bookService.getBookDetails(bookRequest.getBookIsbn());
            return new ResponseEntity<>(bookResponse, HttpStatus.OK);
        } catch (CustomException e) {
            ErrorResponse errorResponse = new ErrorResponse("There was an error", e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse("An unexpected error occurred", e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}