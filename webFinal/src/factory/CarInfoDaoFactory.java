package factory;

import dao.impl.CarInfoDaoImpl;

public class CarInfoDaoFactory {
	public static CarInfoDaoImpl getInstance(){
		return new CarInfoDaoImpl();
	}
}
