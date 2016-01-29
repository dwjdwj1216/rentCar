package dao;

import java.util.ArrayList;

import data.News;


public interface NewsDao {
	public abstract News getByTitle(String title);
	public abstract ArrayList<News> getAllDate();
	public abstract boolean update(String colName,String title,String changeInfo);
	public abstract boolean insert(String title,String url,String publishTime);
	public abstract boolean delete(String title);
	public abstract boolean isNew(String title);
}
