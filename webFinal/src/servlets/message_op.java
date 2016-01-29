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

import factory.MessageDaoFactory;

public class message_op extends HttpServlet {

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
			String commenter = request.getParameter("fixPkcommenter");
			String time = request.getParameter("fixPktime");
			if(commenter ==null||time==null){
				out.print("<script language='javascript'>alert('关键值不能为空');window.location.href='./admin/dbmessage.jsp';</script>");
			}else {
				if(!MessageDaoFactory.getInstance().isMessage(commenter, time)){
					out.print("<script language='javascript'>alert('找不到符合条件的信息');window.location.href='./admin/dbmessage.jsp';</script>");
				}else {
					String colName = request.getParameter("colname");
					String changeInfo = request.getParameter("changeInfo");
					if(colName==null){
						out.print("<script language='javascript'>alert('列名不能为空');window.location.href='./admin/dbmessage.jsp';</script>");
					}else if(colName.equals("commenter")||colName.equals("messageContent")||colName.equals("time")){
						MessageDaoFactory.getInstance().update(colName, commenter, time, changeInfo);
						out.print("<script language='javascript'>alert('修改成功');window.location.href='./admin/dbmessage.jsp';</script>");
					}else{
						out.print("<script language='javascript'>alert('列名不存在');window.location.href='./admin/dbmessage.jsp';</script>");
					}
				}
			}
		}else if(flag!=null&&flag.equals("deleteCol")){
			String commenter = request.getParameter("deletePkcommenter");
			String time = request.getParameter("deletePktime");
			if(commenter ==null||time==null){
				out.print("<script language='javascript'>alert('关键值不能为空');window.location.href='./admin/dbmessage.jsp';</script>");
			}else {
				if(!MessageDaoFactory.getInstance().isMessage(commenter, time)){
					out.print("<script language='javascript'>alert('留言不存在');window.location.href='./admin/dbmessage.jsp';</script>");
				}else {
					MessageDaoFactory.getInstance().delete(commenter, time);
					out.print("<script language='javascript'>alert('删除成功');window.location.href='./admin/dbmessage.jsp';</script>");
				}
			}
		}else if(flag!=null&&flag.equals("add")){
			String commenter = request.getParameter("commenter");
			String time = request.getParameter("time");
			String messageContent = request.getParameter("messageContent");
			if(MessageDaoFactory.getInstance().isMessage(commenter, time)){
				out.print("<script language='javascript'>alert('该留言已存在');window.location.href='./admin/dbmessage.jsp';</script>");
			}else {
				MessageDaoFactory.getInstance().insert(commenter, messageContent, time);
				out.print("<script language='javascript'>alert('增加成功');window.location.href='./admin/dbmessage.jsp';</script>");
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
