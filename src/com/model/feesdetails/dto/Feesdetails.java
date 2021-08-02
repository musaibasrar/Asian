package com.model.feesdetails.dto;

// default package
// Generated 11 Feb, 2015 9:12:09 AM by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Feesdetails generated by hbm2java
 */
@Entity
@Table(name = "fee_feesdetails")
public class Feesdetails implements java.io.Serializable {

	private Integer feesdetailsid;
	private Integer sid;
	private String dateoffees;
	private String grandtotal;
	private String miscamount;
	private String balamount;
	private String amountpercat;
	private String academicyear;
	private int branchid;
	private int userid;
	
	public Feesdetails() {
	}

	public Feesdetails(Integer sid, String dateoffees, String grandtotal,
			String miscamount, String balamount, String amountpercat, String academicyear,
			int branchid, int userid) {
		this.sid = sid;
		this.dateoffees = dateoffees;
		this.grandtotal = grandtotal;
		this.miscamount = miscamount;
		this.balamount = balamount;
		this.amountpercat = amountpercat;
		this.academicyear = academicyear;
		this.branchid = branchid;
		this.userid = userid;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "feesdetailsid", unique = true, nullable = false)
	public Integer getFeesdetailsid() {
		return this.feesdetailsid;
	}

	public void setFeesdetailsid(Integer feesdetailsid) {
		this.feesdetailsid = feesdetailsid;
	}

	@Column(name = "sid")
	public Integer getSid() {
		return this.sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	@Column(name = "dateoffees", length = 100)
	public String getDateoffees() {
		return this.dateoffees;
	}

	public void setDateoffees(String dateoffees) {
		this.dateoffees = dateoffees;
	}

	@Column(name = "grandtotal", length = 45)
	public String getGrandtotal() {
		return this.grandtotal;
	}

	public void setGrandtotal(String grandtotal) {
		this.grandtotal = grandtotal;
	}

	@Column(name = "miscamount", length = 45)
	public String getMiscamount() {
		return this.miscamount;
	}

	public void setMiscamount(String miscamount) {
		this.miscamount = miscamount;
	}

	@Column(name = "balamount", length = 45)
	public String getBalamount() {
		return this.balamount;
	}

	public void setBalamount(String balamount) {
		this.balamount = balamount;
	}

	@Column(name = "amountpercat", length = 45)
	public String getAmountpercat() {
		return this.amountpercat;
	}

	public void setAmountpercat(String amountpercat) {
		this.amountpercat = amountpercat;
	}

	/**
	 * @return the academicyear
	 */
	public String getAcademicyear() {
		return academicyear;
	}

	/**
	 * @param academicyear the academicyear to set
	 */
	public void setAcademicyear(String academicyear) {
		this.academicyear = academicyear;
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
