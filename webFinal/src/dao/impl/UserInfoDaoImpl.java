package dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;

import servlets.userInfo_op;
import tool.DBO;
import dao.UserInfoDao;
import data.CarInfo;
import data.UserInfo;

public class UserInfoDaoImpl implements UserInfoDao {

	@Override
	public UserInfo getByUsername(String username) {
		String sql = "select * from userInfo where username='"+username+"'";
		ResultSet rs = null;
		
		try {
			if(isUser(username)){
				rs = DBO.select(sql);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String password = "";
		String realName = "";
		String money = "";
		try {
			while(rs!=null&&rs.next()){
				password = rs.getString("password");
				realName = rs.getString("realName");
				money = rs.getString("money");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		UserInfo userInfo = new UserInfo(username, password, realName, money);
		return userInfo;
	}

	@Override
	public boolean update(String colName,String username,String changeInfo) {
		boolean flag = false;
		String sql = "update userInfo set "+colName+"='"+changeInfo+"' where username='"+username+"'";
		try {
			DBO.update(sql);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean insert(String username, String password, String realName,
			String money) {
		boolean flag = true;
		String sql = "insert into userInfo(username,password,realName,money) value('"+username+"','"+password+"','"+realName+"','"+money+"')";
		try {
			DBO.insert(sql);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean delete(String username) {
		boolean flag = false;
		String sql = "delete from userInfo where username='"+username+"'";
		try {
			if(isUser(username)){
				DBO.delete(sql);
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean isUser(String username) {
		boolean flag = false;
		String sql = "select username from userInfo where username='"+username+"'";
		ResultSet rs = null;
		try {
			rs = DBO.select(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			while(rs!=null&&rs.next()){
				flag=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}



	@Override
	public ArrayList<UserInfo> getAllDate() {
		ArrayList<UserInfo> allDate = new ArrayList<UserInfo>();
		ResultSet rs = null;
		String sql = "select * from userInfo";
		String username = "";
		try {
			rs = DBO.select(sql);
			while(rs!=null&&rs.next()){
				username = rs.getString("username");
				allDate.add(getByUsername(username));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return allDate;
	}

}
