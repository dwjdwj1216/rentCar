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
import javax.servlet.http.HttpSession;

import factory.UserInfoDaoFactory;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class userInfo_op extends HttpServlet {

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
			String username = request.getParameter("fixUsername");
			if(username ==null){
				out.print("<script language='javascript'>alert('选择的用户名不能为空');window.location.href='./admin/dbuserInfo.jsp';</script>");
			}else {
				if(!UserInfoDaoFactory.getInstance().isUser(username)){
					out.print("<script language='javascript'>alert('选择的用户名不存在');window.location.href='./admin/dbuserInfo.jsp';</script>");
				}else {
					String colName = request.getParameter("colname");
					String changeInfo = request.getParameter("changeInfo");
					String sql = "update userInfo set "+colName+"='"+changeInfo+"' where username='"+username+"'";
					if(colName==null){
						out.print("<script language='javascript'>alert('列名不能为空');window.location.href='./admin/dbuserInfo.jsp';</script>");
					}else if(colName.equals("money")&&!isNumeric(changeInfo)){
						out.print("<script language='javascript'>alert('请输入正确的money数');window.location.href='./admin/dbuserInfo.jsp';</script>");
					}else if(colName.equals("username")||colName.equals("password")||colName.equals("realName")||(colName.equals("money")&&isNumeric(changeInfo))){
						UserInfoDaoFactory.getInstance().update(colName, username, changeInfo);
						out.print("<script language='javascript'>alert('修改成功');window.location.href='./admin/dbuserInfo.jsp';</script>");
					}else{
						out.print("<script language='javascript'>alert('列名不存在');window.location.href='./admin/dbuserInfo.jsp';</script>");
					}
				}
			}
		}else if(flag!=null&&flag.equals("deleteCol")){
			String username = request.getParameter("deleteUsername");
			if(username ==null){
				out.print("<script language='javascript'>alert('删除用户不能为空');window.location.href='./admin/dbuserInfo.jsp';</script>");
			}else {
				if(!UserInfoDaoFactory.getInstance().isUser(username)){
					out.print("<script language='javascript'>alert('删除用户不存在');window.location.href='./admin/dbuserInfo.jsp';</script>");
				}else {
					UserInfoDaoFactory.getInstance().delete(username);
					out.print("<script language='javascript'>alert('删除成功');window.location.href='./admin/dbuserInfo.jsp';</script>");
				}
			}
		}else if(flag!=null&&flag.equals("add")){
			String username = request.getParameter("username");
			String userpassword = request.getParameter("password");
			String realName = request.getParameter("realName");
			String money = request.getParameter("money");
			if(!isNumeric(money)){
				out.print("<script language='javascript'>alert('请输入正确的money数');window.location.href='./admin/dbuserInfo.jsp';</script>");
			}else {
				if(UserInfoDaoFactory.getInstance().isUser(username)){
					out.print("<script language='javascript'>alert('用户名已经存在了');window.location.href='./admin/dbuserInfo.jsp';</script>");
				}else {
					UserInfoDaoFactory.getInstance().insert(username, userpassword, realName, money);
					out.print("<script language='javascript'>alert('添加成功');window.location.href='./admin/dbuserInfo.jsp';</script>");
				}
			}
		}

		out.flush();
	}

	private boolean isNumeric(String money) {
		Pattern pattern = Pattern.compile("[0-9]*"); 
		Matcher isNum = pattern.matcher(money);
		if( !isNum.matches() ){
		    return false; 
		} 
		return true;
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
