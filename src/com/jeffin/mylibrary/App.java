package com.jeffin.mylibrary;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jeffin.mylibrary.pojo.Address;
import com.jeffin.mylibrary.pojo.AllowedArea;
import com.jeffin.mylibrary.pojo.Book;
import com.jeffin.mylibrary.pojo.Customer;
import com.jeffin.mylibrary.pojo.CustomerContactNumber;

public class App {

	public static void main(String[] args) {
		
		LibraryDao libraryDao = new LibraryDao();
		libraryDao.delAllData();
		
		//Insert Customer
		Address addr1 = new Address();
		addr1.setHouseName("House1");
		addr1.setStreet("Street1");
		addr1.setCity("City1");
		addr1.setZip("111111");
		Customer cust1 = new Customer();
		cust1.setCustomerName("Customer1");
		cust1.setAddress(addr1);
		
		Address addr2 = new Address();
		addr2.setHouseName("House2");
		addr2.setStreet("Street2");
		addr2.setCity("City2");
		addr2.setZip("222222");
		Customer cust2 = new Customer();
		cust2.setCustomerName("Customer2");
		cust2.setAddress(addr2);
		
		Address addr3 = new Address();
		addr3.setHouseName("House3");
		addr3.setStreet("Street3");
		addr3.setCity("City3");
		addr3.setZip("333333");
		Customer cust3 = new Customer();
		cust3.setCustomerName("Customer3");
		cust3.setAddress(addr3);
		
		libraryDao.insertCustomer(cust1);
		libraryDao.insertCustomer(cust2);
		libraryDao.insertCustomer(cust3);
		
		
		//Insert Book
		Book book1 = new Book();
		book1.setBookName("Book1");
		book1.setPrice(10.00);
		book1.setStock(10);
		
		Book book2 = new Book();
		book2.setBookName("Book2");
		book2.setPrice(20.00);
		book2.setStock(5);

		Book book3 = new Book();
		book3.setBookName("Book3");
		book3.setPrice(30.00);
		book3.setStock(1);
		
		libraryDao.insertBook(book1);
		libraryDao.insertBook(book2);
		libraryDao.insertBook(book3);
		
		//Insert Customer Contact Number
		CustomerContactNumber customerContactNumber1= new CustomerContactNumber();
		customerContactNumber1.setContactNo("1111111111");
		customerContactNumber1.setCustomer(cust1);
		CustomerContactNumber customerContactNumber2= new CustomerContactNumber();
		customerContactNumber2.setContactNo("1222222222");
		customerContactNumber2.setCustomer(cust1);
		
		CustomerContactNumber customerContactNumber3= new CustomerContactNumber();
		customerContactNumber3.setContactNo("2111111111");
		customerContactNumber3.setCustomer(cust2);
		CustomerContactNumber customerContactNumber4= new CustomerContactNumber();
		customerContactNumber4.setContactNo("2222222222");
		customerContactNumber4.setCustomer(cust2);
		
		CustomerContactNumber customerContactNumber5= new CustomerContactNumber();
		customerContactNumber5.setContactNo("3111111111");
		customerContactNumber5.setCustomer(cust3);
		CustomerContactNumber customerContactNumber6= new CustomerContactNumber();
		customerContactNumber6.setContactNo("3222222222");
		customerContactNumber6.setCustomer(cust3);
		
		libraryDao.insertCustomerContact(customerContactNumber1);
		libraryDao.insertCustomerContact(customerContactNumber2);
		libraryDao.insertCustomerContact(customerContactNumber3);
		libraryDao.insertCustomerContact(customerContactNumber4);
		libraryDao.insertCustomerContact(customerContactNumber5);
		libraryDao.insertCustomerContact(customerContactNumber6);
		
		//Insert Allowed Area
		AllowedArea allowedArea1 = new AllowedArea();
		allowedArea1.setAreaName("Area1");
		List<Customer> customersArea1 = new ArrayList<Customer>();
		customersArea1.add(cust1);
		customersArea1.add(cust2);
		allowedArea1.setCustomers(customersArea1);
		
		AllowedArea allowedArea2 = new AllowedArea();
		allowedArea2.setAreaName("Area2");
		List<Customer> customersArea2 = new ArrayList<Customer>();
		customersArea2.add(cust3);
		allowedArea2.setCustomers(customersArea2);
		
		libraryDao.insertAllowedArea(allowedArea1);
		libraryDao.insertAllowedArea(allowedArea2);
		
		//Issue books
		libraryDao.issueBook(1, 1);
		libraryDao.issueBook(2, 2);
		libraryDao.issueBook(3, 2);
		libraryDao.issueBook(3, 3);
		
		//Return books
		libraryDao.returnBook(1, 1);
		
		//List books reserved
		libraryDao.listBooksReserved(3);
		
		//List books issued
		libraryDao.getBooksIssueForDate(new Date());
	}

}
