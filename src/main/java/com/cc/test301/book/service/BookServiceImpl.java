package com.cc.test301.book.service;

import com.cc.test301.book.model.Book;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

	public Book createBook(Book book) {
		return book;
	}

	public Book updateBook(Book book) {
		return book;
	}
}
