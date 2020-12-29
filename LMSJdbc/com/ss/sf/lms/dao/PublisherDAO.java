/**
 * 
 */
package com.ss.sf.lms.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.sf.lms.domain.Publisher;

/**
 * @publisher William
 * 
 * Sends and receives information from tbl_publisher.
 *
 */
public class PublisherDAO extends BaseDAO<Publisher> {
	
	/*
	 * addPublisher() adds a new publisher to the database.
	 */

	public void addPublisher(Publisher publisher) throws ClassNotFoundException, SQLException, IOException {
		save("Insert into tbl_publisher (publisherID,publisherName,publisherAddress,publisherPhone) values (?,?,?,?)",
				new Object[] { publisher.getPublisherId(), publisher.getPublisherName(),
						publisher.getPublisherAddress(), publisher.getPublisherPhone() });
	}
	
	/*
	 * updatePublisher() updates publisher information in the database.
	 */

	public void updatePublisher(Publisher publisher) throws ClassNotFoundException, SQLException, IOException {
		save("update tbl_publisher set publisherName = ?, publisherAddress = ?, publisherPhone = ? where publisherId = ?",
				new Object[] { publisher.getPublisherName(), publisher.getPublisherAddress(),
						publisher.getPublisherPhone(), publisher.getPublisherId() });
	}
	
	/*
	 * deletePublisher() deletes a publisher from tbl_publisher.
	 */

	public void deletePublisher(Publisher publisher) throws ClassNotFoundException, SQLException, IOException {
		save("delete from tbl_publisher where publisherId = ?", new Object[] { publisher.getPublisherId() });
	}
	
	/*
	 * readPublishers() reads all publishers in tbl_publisher.
	 */

	public List<Publisher> readPublishers() throws SQLException, ClassNotFoundException, IOException {
		return read("select * from tbl_publisher ", null);
	}

	/*
	 * readPublishersByPublisherName() reads the publisher matching the name you gave.
	 */
	public List<Publisher> readPublishersByPublisherName(String publisherName)
			throws SQLException, ClassNotFoundException, IOException {
		return read("select * from tbl_publisher where publisherName  = ? ", new Object[] { publisherName });
	}
	
	/*
	 * readPublishersByPublisherId() reads the publisher matching the id you gave.
	 */

	public List<Publisher> readPublishersByPublisherId(Integer publisherId)
			throws SQLException, ClassNotFoundException, IOException {
		return read("select * from tbl_publisher where publisherId  = ? ", new Object[] { publisherId });
	}
	
	/*
	 * extractData() is PublisherDAO's implementation of the method in BaseDAO. It puts publisher information into a List.
	 */

	@Override
	List<Publisher> extractData(ResultSet rs) throws SQLException, ClassNotFoundException, IOException {
		List<Publisher> publishers = new ArrayList<>();
		while (rs.next()) {
			Publisher publisher = new Publisher();
			publisher.setPublisherId(rs.getInt("publisherId"));
			publisher.setPublisherName(rs.getString("publisherName"));
			publisher.setPublisherAddress(rs.getString("publisherAddress"));
			publisher.setPublisherPhone(rs.getString("publisherPhone"));
			publishers.add(publisher);
		}
		return publishers;
	}

}
