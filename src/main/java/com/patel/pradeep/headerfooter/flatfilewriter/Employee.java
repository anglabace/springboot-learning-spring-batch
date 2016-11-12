package com.patel.pradeep.headerfooter.flatfilewriter;

import java.util.Date;

public class Employee {
	private final long id;

	private final String firstName;

	private final String lastName;

	private final Date birthdate;

	private final double salary;

	private final String department;

	public Employee(long id, String firstName, String lastName, Date birthdate, double salary, String department) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.salary = salary;
		this.department = department;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return the birthdate
	 */
	public Date getBirthdate() {
		return birthdate;
	}

	/**
	 * @return the salary
	 */
	public double getSalary() {
		return salary;
	}

	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", birthdate=" + birthdate
				+ ", salary=" + salary + ", department=" + department + "]";
	}

}
