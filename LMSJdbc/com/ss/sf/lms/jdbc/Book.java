/**
 * 
 */
package com.ss.sf.lms.jdbc;

import java.io.Serializable;

/**
 * @author William
 * 
 * The Book class is the domain object that holds information on books that is sent and received by BookDAO from tbl_book.
 *
 */
public class Book implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6026209082979493344L;
	/**
	 * the book's attributes.
	 */
	private Integer bookId;
	private String title;
	private Integer authId;
	private Integer pubId;

	/**
	 * @return the bookId
	 */
	public Integer getBookId() {
		return bookId;
	}

	/**
	 * @param bookId theBookId to set
	 */
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/*
	 * @return the authId
	 */
	public Integer getAuthId() {
		return authId;
	}

	/**
	 * @param setAuthId the authId to set
	 */
	public void setAuthId(Integer authId) {
		this.authId = authId;
	}

	/*
	 * @return the pubId
	 */

	public Integer getPubId() {
		return pubId;
	}

	/**
	 * @param setPubId the pubId to set
	 */
	public void setPubId(Integer pubId) {
		this.pubId = pubId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookId == null) ? 0 : bookId.hashCode());
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
		Book other = (Book) obj;
		if (bookId == null) {
			if (other.bookId != null)
				return false;
		} else if (!bookId.equals(other.bookId))
			return false;
		return true;
	}
}