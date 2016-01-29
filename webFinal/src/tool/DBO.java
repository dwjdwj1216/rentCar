package tool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class DBO {
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
		Connection con = null;
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/final?useUnicode=true&characterEncoding=UTF-8";
		String user="root";
		String password="123";
		con = DriverManager.getConnection(url,user,password);
		return con;
	}
	
	public static ResultSet select(String sql) throws Exception{
		Connection con = null;
		Statement sta = null;
		ResultSet rs = null;
		
		try {
			con = getConnection();
			sta = con.createStatement();
			rs = sta.executeQuery(sql);
			return rs;
		} catch (SQLException sqle) {
			throw new SQLException("select data exception:"+sqle.getMessage());
		}catch (Exception e) {
			throw new Exception("System e exception:"+e.getMessage());
		}
	}
	
	public static void insert(String sql)throws Exception{
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
		}catch(SQLException sqle){
			throw new Exception("insert data exception : "+sqle.getMessage());
		}finally{
			try {
				if(ps!=null){
					ps.close();
				}
			} catch (Exception e) {
				throw new Exception("ps close exception:" +e.getMessage());
			}
		}
		try {
			if(con!=null){
				con.close();
			}
		} catch (Exception e) {
			throw new Exception("connection close exception: "+e.getMessage());
		}
	}
	
	public static void update(String sql)throws Exception{
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException sqle) {
			throw new Exception("update exception: "+sqle.getMessage());
		}finally{
			try {
				if(ps!=null){
					ps.close();
				}
			} catch (Exception e) {
				throw new Exception("ps close exception:" +e.getMessage());
			}
		}
		try {
			if(con!=null){
				con.close();
			}
		} catch (Exception e) {
			throw new Exception("connection close exception:"+e.getMessage());
		}
	}
	
	public static void delete(String sql)throws Exception{
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException sqle) {
			throw new Exception("delete data exception: "+sqle.getMessage());
		}finally{
			try {
				if(ps!=null){
					ps.close();
				}
			} catch (Exception e) {
				throw new Exception("ps close exception: "+e.getMessage());
			}
		}
		try {
			if(con!=null){
				con.close();
			}
		} catch (Exception e) {
			throw new Exception("connection close exception: "+e.getMessage());
		}
	}
}
