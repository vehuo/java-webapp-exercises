package deletestudent_servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import student_database.StudentDAO;

/**
 * Servlet implementation class StudentDeleteServlet
 */
@WebServlet("/DeleteStudent")
public class StudentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			
			int givenId = -2;
			
//			 1. Retrieve the values of the request parameters
			String studentId = request.getParameter("studentId");

			if (studentId != null) {
				try {
					givenId = Integer.parseInt(studentId);
					
				} catch (Exception ex) { }
			}

			// 2. Create the data to be sent to the JSP page
			StudentDAO studentDAO = new StudentDAO();
			studentDAO.deleteStudent(givenId);

			// 3. Add the data to the request attributes
			request.setAttribute("givenId", givenId);

			// 4. Forward the request back to the JSP page
			request.getRequestDispatcher("studentListService").forward(request, response);

		} catch (Exception ex) {
			System.out.println("The database is temporarily unavailable. Please try again later. \n");
			System.out.println("=== System error message (for the developer's eyes only) === \n" + ex.getMessage());
		}

	}

}