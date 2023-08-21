package com.iloo.librarymanager.model;

import java.util.Objects;

/**
 * Represents an address.
 */
public class Address {
	private String street;
	private String city;
	private String state;
	private String postalCode;
	private String country;

	public Address(String street, String city, String state, String postalCode, String country) {
		this.street = street;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
		this.country = country;
	}

	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public String getCountry() {
		return country;
	}

	@Override
	public int hashCode() {
		return Objects.hash(city, country, postalCode, state, street);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Address other)) {
			return false;
		}
		return Objects.equals(city, other.city) && Objects.equals(country, other.country)
				&& Objects.equals(postalCode, other.postalCode) && Objects.equals(state, other.state)
				&& Objects.equals(street, other.street);
	}

}
