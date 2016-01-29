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

import factory.CarInfoDaoFactory;

public class carInfo_op extends HttpServlet {

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
			String imagePath = request.getParameter("fiximagePath");
			if(imagePath ==null){
				out.print("<script language='javascript'>alert('路径不能为空');window.location.href='./admin/dbcarInfo.jsp';</script>");
			}else {
				if(!CarInfoDaoFactory.getInstance().isCarInfo(imagePath)){
					out.print("<script language='javascript'>alert('该路径不存在');window.location.href='./admin/dbcarInfo.jsp';</script>");
				}else {
					String colName = request.getParameter("colname");
					String changeInfo = request.getParameter("changeInfo");
					if(changeInfo.equals("")){
						changeInfo = null;
					}
					if(colName==null){
						out.print("<script language='javascript'>alert('列名不能为空');window.location.href='./admin/dbcarInfo.jsp';</script>");
					}else if(colName.equals("imagePath")||colName.equals("carName")||colName.equals("carInfo")||colName.equals("carPrice")||colName.equals("rentBy")){
						CarInfoDaoFactory.getInstance().update(colName, imagePath, changeInfo);
						out.print("<script language='javascript'>alert('修改成功');window.location.href='./admin/dbcarInfo.jsp';</script>");
					}else{
						out.print("<script language='javascript'>alert('列名不存在');window.location.href='./admin/dbcarInfo.jsp';</script>");
					}
				}
				
			}
		}else if(flag!=null&&flag.equals("deleteCol")){
			String imagePath = request.getParameter("deleteCarInfo");
			if(imagePath ==null){
				out.print("<script language='javascript'>alert('删除用户不能为空');window.location.href='./admin/dbcarInfo.jsp';</script>");
			}else {
				if (!CarInfoDaoFactory.getInstance().isCarInfo(imagePath)) {
					out.print("<script language='javascript'>alert('删除用户不存在');window.location.href='./admin/dbcarInfo.jsp';</script>");
				}else {
					CarInfoDaoFactory.getInstance().delete(imagePath);
					out.print("<script language='javascript'>alert('删除成功');window.location.href='./admin/dbcarInfo.jsp';</script>");
				}
			}
		}else if(flag!=null&&flag.equals("add")){
			String imagePath = request.getParameter("imagePath");
			String carName = request.getParameter("carName");
			String carInfo = request.getParameter("carInfo");
			String carPrice = request.getParameter("carPrice");
			String rentBy = request.getParameter("rentBy");
			if(CarInfoDaoFactory.getInstance().isCarInfo(imagePath)){
				out.print("<script language='javascript'>alert('该路径已存在');window.location.href='./admin/dbcarInfo.jsp';</script>");
			}else {
				CarInfoDaoFactory.getInstance().insert(imagePath, carName, carInfo, carPrice, rentBy);
				out.print("<script language='javascript'>alert('增加成功');window.location.href='./admin/dbcarInfo.jsp';</script>");
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
