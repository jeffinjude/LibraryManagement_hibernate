package com.jeffin.mylibrary.pojo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="allowed_area")
public class AllowedArea {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="area_id")
	private int areaId;
	
	@Column(name="area_name")
	private String areaName;
	
	@ManyToMany(cascade={CascadeType.ALL})
	@JoinTable(name="customer_allowed_areas",
			   joinColumns=@JoinColumn(name="area_id", referencedColumnName="area_id"),
			   inverseJoinColumns=@JoinColumn(name="customer_id", referencedColumnName="customer_id"))
	private List<Customer> customers;

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
	
}
