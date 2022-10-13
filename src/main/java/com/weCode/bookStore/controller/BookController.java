package com.weCode.bookStore.controller;

import com.weCode.bookStore.dto.BookDto;
import com.weCode.bookStore.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
//@CrossOrigin
@RequestMapping("api/v1/books")
public class BookController {

    @Autowired
    private BookService bookService;
    @Operation(summary = "Get a book by its id", tags = "Book Store API", description = "Book Store API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the book",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BookDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content) })
    @GetMapping
    public ResponseEntity<List<BookDto>> getBooks(){
        List<BookDto> books = bookService.getBooks();
            return ResponseEntity.ok(books);
    }

}
