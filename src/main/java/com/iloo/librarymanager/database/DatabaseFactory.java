package com.iloo.librarymanager.database;

import java.util.Collections;

import com.iloo.librarymanager.model.Book;
import com.iloo.librarymanager.model.User;

/**
 * Implementation of the Database Factory.
 */
class DatabaseFactory implements IDatabaseFactory {

	DatabaseFactory() {
		// implicit constructor
	}

	@Override
	public IDatabase<Book> createBookDatabase() {
		return new BookDatabase(Collections.emptyList());
	}

	@Override
	public IDatabase<User> createUserDatabase() {
		return new UserDatabase(Collections.emptyList());
	}
}
