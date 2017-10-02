package com.jeffin.mylibrary.pojo;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="book_reservation")
public class BookReservation {
	
	@AttributeOverrides({
		@AttributeOverride(name="bookId", column=@Column(name="book_id")),
		@AttributeOverride(name="customerId", column=@Column(name="customer_id"))
	})
	@EmbeddedId
	private BookReservationId bookReservationId;
	
	@Column(name="reservation_date")
	private Date reservationDate;

	public BookReservationId getBookReservationId() {
		return bookReservationId;
	}

	public void setBookReservationId(BookReservationId bookReservationId) {
		this.bookReservationId = bookReservationId;
	}

	public Date getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}
	
}
