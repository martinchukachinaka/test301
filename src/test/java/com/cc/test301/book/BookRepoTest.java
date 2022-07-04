package com.cc.test301.book;

import com.cc.test301.book.model.Book;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.junit5.api.DBRider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

@DBRider
@DataJpaTest
@DBUnit(cacheConnection = false, leakHunter = true, url = "jdbc:h2:mem:myDb;DB_CLOSE_DELAY=-1", user = "sa", password = "password", schema = "myDb")
class BookRepoTest {

	@Autowired
	private BookRepo bookRepo;

	@BeforeEach
	@DataSet("books.yml")
	void setup() {
	}

	@Test
	void testFindAll() {
		List<Book> books = bookRepo.findAll();
		assertThat(books, hasSize(3));
	}
}