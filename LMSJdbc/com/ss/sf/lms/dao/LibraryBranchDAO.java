/**
 * 
 */
package com.ss.sf.lms.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.sf.lms.domain.LibraryBranch;

/**
 * @libraryBranch William
 * 
 * Sends and receives information from tbl_library_branch.
 *
 */
public class LibraryBranchDAO extends BaseDAO<LibraryBranch> {
	
	/*
	 * addLibraryBranch() adds new library branches to the system.
	 */

	public void addLibraryBranch(LibraryBranch libraryBranch) throws ClassNotFoundException, SQLException, IOException {
		save("Insert into tbl_library_branch (branchId,branchName,branchAddress) values (?,?,?)",
				new Object[] { libraryBranch.getBranchId(), libraryBranch.getBranchName(), libraryBranch.getBranchAddress() });
	}

	/*
	 * updateLibraryBranch() updates existing library branch information. To be used by Librarians.
	 */
	public void updateLibraryBranch(LibraryBranch libraryBranch)
			throws ClassNotFoundException, SQLException, IOException {
		save("update tbl_library_branch set branchName = ?, branchAddress = ? where branchId = ?", new Object[] {
				libraryBranch.getBranchName(), libraryBranch.getBranchAddress(), libraryBranch.getBranchId() });
	}
	
	/*
	 * deleteLibraryBranch() deletes library branch information.
	 */

	public void deleteLibraryBranch(LibraryBranch libraryBranch) throws ClassNotFoundException, SQLException, IOException {
		save("delete from tbl_library_branch where branchId = ?", new Object[] { libraryBranch.getBranchId() });
	}
	
	/*
	 * readLibraryBranches() reads in all information from tbl_library_branch.
	 */

	public List<LibraryBranch> readLibraryBranches() throws SQLException, ClassNotFoundException, IOException {
		return read("select * from tbl_library_branch ", null);
	}
	
	/*
	 * readLibraryBranchesByBranchId() reads the library branch that matches a given branchId.
	 */

	public List<LibraryBranch> readLibraryBranchesByBranchId(Integer branchId) throws SQLException, ClassNotFoundException, IOException {
		return read("select * from tbl_library_branch where branchId  = ? ", new Object[] { branchId });
	}
	
	/*
	 * readLibraryBranchesByBranchAddress() reads the library branch with the address matching the one you gave.
	 */

	public List<LibraryBranch> readLibraryBranchesByBranchAddress(String branchAddress) throws SQLException, ClassNotFoundException, IOException {
		return read("select * from tbl_library_branch where branchAddress  = ? ", new Object[] { branchAddress });
	}
	
	/*
	 * readLibraryBranchesByBranchName() reads the library branch with the name that matches the one you gave.
	 */

	public List<LibraryBranch> readLibraryBranchesByBranchName(String branchName) throws SQLException, ClassNotFoundException, IOException {
		return read("select * from tbl_library_branch where branchName  = ? ", new Object[] { branchName });
	}
	
	/*
	 * extractData() is LibraryBranchDAO's implementation of that method in BaseDAO. Reads branch information into a List.
	 */
	@Override
	List<LibraryBranch> extractData(ResultSet rs) throws SQLException, ClassNotFoundException, IOException {
		List<LibraryBranch> libraryBranches = new ArrayList<>();
		while (rs.next()) {
			LibraryBranch libraryBranch = new LibraryBranch();
			libraryBranch.setBranchId(rs.getInt("branchId"));
			libraryBranch.setBranchName(rs.getString("branchName"));
			libraryBranch.setBranchAddress(rs.getString("branchAddress"));
			libraryBranches.add(libraryBranch);
		}
		return libraryBranches;
	}

}
