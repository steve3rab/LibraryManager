package com.iloo.librarymanager.database;

import com.iloo.librarymanager.model.Book;
import com.iloo.librarymanager.model.User;

/**
 * Interface for the Database Factory.
 */
public interface IDatabaseFactory {
	IDatabase<Book> createBookDatabase();

	IDatabase<User> createUserDatabase();
}
