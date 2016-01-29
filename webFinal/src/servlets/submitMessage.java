package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Timestamp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;

import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import factory.MessageDaoFactory;

public class submitMessage extends HttpServlet {

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

		String messageContent = request.getParameter("messageContent");
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH);
		int day = now.get(Calendar.DATE);
		int minute = now.get(Calendar.MINUTE);
		int hour = now.get(Calendar.HOUR);
		int second = now.get(Calendar.SECOND);
		int ap = now.get(Calendar.AM_PM);
		String messageTime = null;
		if(ap==0){
			messageTime = ""+year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second+"am";			
		}else if(ap==1){
			messageTime = ""+year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second+"pm";	
		}
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		if(username!=null&&messageContent!=null){
			MessageDaoFactory.getInstance().insert(username, messageContent, messageTime);
			out.print("<script language='javascript'>alert('submit successed');window.location.href='./user/messageList.jsp';</script>");
		}else {
			out.print("<script language='javascript'>alert('you do something wrong');window.location.href='./user/message.jsp';</script>");
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
