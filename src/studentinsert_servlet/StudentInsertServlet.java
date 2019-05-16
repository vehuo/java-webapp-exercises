package studentinsert_servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import student_database.StudentDAO;
import student_database.Student;

/**
 * Servlet implementation class StudentInsertServlet
 */
@WebServlet("/InsertStudent")
public class StudentInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			
			int givenId = -2;
			int postalCode = -2;
			
//			 1. Retrieve the values of the request parameters
			String studentId = request.getParameter("txtId");

			if (studentId != null) {
				try {
					givenId = Integer.parseInt(studentId);
					
				} catch (Exception ex) { }
			}
			
			String firstName = request.getParameter("txtFirstName");
			String lastName = request.getParameter("txtLastName");
			String streetAddress = request.getParameter("txtStreetAddress");
			String postCode = request.getParameter("txtPostCode");
			
			if (postCode != null) {
				try {
					postalCode = Integer.parseInt(postCode);
					
				} catch (Exception ex) { }
			}
			
			String postOffice = request.getParameter("txtPostOffice");
	

			// 2. Create the data to be sent to the JSP page
			StudentDAO studentDAO = new StudentDAO();
			Student student = new Student(givenId, firstName, lastName, streetAddress, postalCode, postOffice);
			
			int result = studentDAO.insertStudent(student);
			
			String message = "";
			
			if (result == 0) {
				message = "Student data saved successfully.";
			}
			
			if (result == 1) {
				message = "Cannot save the student data. Student id " + studentId + " is already in use";
			}
			
			if (result == -1) {
				message = "The database is temporarily unavailable. Please try again later.";
			}

			// 3. Add the data to the request attributes
			request.setAttribute("message", message);

			// 4. Forward the request back to the JSP page
			request.getRequestDispatcher("StudentInsertPage.jsp").forward(request, response);

		} catch (Exception ex) {
			System.out.println("The database is temporarily unavailable. Please try again later. \n");
			System.out.println("=== System error message (for the developer's eyes only) === \n" + ex.getMessage());
		}

	}

}