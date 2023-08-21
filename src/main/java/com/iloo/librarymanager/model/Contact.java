package com.iloo.librarymanager.model;

import java.util.Objects;

/**
 * Represents contact information.
 */
public class Contact {
	private String email;
	private String phone;

	public Contact(String email, String phone) {
		this.email = email;
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, phone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Contact other)) {
			return false;
		}
		return Objects.equals(email, other.email) && Objects.equals(phone, other.phone);
	}

}
