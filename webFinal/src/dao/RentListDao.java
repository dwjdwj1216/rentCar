package dao;

import java.util.ArrayList;

import data.RentList;

public interface RentListDao {
	public abstract RentList getByRentTime(String rentTime);
	public abstract ArrayList<RentList> getAllDate();
	public abstract ArrayList<RentList> getAllDateByUsername(String username);
	public abstract boolean update(String colName,String rentTime,String changeInfo);
	public abstract boolean insert(String carName,String rentTime,String price,String username);
	public abstract boolean delete(String rentTime);
	public abstract boolean isRentList(String rentTime);
}
