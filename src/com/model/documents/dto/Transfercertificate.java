package com.model.documents.dto;

// default package
// Generated 21 Mar, 2018 9:55:30 AM by Hibernate Tools 4.0.0

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Transfercertificate generated by hbm2java
 */
@Entity
@Table(name = "transfercertificate")
public class Transfercertificate implements java.io.Serializable {

	private Integer tcid;
	private String applicationstatus;
	private Integer noofissues;
	private Date dateofissues;
	private String generalconduct;
	private String progress;
	private Integer sid;
	private int branchid;
	private int userid;
	

	public Transfercertificate() {
	}

	public Transfercertificate(String applicationstatus, Integer noofissues,
			Date dateofissues, String generalconduct, String progress, Integer sid, int branchid, int userid) {
		this.applicationstatus = applicationstatus;
		this.noofissues = noofissues;
		this.dateofissues = dateofissues;
		this.generalconduct = generalconduct;
		this.progress = progress;
		this.sid = sid;
		this.branchid = branchid;
		this.userid = userid;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "tcid", unique = true, nullable = false)
	public Integer getTcid() {
		return this.tcid;
	}

	public void setTcid(Integer tcid) {
		this.tcid = tcid;
	}

	@Column(name = "applicationstatus", length = 20)
	public String getApplicationstatus() {
		return this.applicationstatus;
	}

	public void setApplicationstatus(String applicationstatus) {
		this.applicationstatus = applicationstatus;
	}

	@Column(name = "noofissues")
	public Integer getNoofissues() {
		return this.noofissues;
	}

	public void setNoofissues(Integer noofissues) {
		this.noofissues = noofissues;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dateofissues", length = 10)
	public Date getDateofissues() {
		return this.dateofissues;
	}

	public void setDateofissues(Date dateofissues) {
		this.dateofissues = dateofissues;
	}

	@Column(name = "generalconduct", length = 500)
	public String getGeneralconduct() {
		return this.generalconduct;
	}

	public void setGeneralconduct(String generalconduct) {
		this.generalconduct = generalconduct;
	}

	@Column(name = "progress", length = 100)
	public String getProgress() {
		return this.progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}

	@Column(name = "sid")
	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}
	
	@Column(name = "branchid")
	public int getBranchid() {
	return branchid;
	}

	public void setBranchid(int branchid) {
	this.branchid = branchid;
	}
	
	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

}
