package com.iloo.librarymanager.model;

import java.util.Objects;

/**
 * Represents a book publisher.
 */
public class Publisher {
	private String name;
	private Address address;
	private Contact contact;

	public Publisher(String name, Address address, Contact contact) {
		this.name = name;
		this.address = address;
		this.contact = contact;
	}

	public String getName() {
		return name;
	}

	public Address getAddress() {
		return address;
	}

	public Contact getContact() {
		return contact;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, contact, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Publisher other)) {
			return false;
		}
		return Objects.equals(address, other.address) && Objects.equals(contact, other.contact)
				&& Objects.equals(name, other.name);
	}

}
