package com.iloo.librarymanager.database;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.iloo.librarymanager.exception.BookException;
import com.iloo.librarymanager.model.Book;

/**
 * Concrete implementation of BookDatabase.
 */
class BookDatabase implements IDatabase<Book> {
	private final List<Book> books;

	BookDatabase(List<Book> books) {
		this.books = Collections.unmodifiableList(books);
	}

	@Override
	public List<Book> getAll() {
		return books;
	}

	@Override
	public Optional<Book> getById(int bookId) {
		return books.stream().filter(book -> book.getId() == bookId).findFirst();
	}

	@Override
	public void add(Book book) {
		if (!getById(book.getId()).isEmpty()) {
			throw new BookException("Book with the same ID already exists.");
		}
		List<Book> updatedBooks = new ArrayList<>(books);
		updatedBooks.add(book);
	}

	@Override
	public void update(Book book) {
		List<Book> updatedBooks = new ArrayList<>(books);
		int index = -1;
		for (int i = 0; i < updatedBooks.size(); i++) {
			if (updatedBooks.get(i).getId() == book.getId()) {
				index = i;
				break;
			}
		}
		if (index == -1) {
			throw new BookException("Book with the specified ID not found.");
		}
		updatedBooks.set(index, book);
	}

	@Override
	public void delete(int bookId) {
		List<Book> updatedBooks = new ArrayList<>(books);
		if (!updatedBooks.removeIf(book -> book.getId() == bookId)) {
			throw new BookException("Book with the specified ID not found.");
		}
	}

}
