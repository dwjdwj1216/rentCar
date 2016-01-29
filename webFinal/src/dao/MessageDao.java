package dao;

import java.util.ArrayList;

import data.Message;

public interface MessageDao {
	public abstract Message getByCommenter_Time(String commenter,String time);
	public abstract ArrayList<Message> getAllDate();
	public abstract boolean update(String colName,String commenter,String time,String changeInfo);

	public abstract boolean insert(String commenter,String messageContent,String time);
	public abstract boolean delete(String commenter,String time);
	public abstract boolean isMessage(String commenter,String time);
}
