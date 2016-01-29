package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.UpdatableResultSet;

import data.UserInfo;
import factory.UserInfoDaoFactory;

public class ChangeUserinfo extends HttpServlet {

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
		PrintWriter out= response.getWriter();
		response.setCharacterEncoding("UTF-8"); 
		request.setCharacterEncoding("UTF-8");

		String flag = request.getParameter("press");
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		if(flag!=null&&flag.equals("top-up")){
			UserInfo userInfo = UserInfoDaoFactory.getInstance().getByUsername(username);
			int a = Integer.parseInt(userInfo.getMoney())+100;
			String money = String.valueOf(a);
			UserInfoDaoFactory.getInstance().update("money", username, money);
			out.print("<script language='javascript'>alert('top-up successed');window.location.href='./user/userInfo.jsp';</script>");
		}else if(flag!=null&&flag.equals("changePassword")){
			try {
				String userpassword = request.getParameter("password");
				if(userpassword ==null){
					out.print("<script language='javascript'>alert('password can't be null');window.location.href='./user/userInfo.jsp';</script>");
				}else {
					UserInfoDaoFactory.getInstance().update("password", username, userpassword);
					out.print("<script language='javascript'>alert('change password successed');window.location.href='./user/userInfo.jsp';</script>");
				}
			} catch (Exception e) {
				System.err.println("数据库查询错误:"+e);
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
