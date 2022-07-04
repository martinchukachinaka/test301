package com.cc.test301.book.rest;

import com.cc.test301.book.model.Book;
import com.cc.test301.book.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("books")
public class BookController {

	private final BookService bookService;

	@PostMapping
	public ResponseEntity<?> createBook(@Valid @RequestBody Book book) {
		log.info("create book: {}", book);
		return ResponseEntity.ok(bookService.createBook(book));
	}

	@PutMapping("{id}")
	public ResponseEntity<?> updateBook(@Valid @RequestBody Book book, @PathVariable UUID id) {
		log.info("update book: {}", book);
		book.setId(id);
		return ResponseEntity.ok(bookService.updateBook(book));
	}
}
