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

import factory.RentListDaoFactory;

public class rentList_op extends HttpServlet {

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
		if(flag!=null&&flag.equals("changeCol")){
			String rentTime = request.getParameter("fixRentTime");
			if(rentTime ==null){
				out.print("<script language='javascript'>alert('关键值不能为空');window.location.href='./admin/dbrentList.jsp';</script>");
			}else {
				if(!RentListDaoFactory.getInstance().isRentList(rentTime)){
					out.print("<script language='javascript'>alert('找不到符合条件的信息');window.location.href='./admin/dbrentList.jsp';</script>");
				}else {
					String colName = request.getParameter("colname");
					String changeInfo = request.getParameter("changeInfo");
					if(colName==null){
						out.print("<script language='javascript'>alert('列名不能为空');window.location.href='./admin/dbrentList.jsp';</script>");
					}else if(colName.equals("carName")||colName.equals("username")||colName.equals("rentTime")||colName.equals("Price")){
						RentListDaoFactory.getInstance().update(colName, rentTime, changeInfo);
						out.print("<script language='javascript'>alert('修改成功');window.location.href='./admin/dbrentList.jsp';</script>");
					}else{
						out.print("<script language='javascript'>alert('列名不存在');window.location.href='./admin/dbrentList.jsp';</script>");
					}
				}
			}
		}else if(flag!=null&&flag.equals("deleteCol")){
			String rentTime = request.getParameter("deleteRentTime");
			if(rentTime ==null){
				out.print("<script language='javascript'>alert('关键值不能为空');window.location.href='./admin/dbrentList.jsp';</script>");
			}else {
				if(!RentListDaoFactory.getInstance().isRentList(rentTime)){
					out.print("<script language='javascript'>alert('信息不存在');window.location.href='./admin/dbrentList.jsp';</script>");
				}else {
					RentListDaoFactory.getInstance().delete(rentTime);
					out.print("<script language='javascript'>alert('删除成功');window.location.href='./admin/dbrentList.jsp';</script>");
				}
			}
		}else if(flag!=null&&flag.equals("add")){
			
			String carName = request.getParameter("carName");
			String rentTime = request.getParameter("rentTime");
			String Price = request.getParameter("Price");
			String username =request.getParameter("username");
			if(RentListDaoFactory.getInstance().isRentList(rentTime)){
				out.print("<script language='javascript'>alert('该信息已存在');window.location.href='./admin/dbrentList.jsp';</script>");
			}else {
				RentListDaoFactory.getInstance().insert(carName, rentTime, Price, username);
				out.print("<script language='javascript'>alert('增加成功');window.location.href='./admin/dbrentList.jsp';</script>");
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
