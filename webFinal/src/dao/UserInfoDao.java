package dao;


import java.util.ArrayList;

import data.UserInfo;
public interface UserInfoDao {
	public abstract UserInfo getByUsername(String username);
	public abstract ArrayList<UserInfo> getAllDate();
	public abstract boolean update(String colName,String username,String changeInfo);
	public abstract boolean insert(String username,String password,String realName,String money);
	public abstract boolean delete(String username);
	public abstract boolean isUser(String username);
}
