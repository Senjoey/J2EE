package edu.nju.severlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.nju.factory.EJBFactory;
import edu.nju.factory.ServiceFactory;
import edu.nju.models.Selection;
import edu.nju.models.Student;
import edu.nju.service.StudentService;
import edu.nju.state.StudentState;

/**
 * Servlet implementation class HelloIndex
 */
@WebServlet("/CheckServlet")
public class CheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	StudentService userService = (StudentService) EJBFactory.getEJB("ejb:/J2EELabEJB/StudentServiceBean!edu.nju.StudentService");
	// private DataSource datasource = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext context = getServletContext();
		if(null == request.getParameter("logout")) {//not logout, visitor++
			String strTotal = (String) context.getAttribute("total");
			String strVisitorTotal = (String) context.getAttribute("visitorTotal");
			int total = Integer.parseInt(strTotal);
			int visitorTotal = Integer.parseInt(strVisitorTotal);
			total++;
			visitorTotal++;
			context.setAttribute("total", Integer.toString(total));
			context.setAttribute("visitorTotal", Integer.toString(visitorTotal));
		} else {//logout
			String strLoginTotal = (String) context.getAttribute("loginTotal");
			String strVisitorTotal = (String) context.getAttribute("visitorTotal");
			int loginTotal = Integer.parseInt(strLoginTotal);
			int visitorTotal = Integer.parseInt(strVisitorTotal);
			if (loginTotal > 0) {
				loginTotal--;
				visitorTotal++;
			}
			context.setAttribute("loginTotal", Integer.toString(loginTotal));
			context.setAttribute("visitorTotal", Integer.toString(visitorTotal));
			HttpSession session = request.getSession();
			session.invalidate();
		}
		response.sendRedirect("jsp/login.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		StudentState state = checkLogin(Integer.parseInt(id), password, request);
		switch (state) {
		case PasswordRight:
			ServletContext context = getServletContext();
			String strLoginTotal = (String) context.getAttribute("loginTotal");
			String strVisitorTotal = (String) context.getAttribute("total");
			int loginTotal = Integer.parseInt(strLoginTotal);
			int visitorTotal = Integer.parseInt(strVisitorTotal);
			visitorTotal--;
			loginTotal++;
			context.setAttribute("loginTotal", Integer.toString(loginTotal));
			context.setAttribute("visitorTotal", Integer.toString(visitorTotal));
			response.sendRedirect("jsp/home.jsp");
			break;
		case PasswordWrong:
			response.sendRedirect("jsp/wrong.jsp");
			break;
		case StudentNotExists:
			response.sendRedirect("jsp/error.jsp");
			break;
		default:
			break;
		}
	}

	/**
	 * @param id
	 * @param password
	 * @param request
	 * @return
	 */
	private StudentState checkLogin(int id, String password, HttpServletRequest request) {
//		Student dbStudent = ServiceFactory.getStudentService().getStudent(id);
		Student dbStudent = userService.getStudent(id);
		if (null == dbStudent) {// 1. The student does't exist
			return StudentState.StudentNotExists;
		} else {// 2. The student exists
			if (!password.equals(dbStudent.getPassword())) {// 2.1 The password
															// is wrong
				return StudentState.PasswordWrong;
			} else {// 2.2 The password is right
				String name = dbStudent.getName();
				HttpSession session = request.getSession(true);
				session.setAttribute("studentId", id);
				session.setAttribute("studentName", name);
				checkSelection(id, request);
				return StudentState.PasswordRight;
			}
		}
	}

	private void checkSelection(int studentID, HttpServletRequest request) {
		ArrayList<Selection> selections = ServiceFactory.getSelectionService().getSelectionOfStu(studentID);
		HttpSession session = request.getSession();
		session.setAttribute("selections", selections);
	}

}
