package com.iloo.librarymanager.database;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.iloo.librarymanager.exception.BookException;
import com.iloo.librarymanager.model.Book;

/**
 * JUnit 5 test class for BookDatabaseIT.
 */
class BookDatabaseIT {

	private Book book1;
	private Book book2;
	private Book book3;

	private List<Book> mockBooks;
	private IDatabase<Book> bookDatabase;

	@BeforeEach
	void setUp() {
		book1 = mock(Book.class);
		when(book1.getId()).thenReturn(1);

		book2 = mock(Book.class);
		when(book2.getId()).thenReturn(2);

		book3 = mock(Book.class);
		when(book3.getId()).thenReturn(3);

		// Mock Book objects
		when(book1.getTitle()).thenReturn("Java");
		when(book2.getTitle()).thenReturn("Shell");

		mockBooks = Arrays.asList(book1, book2);
		bookDatabase = new BookDatabase(mockBooks);
	}

	@Test
	@DisplayName("Test getBookById with existing book")
	void testGetBookByIdExisting() {
		Optional<Book> book = bookDatabase.getById(1);

		assertTrue(book.isPresent());
		assertEquals("Java", book.get().getTitle());
	}

	@Test
	@DisplayName("Test getBookById with non-existing book")
	void testGetBookByIdNonExisting() {
		Optional<Book> book = bookDatabase.getById(3);

		assertFalse(book.isPresent());
	}

	@Test
	@DisplayName("Test addBook with unique book")
	void testAddBookUnique() {
		List<Book> mockBook1 = Collections.singletonList(book1);
		IDatabase<Book> bookDatabase1 = new BookDatabase(mockBook1);

		Book newBook = mock(Book.class);
		when(newBook.getId()).thenReturn(2);
		when(newBook.getTitle()).thenReturn("Shell");

		assertDoesNotThrow(() -> bookDatabase1.add(newBook));
	}

	@Test
	@DisplayName("Test addBook with duplicate book")
	void testAddBookDuplicate() {
		List<Book> mockBook1 = Collections.singletonList(book1);
		IDatabase<Book> bookDatabase1 = new BookDatabase(mockBook1);

		Book duplicateBook = mock(Book.class);
		when(duplicateBook.getId()).thenReturn(1); // Same ID as book1
		when(duplicateBook.getTitle()).thenReturn("Shell");

		assertThrows(BookException.class, () -> bookDatabase1.add(duplicateBook));
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
		Book updatedBook = mock(Book.class);
		when(updatedBook.getId()).thenReturn(1);
		when(updatedBook.getTitle()).thenReturn("Updated Java");

		assertDoesNotThrow(() -> bookDatabase.update(updatedBook));
	}

	@Test
	@DisplayName("Test updateBook with non-existing book")
	void testUpdateBookNonExisting() {
		when(book3.getTitle()).thenReturn("Updated Book");

		assertThrows(BookException.class, () -> bookDatabase.update(book3));
	}

	@Test
	@DisplayName("Test deleteBook with existing book")
	void testDeleteBookExisting() {
		assertDoesNotThrow(() -> bookDatabase.delete(1));

		verify(book1, times(1)).getId(); // Verifies that getId was called
	}

	@Test
	@DisplayName("Test deleteBook with non-existing book")
	void testDeleteBookNonExisting() {
		assertThrows(BookException.class, () -> bookDatabase.delete(3));
	}

	@Test
	@DisplayName("Test forEach on book")
	void testForEach() {
		@SuppressWarnings("unchecked")
		Consumer<Book> mockConsumer = mock(Consumer.class);
		bookDatabase.forEach(mockConsumer);

		verify(mockConsumer, times(2)).accept(any());
	}

	@Test
	@DisplayName("Test create on book")
	void testCreate() {
		Supplier<Book> bookSupplier = () -> book3;

		Book createdBook = bookDatabase.create(bookSupplier);

		assertNotNull(createdBook);
		assertEquals(3, createdBook.getId());
	}

	@Test
	@DisplayName("Test filter on book")
	void testFilter() {
		Predicate<Book> predicate = book -> book.getId() == 2;

		List<Book> filteredBooks = bookDatabase.filter(predicate);

		assertEquals(1, filteredBooks.size());
		assertTrue(filteredBooks.contains(book2));
	}
}
