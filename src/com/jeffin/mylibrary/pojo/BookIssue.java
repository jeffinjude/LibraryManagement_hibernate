package com.jeffin.mylibrary.pojo;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="book_issue")
@NamedQueries({
	@NamedQuery(name="BookIssue.getBookIssueForDate", query="from BookIssue bookIssue where issueDate = :issueDateVal")
})
public class BookIssue {
	
	@AttributeOverrides({
		@AttributeOverride(name="bookId", column=@Column(name="book_id")),
		@AttributeOverride(name="customerId", column=@Column(name="customer_id"))
	})
	@EmbeddedId
	private BookIssueId bookIssueId;
	
	@Column(name="issue_date")
	private Date issueDate;
	
	@Column(name="return_date")
	private Date returnDate;
	
	@Column(name="is_returned")
	private int isReturned;

	public BookIssueId getBookIssueId() {
		return bookIssueId;
	}

	public void setBookIssueId(BookIssueId bookIssueId) {
		this.bookIssueId = bookIssueId;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public int getIsReturned() {
		return isReturned;
	}

	public void setIsReturned(int isReturned) {
		this.isReturned = isReturned;
	}
	
}
