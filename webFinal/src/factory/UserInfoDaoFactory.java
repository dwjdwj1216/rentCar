package factory;


import dao.impl.UserInfoDaoImpl;
public class UserInfoDaoFactory{
	public static UserInfoDaoImpl getInstance(){
		return new UserInfoDaoImpl();
	}
}
