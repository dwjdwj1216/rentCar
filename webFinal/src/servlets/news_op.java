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

import factory.NewsDaoFactory;

public class news_op extends HttpServlet {

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
			String Title = request.getParameter("fixTitle");
			if(Title ==null){
				out.print("<script language='javascript'>alert('新闻标题不能为空');window.location.href='./admin/dbnews.jsp';</script>");
			}else {
				if(!NewsDaoFactory.getInstance().isNew(Title)){
					out.print("<script language='javascript'>alert('新闻标题不存在');window.location.href='./admin/dbnews.jsp';</script>");
				}else {
					String colName = request.getParameter("colname");
					String changeInfo = request.getParameter("changeInfo");
					if(colName==null){
						out.print("<script language='javascript'>alert('列名不能为空');window.location.href='./admin/dbnews.jsp';</script>");
					}else if(colName.equals("Title")||colName.equals("Url")||colName.equals("publishTime")){
						NewsDaoFactory.getInstance().update(colName, Title, changeInfo);
						out.print("<script language='javascript'>alert('修改成功');window.location.href='./admin/dbnews.jsp';</script>");
					}else{
						out.print("<script language='javascript'>alert('列名不存在');window.location.href='./admin/dbnews.jsp';</script>");
					}
				}
			}
		}else if(flag!=null&&flag.equals("deleteCol")){
			String Title = request.getParameter("deletenew");
			if(Title ==null){
				out.print("<script language='javascript'>alert('删除新闻标题不能为空');window.location.href='./admin/dbnews.jsp';</script>");
			}else {
				if(!NewsDaoFactory.getInstance().isNew(Title)){
					out.print("<script language='javascript'>alert('删除新闻不存在');window.location.href='./admin/dbnews.jsp';</script>");
				}else {
					NewsDaoFactory.getInstance().delete(Title);
					out.print("<script language='javascript'>alert('删除成功');window.location.href='./admin/dbnews.jsp';</script>");
				}
			}
		}else if(flag!=null&&flag.equals("add")){
			String Title = request.getParameter("Title");
			String Url = request.getParameter("Url");
			String publishTime = request.getParameter("publishTime");
			if(NewsDaoFactory.getInstance().isNew(Title)){
				out.print("<script language='javascript'>alert('该新闻已存在');window.location.href='./admin/dbnews.jsp';</script>");
			}else {
				NewsDaoFactory.getInstance().insert(Title, Url, publishTime);
				out.print("<script language='javascript'>alert('增加成功');window.location.href='./admin/dbnews.jsp';</script>");
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
