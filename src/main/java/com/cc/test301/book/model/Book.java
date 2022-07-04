package com.cc.test301.book.model;

import com.cc.test301.book.model.enums.BookCategories;
import com.cc.test301.common.models.AppEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Book extends AppEntity {

	@NotNull
	@Column(unique = true)
	private String isbn;

	private String author;

	private int numberOfPages;

	@NotEmpty
	@ElementCollection
	@Enumerated(EnumType.STRING)
	private Set<BookCategories> categories = new HashSet<>();

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
			return false;
		}
		Book book = (Book) o;
		return getId() != null && Objects.equals(getId(), book.getId());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
