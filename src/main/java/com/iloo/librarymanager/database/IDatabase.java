package com.iloo.librarymanager.database;

import java.util.List;
import java.util.Optional;

/**
 * The generic database interface for managing objects of a specific type.
 *
 * @param <T> The type of objects managed by the database.
 */
public interface IDatabase<T> {
	/**
	 * Retrieves all objects of type T from the database.
	 *
	 * @return A list of all objects in the database.
	 */
	List<T> getAll();

	/**
	 * Retrieves an object of type T by its unique identifier.
	 *
	 * @param id The unique identifier of the object to retrieve.
	 * @return An Optional containing the object if found, or an empty Optional if
	 *         not found.
	 */
	Optional<T> getById(int id);

	/**
	 * Adds an object of type T to the database.
	 *
	 * @param item The object to add to the database.
	 */
	void add(T item);

	/**
	 * Updates an existing object of type T in the database.
	 *
	 * @param updatedItem The updated object to replace the existing one.
	 */
	void update(T updatedItem);

	/**
	 * Deletes an object of type T from the database by its unique identifier.
	 *
	 * @param id The unique identifier of the object to delete.
	 */
	void delete(int id);
}
