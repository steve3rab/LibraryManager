package com.iloo.librarymanager.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * JUnit 5 test class for the User class.
 */
class UserIT {

	@Test
	@DisplayName("Test User equality")
	void testUserEquality() {
		// Create two instances of User with the same properties
		User.Builder builder1 = new User.Builder(1, "John", "Doe");
		builder1.age(30).gender("Male").hasLateFees(false);
		builder1.occupation("Engineer").libraryCardNumber("123456");
		builder1.isRestricted(false);
		User user1 = builder1.build();

		User.Builder builder2 = new User.Builder(1, "John", "Doe");
		builder2.age(30).gender("Male").hasLateFees(false);
		builder2.occupation("Engineer").libraryCardNumber("123456");
		builder2.isRestricted(false);
		User user2 = builder2.build();

		assertEquals(user1, user2);
	}

	@Test
	@DisplayName("Test User inequality")
	void testUserInequality() {
		// Create two instances of User with different properties
		User.Builder builder1 = new User.Builder(1, "John", "Doe");
		builder1.age(30).gender("Male").hasLateFees(false);
		builder1.address(mock(Address.class)).contact(mock(Contact.class));
		builder1.occupation("Engineer").libraryCardNumber("123456");
		builder1.isRestricted(false);
		User user1 = builder1.build();

		User.Builder builder2 = new User.Builder(2, "Jane", "Smith");
		builder2.age(25).gender("Female").hasLateFees(true);
		builder1.address(mock(Address.class)).contact(mock(Contact.class));
		builder2.occupation("Doctor").libraryCardNumber("789012");
		builder2.isRestricted(true);
		User user2 = builder2.build();

		assertNotEquals(user1, user2);
	}
}
