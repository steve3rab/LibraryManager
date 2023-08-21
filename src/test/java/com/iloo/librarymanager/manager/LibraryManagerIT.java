package com.iloo.librarymanager.manager;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.iloo.librarymanager.database.IDatabase;
import com.iloo.librarymanager.database.IDatabaseFactory;
import com.iloo.librarymanager.model.Book;
import com.iloo.librarymanager.model.User;

class LibraryManagerIT {

	@Mock
	private IDatabaseFactory mockDatabaseFactory;

	private LibraryManager libraryManager;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		@SuppressWarnings("unchecked")
		IDatabase<User> mockIDatabaseUserClass = mock(IDatabase.class);
		when(mockDatabaseFactory.createUserDatabase()).thenReturn(mockIDatabaseUserClass);
		@SuppressWarnings("unchecked")
		IDatabase<Book> mockIDatabaseBookClass = mock(IDatabase.class);
		when(mockDatabaseFactory.createBookDatabase()).thenReturn(mockIDatabaseBookClass);
		libraryManager = LibraryManager.getInstance(mockDatabaseFactory);
	}

	@Test
	void getUserDatabase_ShouldReturnNonNullDatabase() {
		IDatabase<User> userDatabase = libraryManager.getUserDatabase();
		assertNotNull(userDatabase);
	}

	@Test
	void getBookDatabase_ShouldReturnNonNullDatabase() {
		IDatabase<Book> bookDatabase = libraryManager.getBookDatabase();
		assertNotNull(bookDatabase);
	}

}
