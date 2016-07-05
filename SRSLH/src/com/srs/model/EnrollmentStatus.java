package com.srs.model;

public enum EnrollmentStatus {
	success("加入成功 ！  :o)"), 
	secFull("加入失败 ; 该班次已满。  :op"), 
	prereq("加入失败 ; 先修课程不满足。   :op"), 
	prevEnroll("加入失败 ; 您之前参加过该课程！   :op"),
	notInPlan("加入失败;您的学习计划中没有该课程！  :op");
	
	private final String value;
		EnrollmentStatus(String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}
}
