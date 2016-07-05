package com.srs.model;

import java.util.ArrayList;
import java.util.Collection;


public class Student extends Person {
	private String major;
	private String degree;
	private Transcript transcript;
	private ArrayList<Section> attends;
	private PlanOfStudy plan;

	public Student(String name, String ssn, String major, String degree) {
	
		super(name, ssn);

		this.setMajor(major);
		this.setDegree(degree);
		this.setTranscript(new Transcript(this));
		attends = new ArrayList<Section>();
	}

	public Student(String ssn) {
	}


	public void displayCourseSchedule() {

		System.out.println("Course Schedule for " + this.getName());

		for (Section s : attends) {

			if (s.getGrade(this) == null) {
				System.out.println("\tCourse No.:  " + s.getRepresentedCourse().getCourseNo());
				System.out.println("\tSection No.:  " + s.getSectionNo());
				System.out.println("\tCourse Name:  " + s.getRepresentedCourse().getCourseName());
				System.out.println("\tMeeting Day and Time Held:  " + s.getDayOfWeek() + " - " + s.getTimeOfDay());
				System.out.println("\tRoom Location:  " + s.getRoom());
				System.out.println("\tProfessor's Name:  " + s.getInstructor().getName());
				System.out.println("\t-----");
			}
		}
	}

	public void addSection(Section s) {
		attends.add(s);
	}

	public void dropSection(Section s) {
		attends.remove(s);
	}

	public boolean isEnrolledIn(Section s) {
		if (attends.contains(s))
			return true;
		else
			return false;
	}

	public boolean isCurrentlyEnrolledInSimilar(Section s1) {
		boolean foundMatch = false;

		Course c1 = s1.getRepresentedCourse();

		for (Section s2 : attends) {
			Course c2 = s2.getRepresentedCourse();
			if (c1 == c2) {
				if (s2.getGrade(this) == null) {
					foundMatch = true;
					break;
				}
			}
		}

		return foundMatch;
	}

	public void printTranscript() {
		this.getTranscript().display();
	}

	public Collection<Section> getEnrolledSections() {
		return attends;
	}

	@Override
	public void display() {

		super.display();

		System.out.println("Student-Specific Information:");
		System.out.println("\tMajor:  " + this.getMajor());
		System.out.println("\tDegree:  " + this.getDegree());
		this.displayCourseSchedule();
		this.printTranscript();

		System.out.println();
	}

	@Override
	public String toString() {
		return this.getName() + " (" + this.getSsn() + ") [" + this.getDegree() + " - " + this.getMajor() + "]";
	}



	public void setMajor(String major) {
		this.major = major;
	}

	public String getMajor() {
		return major;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getDegree() {
		return degree;
	}

	public void setTranscript(Transcript t) {
		transcript = t;
	}

	public Transcript getTranscript() {
		return transcript;
	}

	public PlanOfStudy getPlan() {
		return plan;
	}

	public void setPlan(PlanOfStudy plan) {
		this.plan = plan;
	}

}
