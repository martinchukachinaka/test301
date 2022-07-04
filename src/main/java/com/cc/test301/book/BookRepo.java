package com.cc.test301.book;

import com.cc.test301.book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepo extends JpaRepository<Book, UUID> {

	Optional<Book> findByIsbn(String isbn);
}
