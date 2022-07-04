package com.cc.test301.book.rest;

import com.cc.test301.book.model.Book;
import com.cc.test301.book.model.enums.BookCategories;
import com.cc.test301.book.service.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class BookControllerComponentTest {

	@MockBean
	private BookService bookService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void creatingABookShouldReturnSuccessful() throws Exception {
		Book newBook = createNewBook();
		given(bookService.createBook(any(Book.class))).willReturn(newBook);

		mockMvc.perform(post("/books")
				                .contentType(MediaType.APPLICATION_JSON)
				                .content(getBodyAsString(newBook)))
		       .andExpect(status().isOk())
		       .andExpect(jsonPath("$.isbn", notNullValue()));
	}

	@Test
	void shouldReportFailuresToCreateBooks() throws Exception {
		given(bookService.createBook(any(Book.class))).willThrow(new RuntimeException());

		mockMvc.perform(post("/books")
				                .contentType(MediaType.APPLICATION_JSON)
				                .content(getBodyAsString(createNewBook())))
		       .andExpect(status().is5xxServerError());
	}

	@Test
	void shouldFailForEditingUnExistentBooks() throws Exception {
		Book nonExistingBook = getBook();
		given(bookService.updateBook(nonExistingBook)).willThrow(new IllegalArgumentException("book not found"));

		mockMvc.perform(put("/books/{id}", nonExistingBook.getId())
				                .contentType(MediaType.APPLICATION_JSON)
				                .content(getBodyAsString(nonExistingBook)))
		       .andExpect(status().isNotFound())
		       .andExpect(jsonPath("$.message", is("book not found")));
	}

	@Test
	void shouldEditABookSuccessfully() throws Exception {
		Book existingBook = getBook();
		given(bookService.updateBook(existingBook)).willReturn(existingBook);

		mockMvc.perform(put("/books/{id}", existingBook.getId().toString())
				                .contentType(MediaType.APPLICATION_JSON)
				                .content(getBodyAsString(existingBook)))
		       .andExpect(status().isOk());
	}

	private Book getBook() {
		UUID bookId = UUID.randomUUID();
		Book existingBook = createNewBook();
		existingBook.setId(bookId);
		return existingBook;
	}

	private <T> String getBodyAsString(T t) {
		try {
			return new ObjectMapper().writeValueAsString(t);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("problem getting json", e);
		}
	}

	private Book createNewBook() {
		Book book = new Book();
		book.setIsbn(RandomStringUtils.randomNumeric(9));
		book.setCategories(Set.of(BookCategories.TECH));
		return book;
	}
}