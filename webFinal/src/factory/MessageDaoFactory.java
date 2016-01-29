package factory;

import dao.impl.MessageDaoImpl;


public class MessageDaoFactory {
	public static MessageDaoImpl getInstance(){
		return new MessageDaoImpl();
	}
}
