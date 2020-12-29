/**
 * 
 */
package com.ss.sf.lms.jdbc;

import java.io.Serializable;

/**
 * @author William
 * 
 * The Author class is the domain object that holds information on authors that AuthorDAO sends and receives from tbl_author.
 *
 */
public class Author implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1642656842651507905L;
	
	/*
	 * the author's attributes
	 */
	private Integer authorId;
	private String authorName;
	/**
	 * @return the authorId
	 */
	public Integer getAuthorId() {
		return authorId;
	}
	/**
	 * @param authorId the authorId to set
	 */
	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}
	/**
	 * @return the authorName
	 */
	public String getAuthorName() {
		return authorName;
	}
	/**
	 * @param authorName the authorName to set
	 */
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authorId == null) ? 0 : authorId.hashCode());
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
		Author other = (Author) obj;
		if (authorId == null) {
			if (other.authorId != null)
				return false;
		} else if (!authorId.equals(other.authorId))
			return false;
		return true;
	}
	
	

}
