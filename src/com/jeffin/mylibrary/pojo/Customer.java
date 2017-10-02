package com.jeffin.mylibrary.pojo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="customer")
@NamedNativeQueries({
	@NamedNativeQuery(
		name = "TruncateAllData",
		query = "call delete_all_data();"
	)
})
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="customer_id")
	private int customerId;
	
	@Column(name="customer_name")
	private String customerName;
	
	@Embedded
	private Address address;
	
	@OneToMany(mappedBy="customer",cascade={CascadeType.ALL},targetEntity=CustomerContactNumber.class)
	List<CustomerContactNumber> customerContactNumbers;
	
	@ManyToMany(mappedBy="customers",cascade={CascadeType.ALL},targetEntity=AllowedArea.class)
	private List<AllowedArea> allowedAreas;

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<CustomerContactNumber> getCustomerContactNumbers() {
		return customerContactNumbers;
	}

	public void setCustomerContactNumbers(List<CustomerContactNumber> customerContactNumbers) {
		this.customerContactNumbers = customerContactNumbers;
	}

	public List<AllowedArea> getAllowedAreas() {
		return allowedAreas;
	}

	public void setAllowedAreas(List<AllowedArea> allowedAreas) {
		this.allowedAreas = allowedAreas;
	}
	
}
