package dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;

import tool.DBO;
import dao.MessageDao;
import data.Message;

public class MessageDaoImpl implements MessageDao {

	@Override
	public Message getByCommenter_Time(String commenter, String time) {
		String sql = "select * from message where commenter = '"+commenter+"' and time ='"+time+"'";
		ResultSet rs = null;
		try {
			if(isMessage(commenter, time)){
				rs = DBO.select(sql);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String messageContent="";
		try {
			while(rs!=null && rs.next()){
				messageContent = rs.getString("messageContent");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Message message = new Message(commenter, messageContent, time);
		
		return message;
	}

	@Override
	public boolean update(String colName,String commenter,String time,String changeInfo) {
		boolean flag = false;
		String sql = "update message set "+colName+"='"+changeInfo+"' where commenter='"+commenter+"' and time ='"+time+"'";
		try {
			DBO.update(sql);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean insert(String commenter, String messageContent, String time) {
		
		boolean flag = true;
		String sql="insert into message(commenter,time,messageContent) value('"+commenter+"','"+time+"','"+messageContent+"')";
		try {
			DBO.insert(sql);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean delete(String commenter, String time) {
		boolean flag = false;
		String sql = "delete from message where commenter = '"+commenter+"' and time ='"+time+"'";
		try {
			if(isMessage(commenter, time)){
				DBO.delete(sql);
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean isMessage(String commenter, String time) {
		boolean flag = false;
		String sql = "select commenter,time from message where commenter = '"+commenter+"' and time ='"+time+"'";
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
	public ArrayList<Message> getAllDate() {
		ArrayList<Message> allDate = new ArrayList<Message>();
		ResultSet rs = null;
		String sql = "select * from message";
		String commenter= "";
		String time="";
		try {
			rs = DBO.select(sql);
			while(rs!=null&&rs.next()){
				commenter = rs.getString("commenter");
				time = rs.getString("time");
				allDate.add(getByCommenter_Time(commenter, time));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return allDate;
	}

}
