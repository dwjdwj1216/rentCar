package dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;

import tool.DBO;
import dao.CarInfoDao;
import data.CarInfo;

public class CarInfoDaoImpl implements CarInfoDao {
	
	
	@Override
	public CarInfo getByCarName(String carName) {
		String sql = "select * from carInfo where carName='"+carName+"'";
		ResultSet rs = null;
		try {
			rs = DBO.select(sql);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String imagePath="";
		String carInfo="";
		String carPrice="";
		String rentBy="";
		
		try {
			while(rs!=null&&rs.next()){
				imagePath = rs.getString("imagePath");
				carInfo = rs.getString("carInfo");
				carPrice = rs.getString("carPrice");
				rentBy = rs.getString("rentBy");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		CarInfo carinfo = new CarInfo(imagePath,carName,carInfo,carPrice,rentBy);
		return carinfo;
	}

	@Override
	public CarInfo getByImagePath(String imagePath) {
		String sql = "select * from carInfo where imagePath='"+imagePath+"'";
		ResultSet rs = null;
		try {
			if(isCarInfo(imagePath)){
				rs = DBO.select(sql);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String carName="";
		String carInfo="";
		String carPrice="";
		String rentBy="";
		
		try {
			while(rs!=null&&rs.next()){
				carName = rs.getString("carName");
				carInfo = rs.getString("carInfo");
				carPrice = rs.getString("carPrice");
				rentBy = rs.getString("rentBy");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		CarInfo carinfo = new CarInfo(imagePath,carName,carInfo,carPrice,rentBy);
		return carinfo;
	}

	@Override
	public boolean update(String colName,String imagePath,String changeInfo) {
		boolean flag = false;
		String sql="";
		if(changeInfo==null){
			sql = "update carInfo set "+colName+"=NULL where imagePath='"+imagePath+"'";
		}else {
			sql = "update carInfo set "+colName+"='"+changeInfo+"' where imagePath='"+imagePath+"'";
		}
		try {
			DBO.update(sql);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean insert(String imagePath, String carName, String carInfo,
			String carPrice, String rentBy) {
		boolean flag = true;
		String sql = "insert into carInfo(imagePath,carName,carInfo,carPrice,rentBy) value('"+imagePath+"','"+carName+"','"+carInfo+"','"+carPrice+"','"+rentBy+"')";
		try {
			DBO.insert(sql);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean delete(String imagePath) {
		boolean flag = false;
		String sql = "delete from carInfo where imagePath='"+imagePath+"'";
		try {
			if(isCarInfo(imagePath)){
				DBO.delete(sql);
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean isCarInfo(String imagePath) {
		boolean flag = false;
		String sql = "select imagePath from carInfo where imagePath='"+imagePath+"'";
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
	public ArrayList<CarInfo> getAllDate() {
		ArrayList<CarInfo> allDate = new ArrayList<CarInfo>();
		ResultSet rs = null;
		String sql = "select * from carInfo";
		String imagePath = "";
		try {
			rs = DBO.select(sql);
			while(rs!=null&&rs.next()){
				imagePath = rs.getString("imagePath");
				allDate.add(getByImagePath(imagePath));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return allDate;
	}


}
