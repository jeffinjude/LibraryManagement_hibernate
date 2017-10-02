package com.jeffin.mylibrary.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="customer_contact_no")
public class CustomerContactNumber {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="contact_no_id")
	private int contactNoId;
	
	@Column(name="contact_no")
	private String contactNo;
	
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="customer_id", referencedColumnName="customer_id")
	private Customer customer;

	public int getContactNoId() {
		return contactNoId;
	}

	public void setContactNoId(int contactNoId) {
		this.contactNoId = contactNoId;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
}
