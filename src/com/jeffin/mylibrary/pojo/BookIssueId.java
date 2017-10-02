package com.jeffin.mylibrary.pojo;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class BookIssueId implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int bookId;
	
	private int customerId;

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
}
