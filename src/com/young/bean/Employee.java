package com.young.bean;

import java.io.Serializable;

public class Employee /*implements Serializable*/{
	private Integer id ;
	private String lastName ;
	private String gender ;
	private String email ;

	//复杂属性：一对一
	private Department department;

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Department getDepartment() {
		return department;
	}

	public Employee() {

	}
	
	
	public Employee(String lastName, String gender, String email) {
		super();
		this.lastName = lastName;
		this.gender = gender;
		this.email = email;
	}
	
	


	public Employee(Integer id, String lastName, String gender, String email, Department department) {
		super();
		this.id = id;
		this.lastName = lastName;
		this.gender = gender;
		this.email = email;
		this.department = department;
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

//	@Override
//	public String toString() {
//		return "Employee----------------------";
//	}
	@Override
	public String toString() {
		return "Employee{" +
				"id=" + id +
				", lastName='" + lastName + '\'' +
				", gender='" + gender + '\'' +
				", email='" + email + '\'' +
				", department=" + department +
				'}';
	}
}
