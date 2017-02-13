package io.pivotal.demo.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Customer implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	private String firstName;
	private String lastName;
	
	protected Customer() {
	}
	
	public Customer(Integer custId, String first, String last) {
		super();
		this.id = custId;
		this.firstName = first;
		this.lastName = last;
	}
	
	@Override
	public String toString() {
		return "Id=" + id + ", firstName=" + firstName + ", lastName=" + lastName;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
}
