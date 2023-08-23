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

import com.iloo.librarymanager.exception.UserException;
import com.iloo.librarymanager.model.User;

/**
 * JUnit 5 test class for IUserDatabase.
 */
class UserDatabaseIT {

	private User user1;
	private User user2;
	private User user3;

	private List<User> mockUsers;
	private IDatabase<User> userDatabase;

	@BeforeEach
	void setUp() {
		user1 = mock(User.class);
		when(user1.getId()).thenReturn(1);

		user2 = mock(User.class);
		when(user2.getId()).thenReturn(2);

		user3 = mock(User.class);
		when(user3.getId()).thenReturn(3);

		// Mock User objects
		when(user1.getFirstName()).thenReturn("John");
		when(user2.getFirstName()).thenReturn("Jane");

		mockUsers = Arrays.asList(user1, user2);
		userDatabase = new UserDatabase(mockUsers);
	}

	@Test
	@DisplayName("Test getUserById with existing user")
	void testGetUserByIdExisting() {
		Optional<User> user = userDatabase.getById(1);

		assertTrue(user.isPresent());
		assertEquals("John", user.get().getFirstName());
	}

	@Test
	@DisplayName("Test getUserById with non-existing user")
	void testGetUserByIdNonExisting() {
		Optional<User> user = userDatabase.getById(3);

		assertFalse(user.isPresent());
	}

	@Test
	@DisplayName("Test addUser with unique user")
	void testAddUserUnique() {
		List<User> mockUsers = Collections.singletonList(user1);
		IDatabase<User> userDatabase = new UserDatabase(mockUsers);

		User newUser = mock(User.class);
		when(newUser.getId()).thenReturn(2);
		when(newUser.getFirstName()).thenReturn("Jane");

		assertDoesNotThrow(() -> userDatabase.add(newUser));
	}

	@Test
	@DisplayName("Test addUser with duplicate user")
	void testAddUserDuplicate() {
		List<User> mockUsers = Collections.singletonList(user1);
		IDatabase<User> userDatabase = new UserDatabase(mockUsers);

		User duplicateUser = mock(User.class);
		when(duplicateUser.getId()).thenReturn(1); // Same ID as user1
		when(duplicateUser.getFirstName()).thenReturn("Jane");

		assertThrows(UserException.class, () -> userDatabase.add(duplicateUser));
	}

	@Test
	@DisplayName("Test createUserDatabase from DatabaseFactory")
	void testCreateUserDatabase() {
		IDatabaseFactory databaseFactory = new DatabaseFactory();
		IDatabase<User> userDatabase = databaseFactory.createUserDatabase();

		assertNotNull(userDatabase);
	}

	@Test
	@DisplayName("Test updateUser with existing user")
	void testUpdateUserExisting() {
		User updatedUser = mock(User.class);
		when(updatedUser.getId()).thenReturn(1);
		when(updatedUser.getFirstName()).thenReturn("Updated John");

		assertDoesNotThrow(() -> userDatabase.update(updatedUser));
	}

	@Test
	@DisplayName("Test updateUser with non-existing user")
	void testUpdateUserNonExisting() {
		when(user3.getFirstName()).thenReturn("Updated User");

		assertThrows(UserException.class, () -> userDatabase.update(user3));
	}

	@Test
	@DisplayName("Test deleteUser with existing user")
	void testDeleteUserExisting() {
		assertDoesNotThrow(() -> userDatabase.delete(1));

		verify(user1, times(1)).getId(); // Verifies that getId was called
	}

	@Test
	@DisplayName("Test deleteUser with non-existing user")
	void testDeleteUserNonExisting() {
		assertThrows(UserException.class, () -> userDatabase.delete(3));
	}

	@Test
	@DisplayName("Test forEach on UserDatabase")
	void testForEach() {
		@SuppressWarnings("unchecked")
		Consumer<User> mockConsumer = mock(Consumer.class);

		userDatabase.forEach(mockConsumer);

		verify(mockConsumer, times(2)).accept(any());
	}

	@Test
	@DisplayName("Test create on UserDatabase")
	void testCreate() {
		Supplier<User> userSupplier = () -> user3;

		User createdUser = userDatabase.create(userSupplier);

		assertNotNull(createdUser);
		assertEquals(3, createdUser.getId());
	}

	@Test
	@DisplayName("Test filter on UserDatabase")
	void testFilter() {
		Predicate<User> predicate = user -> user.getId() == 2;

		List<User> filteredUsers = userDatabase.filter(predicate);

		assertEquals(1, filteredUsers.size());
		assertTrue(filteredUsers.contains(user2));
	}
}
