package com.model.std.dto;

// default package
// Generated 6 Nov, 2014 12:17:14 PM by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Classsec generated by hbm2java
 */
@Entity
@Table(name = "classsec")
public class Classsec implements java.io.Serializable {

	private Integer stdrdid;
	private String classdetails;
	private String section;
	private int branchid;
	private int userid;

	public Classsec() {
	}

	public Classsec(String classdetails, String section, int branchid, int userid) {
		this.classdetails = classdetails;
		this.section = section;
		this.branchid = branchid;
		this.userid = userid;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "stdrdid", unique = true, nullable = false)
	public Integer getStdrdid() {
		return this.stdrdid;
	}

	public void setStdrdid(Integer stdrdid) {
		this.stdrdid = stdrdid;
	}

	@Column(name = "classdetails", length = 45)
	public String getClassdetails() {
		return this.classdetails;
	}

	public void setClassdetails(String classdetails) {
		this.classdetails = classdetails;
	}
	
        	
	@Column(name = "section", length = 45)
        public String getSection() {
            return this.section;
        }
    
        public void setSection(String section) {
            this.section = section;
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
