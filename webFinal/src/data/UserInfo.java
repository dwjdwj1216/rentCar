package data;

public class UserInfo {
	private String username;
	private String password;
	private String realName;
	private String money;
	public String getUsername(){
		return username;
	}
	public void setUsername(String username){
		this.username = username;
	}
	public String getPassword(){
		return password;
	}
	public void setPassword(String password){
		this.password = password;
	}
	public String getRealName(){
		return realName;
	}
	public void setRealName(String realName){
		this.realName = realName;
	}
	public String getMoney(){
		return money;
	}
	public void setMoney(String money){
		this.money = money;
	}
	
	public UserInfo(){
		super();
	}
	public UserInfo(String username,String password,String realName,String money){
		super();
		this.username = username;
		this.password = password;
		this.realName = realName;
		this.money = money;
	}
}
