package com.iloo.librarymanager.model;

import java.util.Objects;

/**
 * Represents a library user.
 */
public class User {
	private int id;
	private String firstName;
	private String lastName;
	private Address address;
	private Contact contact;
	private int age;
	private String gender;
	private String occupation;
	private String libraryCardNumber;
	private boolean hasLateFees;
	private boolean isRestricted;

	private User(Builder builder) {
		this.id = builder.id;
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.address = builder.address;
		this.contact = builder.contact;
		this.age = builder.age;
		this.gender = builder.gender;
		this.occupation = builder.occupation;
		this.libraryCardNumber = builder.libraryCardNumber;
		this.hasLateFees = builder.hasLateFees;
		this.isRestricted = builder.isRestricted;
	}

	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Address getAddress() {
		return address;
	}

	public Contact getContact() {
		return contact;
	}

	public int getAge() {
		return age;
	}

	public String getGender() {
		return gender;
	}

	public String getOccupation() {
		return occupation;
	}

	public String getLibraryCardNumber() {
		return libraryCardNumber;
	}

	public boolean hasLateFees() {
		return hasLateFees;
	}

	public boolean isRestricted() {
		return isRestricted;
	}

	/**
	 * Builder class for User.
	 */
	public static class Builder {
		private int id;
		private String firstName;
		private String lastName;
		private Address address;
		private Contact contact;
		private int age;
		private String gender;
		private String occupation;
		private String libraryCardNumber;
		private boolean hasLateFees;
		private boolean isRestricted;

		public Builder(int id, String firstName, String lastName) {
			this.id = id;
			this.firstName = firstName;
			this.lastName = lastName;
		}

		public Builder address(Address address) {
			this.address = address;
			return this;
		}

		public Builder contact(Contact contact) {
			this.contact = contact;
			return this;
		}

		public Builder age(int age) {
			this.age = age;
			return this;
		}

		public Builder gender(String gender) {
			this.gender = gender;
			return this;
		}

		public Builder occupation(String occupation) {
			this.occupation = occupation;
			return this;
		}

		public Builder libraryCardNumber(String libraryCardNumber) {
			this.libraryCardNumber = libraryCardNumber;
			return this;
		}

		public Builder hasLateFees(boolean hasLateFees) {
			this.hasLateFees = hasLateFees;
			return this;
		}

		public Builder isRestricted(boolean isRestricted) {
			this.isRestricted = isRestricted;
			return this;
		}

		public User build() {
			return new User(this);
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, age, contact, firstName, gender, hasLateFees, id, isRestricted, lastName,
				libraryCardNumber, occupation);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof User other)) {
			return false;
		}
		return Objects.equals(address, other.address) && (age == other.age) && Objects.equals(contact, other.contact)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(gender, other.gender)
				&& (hasLateFees == other.hasLateFees) && (id == other.id) && (isRestricted == other.isRestricted)
				&& Objects.equals(lastName, other.lastName)
				&& Objects.equals(libraryCardNumber, other.libraryCardNumber)
				&& Objects.equals(occupation, other.occupation);
	}

}
