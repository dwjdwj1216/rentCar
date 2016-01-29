package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import factory.UserInfoDaoFactory;

public class Register extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		response.setCharacterEncoding("UTF-8"); 
		request.setCharacterEncoding("UTF-8");
		
		String username = request.getParameter("username");
		String userpassword = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		String realName = request.getParameter("realName");
		if(UserInfoDaoFactory.getInstance().isUser(username)){
			out.print("<script language='javascript'>alert('用户名已经存在');window.location.href='./register.jsp';</script>");
		}else {
			if(userpassword.equals(confirmPassword)==false){
				out.print("<script language='javascript'>alert('password do not equal with confirm password');window.location.href='./register.jsp';</script>");
			}else {
				UserInfoDaoFactory.getInstance().insert(username, userpassword, realName, "0");
				out.print("<script language='javascript'>alert('注册成功');window.location.href='./user.jsp';</script>");
			}
		}
		out.flush();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
