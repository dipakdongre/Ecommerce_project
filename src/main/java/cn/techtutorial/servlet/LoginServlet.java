package cn.techtutorial.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import cn.techtutorial.connection.DbCon;
import cn.techtutorial.dao.UserDao;
import cn.techtutorial.model.User;

@WebServlet("/user-login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("login.jsp");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		try(PrintWriter out = response.getWriter()){
			String email = request.getParameter("Login-email");
			String password = request.getParameter("Login-password");
			out.println(email+password);
			try {
				UserDao udao = new UserDao(DbCon.getConnection());
				User user= udao.userLogin(email,password);
				
				if(user!=null) {
					request.getSession().setAttribute("auth",user);
					response.sendRedirect("index.jsp");
					out.print("user login");
				}else {
					out.println("user login failed");
				}
			}catch(ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			
		}
	}

}
