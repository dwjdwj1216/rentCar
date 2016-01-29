package factory;

import dao.impl.RentListDaoImpl;


public class RentListDaoFactory {
	public static RentListDaoImpl getInstance(){
		return new RentListDaoImpl();
	}
}
