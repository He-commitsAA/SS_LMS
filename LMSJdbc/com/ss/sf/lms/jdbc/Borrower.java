/**
 * 
 */
package com.ss.sf.lms.jdbc;

import java.io.Serializable;

/**
 * @author William
 * 
 * The Borrower class is the domain object that holds information on borrowers that is sent and received by BorrowerDAO from tbl_borrower.
 *
 */
public class Borrower implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8807369947180545224L;

	/**
	 * the attributes.
	 */
	private Integer cardNo;
	private String name;
	private String address;
	private String phone;

	/**
	 * @return the cardNo
	 */
	public Integer getCardNo() {
		return cardNo;
	}

	/**
	 * @param cardNo theCardNo to set
	 */
	public void setCardNo(Integer cardNo) {
		this.cardNo = cardNo;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/*
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param setAddress the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/*
	 * @return the phone
	 */

	public String getPhone() {
		return phone;
	}

	/**
	 * @param setPhone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cardNo == null) ? 0 : cardNo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Borrower other = (Borrower) obj;
		if (cardNo == null) {
			if (other.cardNo != null)
				return false;
		} else if (!cardNo.equals(other.cardNo))
			return false;
		return true;
	}
}