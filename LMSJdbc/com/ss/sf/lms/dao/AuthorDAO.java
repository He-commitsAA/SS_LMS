/**
 * 
 */
package com.ss.sf.lms.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.sf.lms.domain.Author;

/**
 * @author William
 * 
 *	Sends and receives information on Authors in tbl_author.
 *
 */
public class AuthorDAO extends BaseDAO<Author> {
	
	/*
	 * addAuthor() adds a new author and their assigned Id to the database.
	 */

	public void addAuthor(Author author) throws ClassNotFoundException, SQLException, IOException {
		save("Insert into tbl_author (authorId,authorName) values (?,?)", new Object[] { author.getAuthorId(), author.getAuthorName() });
	}
	
	/*
	 * updateAuthor() changes an Author's name in the database.
	 */

	public void updateAuthor(Author author) throws ClassNotFoundException, SQLException, IOException {
		save("update tbl_author set authorName = ? where authorId = ?",
				new Object[] { author.getAuthorName(), author.getAuthorId() });
	}
	
	/*
	 * deleteAuthor() deletes an Author from the database.
	 */

	public void deleteAuthor(Author author) throws ClassNotFoundException, SQLException, IOException {
		save("delete from tbl_author where authorId = ?", new Object[] {author.getAuthorId()});
	}
	
	/*
	 * readAuthors() reads all Authors from the database table tbl_author
	 */

	public List<Author> readAuthors() throws SQLException, ClassNotFoundException, IOException {
		return read("select * from tbl_author ", null);
	}
	
	/*
	 * readAuthorsByName() is like readAuthors() but only returns Authors where the name matches the name you give.
	 */
	public List<Author> readAuthorsByName(String authorName) throws SQLException, ClassNotFoundException, IOException {
		return read("select * from tbl_author where authorName  = ? ", new Object[] {authorName});
	}
	
	/*
	 * readAuthorsById() is like readAuthors() but only returns the Author where the id matches the one you give.
	 */
	
	public List<Author> readAuthorsById(Integer authorId) throws SQLException, ClassNotFoundException, IOException {
		return read("select * from tbl_author where authorId  = ? ", new Object[] {authorId});
	}
	
	/*
	 * extractData() is AuthorDAO's implementation of that method in BaseDAO, and puts Author info into List for use in Java.
	 */

	@Override
	List<Author> extractData(ResultSet rs)  throws SQLException, ClassNotFoundException, IOException {
		List<Author> authors = new ArrayList<>();
		while (rs.next()) {
			Author author = new Author();
			author.setAuthorId(rs.getInt("authorId"));
			author.setAuthorName(rs.getString("authorName"));
			authors.add(author);
		}
		return authors;
	}

}
