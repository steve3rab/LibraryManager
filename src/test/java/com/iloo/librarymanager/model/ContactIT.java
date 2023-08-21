package com.iloo.librarymanager.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * JUnit 5 test class for the Contact class.
 */
class ContactIT {

	@Test
	@DisplayName("Test Contact equality")
	void testContactEquality() {
		// Create two instances of Contact with the same properties
		Contact contact1 = new Contact("johndoe@example.com", "123-456-7890");
		Contact contact2 = new Contact("johndoe@example.com", "123-456-7890");

		assertEquals(contact1, contact2);
	}

	@Test
	@DisplayName("Test Contact inequality")
	void testContactInequality() {
		// Create two instances of Contact with different properties
		Contact contact1 = new Contact("johndoe@example.com", "123-456-7890");
		Contact contact2 = new Contact("janesmith@example.com", "987-654-3210");

		assertNotEquals(contact1, contact2);
	}
}
