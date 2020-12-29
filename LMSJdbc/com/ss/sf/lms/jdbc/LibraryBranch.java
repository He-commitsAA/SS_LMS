/**
 * 
 */
package com.ss.sf.lms.jdbc;

import java.io.Serializable;

/**
 * @author William
 * 
 * The LibraryBranch class is the domain object that can hold information that LibraryBranchDAO sends and receives from tbl_library_branch.
 *
 */
public class LibraryBranch implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 654654030873394471L;

	private Integer branchId;
	private String branchName;
	private String branchAddress;

	/**
	 * @return
	 */
	public Integer getBranchId() {
		return branchId;
	}

	/**
	 * @param
	 */
	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	/**
	 * @return
	 */
	public String getBranchName() {
		return branchName;
	}

	/**
	 * @param
	 */
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	/*
	 * @return
	 */
	public String getBranchAddress() {
		return branchAddress;
	}

	/**
	 * @param
	 */
	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((branchId == null) ? 0 : branchId.hashCode());
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
		LibraryBranch other = (LibraryBranch) obj;
		if (branchId == null) {
			if (other.branchId != null)
				return false;
		} else if (!branchId.equals(other.branchId))
			return false;
		return true;
	}
}