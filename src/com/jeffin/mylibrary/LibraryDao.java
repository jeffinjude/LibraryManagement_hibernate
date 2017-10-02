package com.jeffin.mylibrary;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.jeffin.mylibrary.pojo.AllowedArea;
import com.jeffin.mylibrary.pojo.Book;
import com.jeffin.mylibrary.pojo.BookIssue;
import com.jeffin.mylibrary.pojo.BookIssueId;
import com.jeffin.mylibrary.pojo.BookReservation;
import com.jeffin.mylibrary.pojo.BookReservationId;
import com.jeffin.mylibrary.pojo.Customer;
import com.jeffin.mylibrary.pojo.CustomerContactNumber;

public class LibraryDao {
	private static final int NO_OF_DAYS = 35; //A person could keep a borrowed book for 35 days
	
	public void delAllData() {
		
		Session session = null;
		try{
			
			session = HibernateUtil.getSessionfactory().openSession();
			Transaction transaction = session.beginTransaction();
			Query query = session.getNamedQuery("TruncateAllData");
			query.executeUpdate();
			
		}
		catch(HibernateException e) {
			System.out.println("Hibernate exception in function delAllData. Exception: " + e.getMessage());
		}
		catch(Exception e) {
			System.out.println("Exception in function delAllData. Exception: " + e.getMessage());
		}
		finally {
			session.close();
		}
	}

	public void insertBook(Book book) {
		
		Session session = null;
		try{
			
			session = HibernateUtil.getSessionfactory().openSession();
			Transaction transaction = session.beginTransaction();
			session.save(book);
			transaction.commit();
			
		}
		catch(HibernateException e) {
			System.out.println("Hibernate exception in function insertBooks. Exception: " + e.getMessage());
		}
		catch(Exception e) {
			System.out.println("Exception in function insertBooks. Exception: " + e.getMessage());
		}
		finally {
			session.close();
		}
	}
	
	public void insertCustomer(Customer customer) {
		Session session = HibernateUtil.getSessionfactory().openSession();
		try{
			
			session = HibernateUtil.getSessionfactory().openSession();
			Transaction transaction = session.beginTransaction();
			session.save(customer);
			transaction.commit();
			
		}
		catch(HibernateException e) {
			System.out.println("Hibernate exception in function insertCustomers. Exception: " + e.getMessage());
		}
		catch(Exception e) {
			System.out.println("Esxception in function insertCustomers. Exception: " + e.getMessage());
		}
		finally {
			session.close();
		}
	}
	
	public void insertCustomerContact(CustomerContactNumber customerContactNumber) {
		Session session = null;
		try{
			
			session = HibernateUtil.getSessionfactory().openSession();
			Transaction transaction = session.beginTransaction();
			session.save(customerContactNumber);
			transaction.commit();
			
		}
		catch(HibernateException e) {
			System.out.println("Hibernate exception in function insertCustomerContact. Exception: " + e.getMessage());
		}
		catch(Exception e) {
			System.out.println("Exception in function insertCustomerContact. Exception: " + e.getMessage());
		}
		finally {
			session.close();
		}
	}
	
	public void insertAllowedArea(AllowedArea allowedArea) {
		Session session = null;
		try{
			
			session = HibernateUtil.getSessionfactory().openSession();
			Transaction transaction = session.beginTransaction();
			session.save(allowedArea);
			transaction.commit();
			
		}
		catch(HibernateException e) {
			System.out.println("Hibernate exception in function insertAllowedArea. Exception: " + e.getMessage());
		}
		catch(Exception e) {
			System.out.println("Exception in function insertAllowedArea. Exception: " + e.getMessage());
		}
		finally {
			session.close();
		}
	}
	
	public void issueBook(int bookId, int customerId) {
		Session session = null;
		Book book = null;
		Customer customer = null;
		
		try{
			
			session = HibernateUtil.getSessionfactory().openSession();
			Transaction transaction = session.beginTransaction();
			book = (Book) session.get(Book.class, bookId);
			customer = (Customer) session.get(Customer.class, customerId);
			
			if(book != null && customer != null) {
				if(book.getStock() > 0) {
					Date issueDate = new Date();
					GregorianCalendar  gregorianCalendar = new GregorianCalendar();
					gregorianCalendar.setTime(issueDate);
					gregorianCalendar.add(Calendar.DATE, NO_OF_DAYS);
					Date returnDate = gregorianCalendar.getTime();
					
					BookIssueId bookIssueId = new BookIssueId();
					bookIssueId.setBookId(bookId);
					bookIssueId.setCustomerId(customerId);
					BookIssue bookIssue = new BookIssue();
					bookIssue.setBookIssueId(bookIssueId);
					bookIssue.setIssueDate(issueDate);
					//Something to test out getBooksIssueForDate()
//					if(book.getBookId() == 3) {
//						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//						bookIssue.setIssueDate(sdf.parse("02/10/2017"));
//					}
					bookIssue.setReturnDate(returnDate);
					bookIssue.setIsReturned(0);
					session.save(bookIssue);
					updateBookStock(book, true);
					transaction.commit();
				}
				else {
					//Reserve book
					reserveBook(bookId, customerId);
				}
			}
			
		}
		catch(HibernateException e) {
			System.out.println("Hibernate exception in function issueBook. Exception: " + e.getMessage());
		}
		catch(Exception e) {
			System.out.println("Exception in function issueBook. Exception: " + e.getMessage());
		}
		finally {
			session.close();
		}
	}
	
