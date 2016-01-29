package dao;

import java.util.ArrayList;


import data.CarInfo;

public interface CarInfoDao {
	public abstract CarInfo getByImagePath(String imagePath);
	public abstract CarInfo getByCarName(String carName);
	public abstract ArrayList<CarInfo> getAllDate();
	public abstract boolean update(String colName,String imagePath,String changeInfo);
	public abstract boolean insert(String imagePath,String carName,String carInfo,String carPrice,String rentBy);
	public abstract boolean delete(String imagePath);
	public abstract boolean isCarInfo(String imagePath);
}
