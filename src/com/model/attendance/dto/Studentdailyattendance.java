package com.model.attendance.dto;

// default package
// Generated 22 Jan, 2018 4:35:50 PM by Hibernate Tools 4.0.0

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.model.student.dto.Student;

/**
 * Studentdailyattendance generated by hbm2java
 */
@Entity
@Table(name = "att_studentdailyattendance")
public class Studentdailyattendance implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer attendanceid;
	private String attendeeid;
	private String intime;
	private String outtime;
	private Date date;
	private String attendancestatus;
	private String academicyear;
	private int branchid;
	private int userid;

	
	public Studentdailyattendance() {
	}

	public Studentdailyattendance(String attendeeid, String intime,
			String outtime, Date date, String attendancestatus,
			String academicyear, int branchid, int userid) {
		this.attendeeid = attendeeid;
		this.intime = intime;
		this.outtime = outtime;
		this.date = date;
		this.attendancestatus = attendancestatus;
		this.academicyear = academicyear;
		this.branchid = branchid;
		this.userid = userid;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "attendanceid", unique = true, nullable = false)
	public Integer getAttendanceid() {
		return this.attendanceid;
	}

	public void setAttendanceid(Integer attendanceid) {
		this.attendanceid = attendanceid;
	}

	@Column(name = "attendeeid",length = 45)
	public String getAttendeeid() {
		return this.attendeeid;
	}

	public void setAttendeeid(String attendeeid) {
		this.attendeeid = attendeeid;
	}

	@Column(name = "intime", length = 45)
	public String getIntime() {
		return this.intime;
	}

	public void setIntime(String intime) {
		this.intime = intime;
	}

	@Column(name = "outtime", length = 45)
	public String getOuttime() {
		return this.outtime;
	}

	public void setOuttime(String outtime) {
		this.outtime = outtime;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date", length = 10)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "attendancestatus", length = 15)
	public String getAttendancestatus() {
		return this.attendancestatus;
	}

	public void setAttendancestatus(String attendancestatus) {
		this.attendancestatus = attendancestatus;
	}

	@Column(name = "academicyear", length = 10)
	public String getAcademicyear() {
		return this.academicyear;
	}

	public void setAcademicyear(String academicyear) {
		this.academicyear = academicyear;
	}
	
	@Column(name = "branchid")
	public Integer getBranchid() {
	return branchid;
	}

	public void setBranchid(Integer branchid) {
	this.branchid = branchid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public void setBranchid(int branchid) {
		this.branchid = branchid;
	}
}
