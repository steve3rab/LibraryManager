package com.iloo.librarymanager.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * JUnit 5 test class for the Publisher class.
 */
class PublisherIT {

	@Test
	@DisplayName("Test Publisher equality")
	void testPublisherEquality() {
		// Create two instances of Publisher with the same properties
		Address address = new Address("123 Main St", "City", "State", "12345", "Country");
		Contact contact = new Contact("johndoe@example.com", "123-456-7890");
		Publisher publisher1 = new Publisher("Publisher A", address, contact);
		Publisher publisher2 = new Publisher("Publisher A", address, contact);

		assertEquals(publisher1, publisher2);
	}

	@Test
	@DisplayName("Test Publisher inequality")
	void testPublisherInequality() {
		// Create two instances of Publisher with different properties
		Address address1 = new Address("123 Main St", "City", "State", "12345", "Country");
		Contact contact1 = new Contact("johndoe@example.com", "123-456-7890");
		Publisher publisher1 = new Publisher("Publisher A", address1, contact1);

		Address address2 = new Address("456 Elm St", "Town", "State", "67890", "Country");
		Contact contact2 = new Contact("janesmith@example.com", "987-654-3210");
		Publisher publisher2 = new Publisher("Publisher B", address2, contact2);

		assertNotEquals(publisher1, publisher2);
	}
}
