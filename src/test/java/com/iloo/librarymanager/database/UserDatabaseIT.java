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

import com.iloo.librarymanager.exception.UserException;
import com.iloo.librarymanager.model.User;

/**
 * JUnit 5 test class for IUserDatabase.
 */
class UserDatabaseIT {

	@Test
	@DisplayName("Test getUserById with existing user")
	void testGetUserByIdExisting() {
		// Mock User objects
		User user1 = mock(User.class);
		when(user1.getId()).thenReturn(1);
		when(user1.getFirstName()).thenReturn("John");

		User user2 = mock(User.class);
		when(user2.getId()).thenReturn(2);
		when(user2.getFirstName()).thenReturn("Jane");

		List<User> mockUsers = Arrays.asList(user1, user2);
		IDatabase<User> userDatabase = new UserDatabase(mockUsers);

		Optional<User> user = userDatabase.getById(1);

		assertTrue(user.isPresent());
		assertEquals("John", user.get().getFirstName());
	}

	@Test
	@DisplayName("Test getUserById with non-existing user")
	void testGetUserByIdNonExisting() {
		User user1 = mock(User.class);
		when(user1.getId()).thenReturn(1);
		when(user1.getFirstName()).thenReturn("John");

		User user2 = mock(User.class);
		when(user2.getId()).thenReturn(2);
		when(user2.getFirstName()).thenReturn("Jane");

		List<User> mockUsers = Arrays.asList(user1, user2);
		IDatabase<User> userDatabase = new UserDatabase(mockUsers);

		Optional<User> user = userDatabase.getById(3);

		assertFalse(user.isPresent());
	}

	@Test
	@DisplayName("Test addUser with unique user")
	void testAddUserUnique() {
		User user1 = mock(User.class);
		when(user1.getId()).thenReturn(1);
		when(user1.getFirstName()).thenReturn("John");

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
		User user1 = mock(User.class);
		when(user1.getId()).thenReturn(1);
		when(user1.getFirstName()).thenReturn("John");

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
		User user1 = mock(User.class);
		when(user1.getId()).thenReturn(1);
		when(user1.getFirstName()).thenReturn("John");

		User user2 = mock(User.class);
		when(user2.getId()).thenReturn(2);
		when(user2.getFirstName()).thenReturn("Jane");

		List<User> mockUsers = Arrays.asList(user1, user2);
		IDatabase<User> userDatabase = new UserDatabase(mockUsers);

		User updatedUser = mock(User.class);
		when(updatedUser.getId()).thenReturn(1);
		when(updatedUser.getFirstName()).thenReturn("Updated John");

		assertDoesNotThrow(() -> userDatabase.update(updatedUser));
	}

	@Test
	@DisplayName("Test updateUser with non-existing user")
	void testUpdateUserNonExisting() {
		User user1 = mock(User.class);
		when(user1.getId()).thenReturn(1);
		when(user1.getFirstName()).thenReturn("John");

		User user2 = mock(User.class);
		when(user2.getId()).thenReturn(2);
		when(user2.getFirstName()).thenReturn("Jane");

		List<User> mockUsers = Arrays.asList(user1, user2);
		IDatabase<User> userDatabase = new UserDatabase(mockUsers);

		User updatedUser = mock(User.class);
		when(updatedUser.getId()).thenReturn(3);
		when(updatedUser.getFirstName()).thenReturn("Updated User");

		assertThrows(UserException.class, () -> userDatabase.update(updatedUser));
	}

	@Test
	@DisplayName("Test deleteUser with existing user")
	void testDeleteUserExisting() {
		User user1 = mock(User.class);
		when(user1.getId()).thenReturn(1);
		when(user1.getFirstName()).thenReturn("John");

		User user2 = mock(User.class);
		when(user2.getId()).thenReturn(2);
		when(user2.getFirstName()).thenReturn("Jane");

		List<User> mockUsers = Arrays.asList(user1, user2);
		IDatabase<User> userDatabase = new UserDatabase(mockUsers);

		assertDoesNotThrow(() -> userDatabase.delete(1));

		verify(user1, times(1)).getId(); // Verifies that getId was called
	}

	@Test
	@DisplayName("Test deleteUser with non-existing user")
	void testDeleteUserNonExisting() {
		User user1 = mock(User.class);
		when(user1.getId()).thenReturn(1);
		when(user1.getFirstName()).thenReturn("John");

		User user2 = mock(User.class);
		when(user2.getId()).thenReturn(2);
		when(user2.getFirstName()).thenReturn("Jane");

		List<User> mockUsers = Arrays.asList(user1, user2);
		IDatabase<User> userDatabase = new UserDatabase(mockUsers);

		assertThrows(UserException.class, () -> userDatabase.delete(3));
	}
}
