package com.itechies.iquiz.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Exam {
	
	
	
	private Integer id;
	private String name;
	private String subjectCode;
	private String dateOfExam;
	private String examTime;
	private Integer department;
	private Integer examStatus;
	private Integer addedBy;
	private String addedOn;
	private Integer modifiedBy;
	private String modifiedOn;
	
	public String getDateOfExam() {
		return dateOfExam;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDateOfExam(String dateOfExam) {
		this.dateOfExam = dateOfExam;
	}
	public String getModifiedOn() {
		return modifiedOn;
	}
	public void setModifiedOn(String modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	public String getAddedOn() {
		return addedOn;
	}
	public void setAddedBy(Integer addedBy) {
		this.addedBy = addedBy;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getSubjectCode() {
		return subjectCode;
	}
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	
	
	public String getExamTime() {
		return examTime;
	}
	public void setExamTime(String examTime) {
		this.examTime = examTime;
	}
	public Integer getDepartment() {
		return department;
	}
	public void setDepartment(Integer department) {
		this.department = department;
	}
	public Integer getExamStatus() {
		return examStatus;
	}
	public void setExamStatus(Integer examStatus) {
		this.examStatus = examStatus;
	}
	public Integer getAddedBy() {
		return addedBy;
	}
	
	public void setAddedOn(String string) {
		this.addedOn = string;
	}
	public Integer getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	
	
	
}
