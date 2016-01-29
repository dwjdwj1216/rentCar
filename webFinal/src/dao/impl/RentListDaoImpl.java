package dao.impl;

import java.security.AllPermission;
import java.sql.ResultSet;
import java.util.ArrayList;

import servlets.news_op;
import tool.DBO;
import dao.RentListDao;
import data.RentList;

public class RentListDaoImpl implements RentListDao {

	@Override
	public RentList getByRentTime(String rentTime) {
		String sql = "select * from rentList where rentTime='"+rentTime+"'";
		ResultSet rs = null;
		try {
			if(isRentList(rentTime)){
				rs = DBO.select(sql);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String carName = "";
		String price = "";
		String username = "";
		
		try {
			while(rs!=null && rs.next()){
				carName = rs.getString("carName");
				price = rs.getString("Price");
				username = rs.getString("username");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		RentList rentList = new RentList(carName, rentTime, price, username);
		return rentList;
	}

	@Override
	public boolean update(String colName,String rentTime,String changeInfo) {
		boolean flag = false;
		String sql = "update rentList set "+colName+"='"+changeInfo+"' where rentTime='"+rentTime+"'";
		try {
			DBO.update(sql);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	

	@Override
	public boolean insert(String carName, String rentTime, String price,
			String username) {
		boolean flag = true;
		String sql="insert into rentList(carName,rentTime,Price,username) value('"+carName+"','"+rentTime+"','"+price+"','"+username+"')";
		try {
			DBO.insert(sql);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean delete(String rentTime) {
		boolean flag = false;
		String sql = "delete from rentList where rentTime = '"+rentTime+"'";
		try {
			if(isRentList(rentTime)){
				DBO.delete(sql);
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean isRentList(String rentTime) {
		boolean flag = false;
		String sql = "select rentTime from rentList where rentTime='"+rentTime+"'";
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
	public ArrayList<RentList> getAllDateByUsername(String username) {
		ArrayList<RentList> allDate = new ArrayList<RentList>();
		ResultSet rs = null;
		String sql="select * from rentList where username = '"+username+"'";
		String rentTime = "";
		try {
			rs = DBO.select(sql);
			while(rs!=null&&rs.next()){
				rentTime = rs.getString("rentTime");
				allDate.add(getByRentTime(rentTime));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allDate;
	}

	@Override
	public ArrayList<RentList> getAllDate() {
		ArrayList<RentList> allDate = new ArrayList<RentList>();
		ResultSet rs = null;
		String sql="select * from rentList";
		String rentTime = "";
		try {
			rs = DBO.select(sql);
			while(rs!=null&&rs.next()){
				rentTime = rs.getString("rentTime");
				allDate.add(getByRentTime(rentTime));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allDate;
	}

}
