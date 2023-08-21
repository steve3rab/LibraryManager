package com.iloo.librarymanager.database;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.iloo.librarymanager.exception.BookException;
import com.iloo.librarymanager.model.Book;

/**
 * JUnit 5 test class for BookDatabaseIT.
 */
class BookDatabaseIT {

	@Test
	@DisplayName("Test getBookById with existing book")
	void testGetBookByIdExisting() {
		// Mock Book objects
		Book book1 = mock(Book.class);
		when(book1.getId()).thenReturn(1);
		when(book1.getTitle()).thenReturn("Java");

		Book book2 = mock(Book.class);
		when(book2.getId()).thenReturn(2);
		when(book2.getTitle()).thenReturn("Shell");

		List<Book> mockBooks = Arrays.asList(book1, book2);
		IDatabase<Book> bookDatabase = new BookDatabase(mockBooks);

		Optional<Book> book = bookDatabase.getById(1);

		assertTrue(book.isPresent());
		assertEquals("Java", book.get().getTitle());
	}

	@Test
	@DisplayName("Test getBookById with non-existing book")
	void testGetBookByIdNonExisting() {
		Book book1 = mock(Book.class);
		when(book1.getId()).thenReturn(1);
		when(book1.getTitle()).thenReturn("Java");

		Book book2 = mock(Book.class);
		when(book2.getId()).thenReturn(2);
		when(book2.getTitle()).thenReturn("Shell");

		List<Book> mockBooks = Arrays.asList(book1, book2);
		IDatabase<Book> bookDatabase = new BookDatabase(mockBooks);

		Optional<Book> book = bookDatabase.getById(3);

		assertFalse(book.isPresent());
	}

	@Test
	@DisplayName("Test addBook with unique book")
	void testAddBookUnique() {
		Book book1 = mock(Book.class);
		when(book1.getId()).thenReturn(1);
		when(book1.getTitle()).thenReturn("Java");

		List<Book> mockBooks = Collections.singletonList(book1);
		IDatabase<Book> bookDatabase = new BookDatabase(mockBooks);

		Book newBook = mock(Book.class);
		when(newBook.getId()).thenReturn(2);
		when(newBook.getTitle()).thenReturn("Shell");

		assertDoesNotThrow(() -> bookDatabase.add(newBook));
	}

	@Test
	@DisplayName("Test addBook with duplicate book")
	void testAddBookDuplicate() {
		Book book1 = mock(Book.class);
		when(book1.getId()).thenReturn(1);
		when(book1.getTitle()).thenReturn("Java");

		List<Book> mockBooks = Collections.singletonList(book1);
		IDatabase<Book> bookDatabase = new BookDatabase(mockBooks);

		Book duplicateBook = mock(Book.class);
		when(duplicateBook.getId()).thenReturn(1); // Same ID as book1
		when(duplicateBook.getTitle()).thenReturn("Shell");

		assertThrows(BookException.class, () -> bookDatabase.add(duplicateBook));
	}

	@Test
	@DisplayName("Test createBookDatabase from DatabaseFactory")
	void testCreateBookDatabase() {
		IDatabaseFactory databaseFactory = new DatabaseFactory();
		IDatabase<Book> bookDatabase = databaseFactory.createBookDatabase();

		assertNotNull(bookDatabase);
	}

	@Test
	@DisplayName("Test updateBook with existing book")
	void testUpdateBookExisting() {
		Book book1 = mock(Book.class);
		when(book1.getId()).thenReturn(1);
		when(book1.getTitle()).thenReturn("Java");

		Book book2 = mock(Book.class);
		when(book2.getId()).thenReturn(2);
		when(book2.getTitle()).thenReturn("Shell");

		List<Book> mockBooks = Arrays.asList(book1, book2);
		IDatabase<Book> bookDatabase = new BookDatabase(mockBooks);

		Book updatedBook = mock(Book.class);
		when(updatedBook.getId()).thenReturn(1);
		when(updatedBook.getTitle()).thenReturn("Updated Java");

		assertDoesNotThrow(() -> bookDatabase.update(updatedBook));
	}

	@Test
	@DisplayName("Test updateBook with non-existing book")
	void testUpdateBookNonExisting() {
		Book book1 = mock(Book.class);
		when(book1.getId()).thenReturn(1);
		when(book1.getTitle()).thenReturn("Java");

		Book book2 = mock(Book.class);
		when(book2.getId()).thenReturn(2);
		when(book2.getTitle()).thenReturn("Shell");

		List<Book> mockBooks = Arrays.asList(book1, book2);
		IDatabase<Book> bookDatabase = new BookDatabase(mockBooks);

		Book updatedBook = mock(Book.class);
		when(updatedBook.getId()).thenReturn(3);
		when(updatedBook.getTitle()).thenReturn("Updated Book");

		assertThrows(BookException.class, () -> bookDatabase.update(updatedBook));
	}

	@Test
	@DisplayName("Test deleteBook with existing book")
	void testDeleteBookExisting() {
		Book book1 = mock(Book.class);
		when(book1.getId()).thenReturn(1);
		when(book1.getTitle()).thenReturn("Java");

		Book book2 = mock(Book.class);
		when(book2.getId()).thenReturn(2);
		when(book2.getTitle()).thenReturn("Shell");

		List<Book> mockBooks = Arrays.asList(book1, book2);
		IDatabase<Book> bookDatabase = new BookDatabase(mockBooks);

		assertDoesNotThrow(() -> bookDatabase.delete(1));

		verify(book1, times(1)).getId(); // Verifies that getId was called
	}

	@Test
	@DisplayName("Test deleteBook with non-existing book")
	void testDeleteBookNonExisting() {
		Book book1 = mock(Book.class);
		when(book1.getId()).thenReturn(1);
		when(book1.getTitle()).thenReturn("Java");

		Book book2 = mock(Book.class);
		when(book2.getId()).thenReturn(2);
		when(book2.getTitle()).thenReturn("Shell");

		List<Book> mockBooks = Arrays.asList(book1, book2);
		IDatabase<Book> bookDatabase = new BookDatabase(mockBooks);

		assertThrows(BookException.class, () -> bookDatabase.delete(3));
	}
}
