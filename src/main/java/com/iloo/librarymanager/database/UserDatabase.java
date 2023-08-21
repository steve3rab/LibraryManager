package com.iloo.librarymanager.database;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.iloo.librarymanager.exception.UserException;
import com.iloo.librarymanager.model.User;

/**
 * Concrete implementation of UserDatabase.
 */
class UserDatabase implements IDatabase<User> {

	private final List<User> users;

	UserDatabase(List<User> users) {
		this.users = Collections.unmodifiableList(users);
	}

	@Override
	public List<User> getAll() {
		return users;
	}

	@Override
	public Optional<User> getById(int id) {
		return users.stream().filter(user -> user.getId() == id).findFirst();
	}

	@Override
	public void add(User user) {
		if (!getById(user.getId()).isEmpty()) {
			throw new UserException("User with the same ID already exists.");
		}
		List<User> updatedUsers = new ArrayList<>(users);
		updatedUsers.add(user);
	}

	@Override
	public void update(User updatedUser) {
		List<User> updatedUsers = new ArrayList<>(users);
		int index = -1;
		for (int i = 0; i < updatedUsers.size(); i++) {
			if (updatedUsers.get(i).getId() == updatedUser.getId()) {
				index = i;
				break;
			}
		}
		if (index == -1) {
			throw new UserException("User with the specified ID not found.");
		}
		updatedUsers.set(index, updatedUser);
	}

	@Override
	public void delete(int id) {
		List<User> updatedUsers = new ArrayList<>(users);
		if (!updatedUsers.removeIf(user -> user.getId() == id)) {
			throw new UserException("User with the specified ID not found.");
		}
	}

}
