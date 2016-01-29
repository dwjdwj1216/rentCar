package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import data.UserInfo;
import factory.UserInfoDaoFactory;


public class Login extends HttpServlet {

	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out= response.getWriter();
		response.setCharacterEncoding("UTF-8"); 
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String userpassword = request.getParameter("password");
		UserInfo userInfo = UserInfoDaoFactory.getInstance().getByUsername(username);
		try {
			HttpSession session = request.getSession();
			if(userInfo!=null){
				if(!username.equals("123")&&userpassword.equals(userInfo.getPassword())){
					String realName = userInfo.getRealName();
					
					session.setAttribute("realName", realName);
					session.setAttribute("username", username);
					session.setAttribute("status", "user");
					request.getRequestDispatcher("./user/user.jsp").forward(request, response);
				}else if(username.equals("123")&&userpassword.equals(userInfo.getPassword())){
					session.setAttribute("status", "visitor");
					request.getRequestDispatcher("./visitor/visitor.jsp").forward(request, response);
				}else if(!userpassword.equals(userInfo.getPassword())){
					out.print("<script language='javascript'>alert('密码错误');window.location.href='./index.jsp';</script>");
				}
			}else {
				out.print("<script language='javascript'>alert('该用户名不存在');window.location.href='./index.jsp';</script>");
			}
		} catch (Exception e) {
			System.err.println("数据库查询错误:"+e);
		}
		out.flush();
	}
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
