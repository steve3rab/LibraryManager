package com.iloo.librarymanager.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * JUnit 5 test class for the Book class.
 */
class BookIT {

	@Test
	@DisplayName("Test Book equality")
	void testBookEquality() {
		// Create two instances of Book with the same properties
		Book.Builder builder1 = new Book.Builder(1, "Title", "Author");
		builder1.genre("Fiction").publicationYear(2020);
		builder1.language("English").pageCount(300);
		builder1.format("Hardcover").price(25.99);
		builder1.summary("A great story").isAvailable(true);
		builder1.isReferenceOnly(false).isBorrowable(true);
		Book book1 = builder1.build();

		Book.Builder builder2 = new Book.Builder(1, "Title", "Author");
		builder2.genre("Fiction").publicationYear(2020);
		builder2.language("English").pageCount(300);
		builder2.format("Hardcover").price(25.99);
		builder2.summary("A great story").isAvailable(true);
		builder2.isReferenceOnly(false).isBorrowable(true);
		Book book2 = builder2.build();

		assertEquals(book1, book2);
	}

	@Test
	@DisplayName("Test Book inequality")
	void testBookInequality() {
		// Create two instances of Book with different properties
		Book.Builder builder1 = new Book.Builder(1, "Title", "Author");
		builder1.genre("Fiction").publicationYear(2020);
		builder1.isbn("123456").publisher(mock(Publisher.class));
		builder1.language("English").pageCount(300);
		builder1.format("Hardcover").price(25.99);
		builder1.summary("A great story").isAvailable(true);
		builder1.isReferenceOnly(false).isBorrowable(true);
		Book book1 = builder1.build();

		Book.Builder builder2 = new Book.Builder(2, "Different Title", "Author");
		builder2.genre("Non-Fiction").publicationYear(2022);
		builder2.isbn("789012").publisher(mock(Publisher.class));
		builder2.language("Spanish").pageCount(250);
		builder2.format("Paperback").price(19.99);
		builder2.summary("Not as great").isAvailable(false);
		builder2.isReferenceOnly(true).isBorrowable(false);
		Book book2 = builder2.build();

		assertNotEquals(book1, book2);
	}
}
