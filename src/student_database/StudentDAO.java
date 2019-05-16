package student_database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import student_database.ConnectionParameters;

/**
 * DAO class for accessing students. NB! There is no user input/output in this
 * class!
 * 
 * @author Kari
 * @version 1.1 2017-10-22
 */
public class StudentDAO {
	private final String username;
	private final String password;
	private final String databaseURL;

	public StudentDAO() throws Exception {
		username = ConnectionParameters.username;
		password = ConnectionParameters.password;
		databaseURL = ConnectionParameters.databaseURL;

		// In *Tomcat 8* the JDBC driver must be explicitly loaded as below
		try {
			Class.forName(ConnectionParameters.jdbcDriver);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * Opens a new database connection
	 * 
	 * @throws SQLException
	 */
	private Connection openConnection() throws SQLException {
		Connection dbConnection = DriverManager.getConnection(databaseURL, username, password);
		return dbConnection;
	}

	/**
	 * Closes an existing database connection
	 * 
	 * @throws SQLException
	 */
	private void closeConnection(Connection dbConnection) throws SQLException {
		if (dbConnection != null) {
			dbConnection.close();
		}
	}

	/**
	 * Retrieves all students from the database.
	 * 
	 * @return ArrayList<Student> - a List of Students
	 * @throws SQLException
	 */
	public ArrayList<Student> getAllStudents() throws SQLException {
		ArrayList<Student> studentList = new ArrayList<Student>();
		Connection dbConnection = null;

		try {
			dbConnection = openConnection();

			String sqlText = "SELECT * FROM Student";

			PreparedStatement preparedStatement = dbConnection.prepareStatement(sqlText);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String firstName = resultSet.getString("firstname");
				String lastName = resultSet.getString("lastname");
				String streetAddress = resultSet.getString("streetaddress");
				int postCode = resultSet.getInt("postcode");
				String postOffice = resultSet.getString("postoffice");

				studentList.add(new Student(id, firstName, lastName, streetAddress, postCode, postOffice));
			}

			return studentList;

		} catch (SQLException sqle) {
			throw sqle; // Let the caller decide what to do with the exception

		} finally {
			closeConnection(dbConnection);
		}
	}

	/**
	 * Retrieves students from the database for the given id
	 * 
	 * @return ArrayList<Student> - a List of Students
	 * @throws SQLException
	 */
	public Student getStudentById(int studentId) throws SQLException {
		Connection dbConnection = null;

		try {
			dbConnection = openConnection();

			String sqlText = "SELECT * FROM Student WHERE id = ?";

			PreparedStatement preparedStatement = dbConnection.prepareStatement(sqlText);
			preparedStatement.setInt(1, studentId);

			ResultSet resultSet = preparedStatement.executeQuery();

			Student student = new Student();

			if (resultSet.next()) {

				// 7. Each column value has to be retrieved separately as below
				int id = resultSet.getInt("id");
				String firstName = resultSet.getString("firstname");
				String lastName = resultSet.getString("lastname");
				String streetAddress = resultSet.getString("streetaddress");
				int postCode = resultSet.getInt("postcode");
				String postOffice = resultSet.getString("postoffice");

				student.setId(id);
				student.setFirstName(firstName);
				student.setLastName(lastName);
				student.setStreetAddress(streetAddress);
				student.setPostCode(postCode);
				student.setPostOffice(postOffice);

			}

			return student;

		} catch (SQLException sqle) {
			throw sqle; // Let the caller decide what to do with the exception

		} finally {
			closeConnection(dbConnection);
		}
	}

	// Inserts a new row into the Student table

	public int insertStudent(Student student) {

		Connection dbConnection = null;

		try {
			dbConnection = openConnection();

			String sqlText = "INSERT INTO Student (id, firstname, lastname, streetaddress, postcode, postoffice) VALUES (?, ?, ?, ?, ?, ?)";

			PreparedStatement preparedStatement = dbConnection.prepareStatement(sqlText);
			preparedStatement.setInt(1, student.getId());
			preparedStatement.setString(2, student.getFirstName());
			preparedStatement.setString(3, student.getLastName());
			preparedStatement.setString(4, student.getStreetAddress());
			preparedStatement.setInt(5, student.getPostCode());
			preparedStatement.setString(6, student.getPostOffice());

			preparedStatement.executeUpdate();

			return 0;

		} catch (SQLException sqle) {
			// First, check if the problem is primary key violation (the error code is
			// vendor-dependent)
			if (sqle.getErrorCode() == ConnectionParameters.PK_VIOLATION_ERROR) {
				return 1;
			} else {
				return -1;
			}

		} finally {
			if (dbConnection != null) {
				try {
					closeConnection(dbConnection);
				} catch (SQLException sqle) {
					System.out.println("\nClose connection failed. \n" + sqle.getMessage());
				}
			}
		}
	}

	public void deleteStudent(int studentId) {

		Connection dbConnection = null;

		try {
			dbConnection = openConnection();

			String sqlText = "DELETE FROM Student WHERE id = ?";

			PreparedStatement preparedStatement = dbConnection.prepareStatement(sqlText);
			preparedStatement.setInt(1, studentId);

			preparedStatement.executeQuery();

		} catch (SQLException sqle) {

			// First, check if the problem is primary key violation (the error code is
			// vendor-dependent)
			if (sqle.getErrorCode() == ConnectionParameters.PK_VIOLATION_ERROR) {
				System.out.println(
						"Cannot delete the student. " + "The student id (" + studentId + ") is already in use.");
			} else {
				System.out.println("===== Database error =====\n" + sqle.getMessage());
			}

		} finally {
			if (dbConnection != null) {
				try {
					dbConnection.close();
				} catch (SQLException sqle) {
					System.out.println("\nClose connection failed. \n" + sqle.getMessage());
				}
			}

		}

	}

}
// End