	public void returnBook(int bookId, int customerId) {
		Session session = null;
		Book book = null;
		Customer customer = null;
		
		try{
			
			session = HibernateUtil.getSessionfactory().openSession();
			Transaction transaction = session.beginTransaction();
			book = (Book) session.get(Book.class, bookId);
			customer = (Customer) session.get(Customer.class, customerId);
			BookIssueId bookIssueId = new BookIssueId();
			bookIssueId.setBookId(bookId);
			bookIssueId.setCustomerId(customerId);
			
			if(book != null && customer != null) {
				Query query = session.createQuery("Update BookIssue bookIssue set bookIssue.isReturned = :isReturnedVal where bookIssue.bookIssueId = :bookIssueIdVal");
				query.setParameter("isReturnedVal",1);
				query.setParameter("bookIssueIdVal",bookIssueId);
				query.executeUpdate();
				updateBookStock(book, false);
				transaction.commit();
			}
			
		}
		catch(HibernateException e) {
			System.out.println("Hibernate exception in function returnBook. Exception: " + e.getMessage());
		}
		catch(Exception e) {
			System.out.println("Exception in function returnBook. Exception: " + e.getMessage());
		}
		finally {
			session.close();
		}
	}
	
	public void updateBookStock(Book book, boolean reduceStock) {
		Session session = null;
		try{
			
			session = HibernateUtil.getSessionfactory().openSession();
			Transaction transaction = session.beginTransaction();
			int newStock = book.getStock();
			
			if(reduceStock) {
				newStock--;
			}
			else {
				newStock++;
			}
			
			book.setStock(newStock);
			session.saveOrUpdate(book);
			transaction.commit();
			
		}
		catch(HibernateException e) {
			System.out.println("Hibernate exception in function updateBookStock. Exception: " + e.getMessage());
		}
		catch(Exception e) {
			System.out.println("Exception in function updateBookStock. Exception: " + e.getMessage());
		}
		finally {
			session.close();
		}
	}
	
	public void reserveBook(int bookId, int customerId) {
		Session session = null;
		try{
			
			session = HibernateUtil.getSessionfactory().openSession();
			Transaction transaction = session.beginTransaction();
			BookReservationId bookReservationId = new BookReservationId();
			bookReservationId.setBookId(bookId);
			bookReservationId.setCustomerId(customerId);
			BookReservation bookReservation = new BookReservation();
			bookReservation.setBookReservationId(bookReservationId);
			bookReservation.setReservationDate(new Date());
			session.save(bookReservation);
			transaction.commit();
			
		}
		catch(HibernateException e) {
			System.out.println("Hibernate exception in function reserveBook. Exception: " + e.getMessage());
		}
		catch(Exception e) {
			System.out.println("Exception in function reserveBook. Exception: " + e.getMessage());
		}
		finally {
			session.close();
		}
	}
	
	public void listBooksReserved(int customerId) {
		Session session = null;
		Book book = null;
		BookReservation bookReservation = null;
		try{
			
			session = HibernateUtil.getSessionfactory().openSession();
			List booksReserved = session.createCriteria(BookReservation.class)
									.add(Restrictions.eq("bookReservationId.customerId", customerId)).list();
			Iterator iterator = booksReserved.iterator();
			System.out.println("Books reserved by customerId " + customerId + ":\n");
			while(iterator.hasNext()) {
				bookReservation = (BookReservation) iterator.next();
				book = (Book) session.get(Book.class, bookReservation.getBookReservationId().getBookId());
				System.out.println(book.toString() + "\n");
			}
			
		}
		catch(HibernateException e) {
			System.out.println("Hibernate exception in function listBooksReserved. Exception: " + e.getMessage());
		}
		catch(Exception e) {
			System.out.println("Exception in function listBooksReserved. Exception: " + e.getMessage());
		}
		finally {
			session.close();
		}
	}
	
	public void getBooksIssueForDate(Date date) {
		Session session = null;
		Book book = null;
		BookIssue bookIssue = null;
		try{
			
			session = HibernateUtil.getSessionfactory().openSession();
			Query query = session.getNamedQuery("BookIssue.getBookIssueForDate");
			query.setDate("issueDateVal", date);
			List<BookIssue> booksIssued = query.list();
			Iterator iterator = booksIssued.iterator();
			System.out.println("Books issued until " + date + " :\n");
			while(iterator.hasNext()) {
				bookIssue = (BookIssue) iterator.next();
				book = (Book) session.get(Book.class, bookIssue.getBookIssueId().getBookId());
				System.out.println(book.getBookName());
			}
			
		}
		catch(HibernateException e) {
			System.out.println("Hibernate exception in function getBooksIssueForDate. Exception: " + e.getMessage());
		}
		catch(Exception e) {
			System.out.println("Exception in function getBooksIssueForDate. Exception: " + e.getMessage());
		}
		finally {
			session.close();
		}
	}
}
