package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.CarInfo;
import data.UserInfo;
import factory.CarInfoDaoFactory;
import factory.RentListDaoFactory;
import factory.UserInfoDaoFactory;

public class Rent extends HttpServlet {

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

		String carName = request.getParameter("carName");
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		UserInfo userInfo = UserInfoDaoFactory.getInstance().getByUsername(username);
		CarInfo carInfo = CarInfoDaoFactory.getInstance().getByCarName(carName);
		int own = 0,cost = 0;
		if(userInfo.getMoney()!=null){
			own=Integer.parseInt(userInfo.getMoney());
		}
		String num = carInfo.getCarPrice().split("/")[0];
		if(num!=null){
			cost=Integer.parseInt(num);
		}
		if(own<cost){
			out.print("<script language='javascript'>alert('please top-up');window.location.href='./user/userInfo.jsp';</script>");
		}else{
			String money = String.valueOf(own-cost);
			CarInfoDaoFactory.getInstance().update("rentBy", carInfo.getImagePath(),username);
			UserInfoDaoFactory.getInstance().update("money", username, money);
			out.print("<script language='javascript'>alert('rent successed');window.location.href='./user/rentList.jsp';</script>");
			Calendar now = Calendar.getInstance();
			int year = now.get(Calendar.YEAR);
			int month = now.get(Calendar.MONTH);
			int day = now.get(Calendar.DATE);
			int minute = now.get(Calendar.MINUTE);
			int hour = now.get(Calendar.HOUR);
			int second = now.get(Calendar.SECOND);
			int ap = now.get(Calendar.AM_PM);
			String rentTime = null;
			if(ap==0){
				rentTime = ""+year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second+"am";			
			}else if(ap==1){
				rentTime = ""+year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second+"pm";	
			}
			RentListDaoFactory.getInstance().insert(carName, rentTime, carInfo.getCarInfo(), username);
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
