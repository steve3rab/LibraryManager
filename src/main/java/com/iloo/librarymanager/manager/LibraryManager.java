package com.iloo.librarymanager.manager;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.iloo.librarymanager.database.IDatabase;
import com.iloo.librarymanager.database.IDatabaseFactory;
import com.iloo.librarymanager.model.Book;
import com.iloo.librarymanager.model.User;

/**
 * Singleton class for managing a library's books.
 */
public class LibraryManager {
	private static final AtomicReference<LibraryManager> INSTANCE = new AtomicReference<>();
	private final IDatabase<User> userDatabase;
	private final IDatabase<Book> bookDatabase;
	private final Lock userDatabaseLock = new ReentrantLock();
	private final Lock bookDatabaseLock = new ReentrantLock();

	private LibraryManager(IDatabaseFactory databaseFactory) {
		userDatabase = databaseFactory.createUserDatabase();
		bookDatabase = databaseFactory.createBookDatabase();
	}

	/**
	 * Get the instance of LibraryManager.
	 *
	 * @return The LibraryManager instance.
	 */
	public static LibraryManager getInstance(IDatabaseFactory databaseFactory) {
		if (INSTANCE.get() == null) {
			synchronized (LibraryManager.class) {
				if (INSTANCE.get() == null) {
					INSTANCE.set(new LibraryManager(databaseFactory));
				}
			}
		}
		return INSTANCE.get();
	}

	/**
	 * Get the user database with synchronized access.
	 *
	 * @return The synchronized user database.
	 */
	public IDatabase<User> getUserDatabase() {
		userDatabaseLock.lock();
		try {
			return userDatabase;
		} finally {
			userDatabaseLock.unlock();
		}
	}

	/**
	 * Get the book database with synchronized access.
	 *
	 * @return The synchronized book database.
	 */
	public IDatabase<Book> getBookDatabase() {
		bookDatabaseLock.lock();
		try {
			return bookDatabase;
		} finally {
			bookDatabaseLock.unlock();
		}
	}
}
