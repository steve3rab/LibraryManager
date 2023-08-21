package com.iloo.librarymanager.model;

import java.util.Objects;

/**
 * Represents a book in the library.
 */
public class Book {
	private int id;
	private String title;
	private String author;
	private String genre;
	private int publicationYear;
	private String isbn;
	private Publisher publisher;
	private String language;
	private int pageCount;
	private String format;
	private double price;
	private String summary;
	private boolean isAvailable;
	private boolean isReferenceOnly;
	private boolean isBorrowable;

	private Book(Builder builder) {
		this.id = builder.id;
		this.title = builder.title;
		this.author = builder.author;
		this.genre = builder.genre;
		this.publicationYear = builder.publicationYear;
		this.isbn = builder.isbn;
		this.publisher = builder.publisher;
		this.language = builder.language;
		this.pageCount = builder.pageCount;
		this.format = builder.format;
		this.price = builder.price;
		this.summary = builder.summary;
		this.isAvailable = builder.isAvailable;
		this.isReferenceOnly = builder.isReferenceOnly;
		this.isBorrowable = builder.isBorrowable;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String getGenre() {
		return genre;
	}

	public int getPublicationYear() {
		return publicationYear;
	}

	public String getIsbn() {
		return isbn;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public String getLanguage() {
		return language;
	}

	public int getPageCount() {
		return pageCount;
	}

	public String getFormat() {
		return format;
	}

	public double getPrice() {
		return price;
	}

	public String getSummary() {
		return summary;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public boolean isReferenceOnly() {
		return isReferenceOnly;
	}

	public boolean isBorrowable() {
		return isBorrowable;
	}

	/**
	 * Builder class for Book.
	 */
	public static class Builder {
		private int id;
		private String title;
		private String author;
		private String genre;
		private int publicationYear;
		private String isbn;
		private Publisher publisher;
		private String language;
		private int pageCount;
		private String format;
		private double price;
		private String summary;
		private boolean isAvailable;
		private boolean isReferenceOnly;
		private boolean isBorrowable;

		public Builder(int id, String title, String author) {
			this.id = id;
			this.title = title;
			this.author = author;
		}

		public Builder genre(String genre) {
			this.genre = genre;
			return this;
		}

		public Builder publicationYear(int publicationYear) {
			this.publicationYear = publicationYear;
			return this;
		}

		public Builder isbn(String isbn) {
			this.isbn = isbn;
			return this;
		}

		public Builder publisher(Publisher publisher) {
			this.publisher = publisher;
			return this;
		}

		public Builder language(String language) {
			this.language = language;
			return this;
		}

		public Builder pageCount(int pageCount) {
			this.pageCount = pageCount;
			return this;
		}

		public Builder format(String format) {
			this.format = format;
			return this;
		}

		public Builder price(double price) {
			this.price = price;
			return this;
		}

		public Builder summary(String summary) {
			this.summary = summary;
			return this;
		}

		public Builder isAvailable(boolean isAvailable) {
			this.isAvailable = isAvailable;
			return this;
		}

		public Builder isReferenceOnly(boolean isReferenceOnly) {
			this.isReferenceOnly = isReferenceOnly;
			return this;
		}

		public Builder isBorrowable(boolean isBorrowable) {
			this.isBorrowable = isBorrowable;
			return this;
		}

		public Book build() {
			return new Book(this);
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(author, format, genre, id, isAvailable, isBorrowable, isReferenceOnly, isbn, language,
				pageCount, price, publicationYear, publisher, summary, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Book other)) {
			return false;
		}
		return Objects.equals(author, other.author) && Objects.equals(format, other.format)
				&& Objects.equals(genre, other.genre) && (id == other.id) && (isAvailable == other.isAvailable)
				&& (isBorrowable == other.isBorrowable) && (isReferenceOnly == other.isReferenceOnly)
				&& Objects.equals(isbn, other.isbn) && Objects.equals(language, other.language)
				&& (pageCount == other.pageCount)
				&& (Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price))
				&& (publicationYear == other.publicationYear) && Objects.equals(publisher, other.publisher)
				&& Objects.equals(summary, other.summary) && Objects.equals(title, other.title);
	}

}
