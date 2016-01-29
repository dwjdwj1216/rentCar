package dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;

import tool.DBO;
import dao.NewsDao;
import data.Message;
import data.News;

public class NewsDaoImpl implements NewsDao {

	@Override
	public News getByTitle(String title) {
		String sql = "select * from news where Title='"+title+"'";
		ResultSet rs = null;
		try {
			if(isNew(title)){
				rs = DBO.select(sql);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String url = "";
		String publishTime = "";
		try {
			while(rs!=null && rs.next()){
				url = rs.getString("Url");
				publishTime  = rs.getString("publishTime");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		News news = new News(title, url, publishTime);
		return news;
	}

	@Override
	public boolean update(String colName,String title,String changeInfo) {
		boolean flag = false;
		String sql = "update news set "+colName+"='"+changeInfo+"' where Title='"+title+"'";
		try {
			DBO.update(sql);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean insert(String title, String url, String publishTime) {
		boolean flag = true;
		String sql="insert into news(Title,Url,publishTime) value('"+title+"','"+url+"','"+publishTime+"')";
		try {
			DBO.insert(sql);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean delete(String title) {
		boolean flag = false;
		String sql = "delete from news where Title='"+title+"'";
		try {
			if(isNew(title)){
				DBO.delete(sql);
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean isNew(String title) {
		boolean flag = false;
		String sql = "select Title from news where Title='"+title+"'";
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
	public ArrayList<News> getAllDate() {
		ArrayList<News> allDate = new ArrayList<News>();
		ResultSet rs = null;
		String sql = "select * from news";
		String title= "";
		try {
			rs = DBO.select(sql);
			while(rs!=null&&rs.next()){
				title = rs.getString("Title");
				allDate.add(getByTitle(title));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return allDate;
	}

}
