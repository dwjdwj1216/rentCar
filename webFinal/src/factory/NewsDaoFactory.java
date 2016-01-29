package factory;

import dao.impl.NewsDaoImpl;

public class NewsDaoFactory {
	public static NewsDaoImpl getInstance(){
		return new NewsDaoImpl();
	}
}
