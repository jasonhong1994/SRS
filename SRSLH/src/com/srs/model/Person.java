package com.srs.model;


public abstract class Person {

	private String name;
	private String ssn;

	public Person(String name, String ssn) {
		this.setName(name);
		this.setSsn(ssn);
	}

	public Person() {
	}

	public void setName(String n) {
		name = n;
	}

	public String getName() {
		return name;
	}

	public void setSsn(String s) {
		ssn = s;
	}

	public String getSsn() {
		return ssn;
	}

	@Override
	public abstract String toString();


	public void display() {
		System.out.println("Person Information:");
		System.out.println("\tName:  " + this.getName());
		System.out.println("\tSoc. Security No.:  " + this.getSsn());
	}
}
