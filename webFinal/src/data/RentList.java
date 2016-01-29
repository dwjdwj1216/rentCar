package data;

public class RentList {
	private String carName;
	private String rentTime;
	private String price;
	private String username;
	
	public String getCarName(){
		return carName;
	}
	public void setCarName(String carName){
		this.carName = carName;
	}
	
	public String getRentTime(){
		return rentTime;
	}
	public void setRentTime(String rentTime){
		this.rentTime = rentTime;
	}
	
	public String getPrice(){
		return price;
	}
	public void setPrice(String price){
		this.price = price;
	}
	
	public String getUsername(){
		return username;
	}
	public void setUsername(String username){
		this.username = username;
	}
	
	public RentList(){
		super();
	}
	public RentList(String carName,String rentTime,String price,String username){
		super();
		this.carName = carName;
		this.rentTime = rentTime;
		this.price = price;
		this.username = username;
	}
}
