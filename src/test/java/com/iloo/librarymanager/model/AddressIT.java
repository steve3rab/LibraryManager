package com.iloo.librarymanager.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * JUnit 5 test class for the Address class.
 */
class AddressIT {

	@Test
	@DisplayName("Test Address equality")
	void testAddressEquality() {
		// Create two instances of Address with the same properties
		Address address1 = new Address("123 Main St", "City", "State", "12345", "Country");
		Address address2 = new Address("123 Main St", "City", "State", "12345", "Country");

		assertEquals(address1, address2);
	}

	@Test
	@DisplayName("Test Address inequality")
	void testAddressInequality() {
		// Create two instances of Address with different properties
		Address address1 = new Address("123 Main St", "City", "State", "12345", "Country");
		Address address2 = new Address("456 Elm St", "Town", "Province", "67890", "DifferentCountry");

		assertNotEquals(address1, address2);
	}

}
