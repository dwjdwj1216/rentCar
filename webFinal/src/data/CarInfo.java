package data;

public class CarInfo {
	private String imagePath;
	private String carName;
	private String carInfo;
	private String carPrice;
	private String rentBy;
	
	public String getImagePath(){
		return imagePath;
	}
	public void setImagePath(String imagePath){
		this.imagePath = imagePath;
	}
	
	public String getCarName(){
		return carName;
	}
	public void setCarName(String carName){
		this.carName = carName;
	}
	
	public String getCarInfo(){
		return carInfo;
	}
	public void setCarInfo(String carInfo){
		this.carInfo = carInfo;
	}
	
	public String getCarPrice(){
		return carPrice;
	}
	public void setCarPrice(String carPrice){
		this.carPrice = carPrice;
	}
	
	public String getRentBy(){
		return rentBy;
	}
	public void setRentBy(String rentBy){
		this.rentBy = rentBy;
	}
	
	public CarInfo(){
		super();
	}
	public CarInfo(String imagePath,String carName,String carInfo,String carPrice,String rentBy){
		super();
		this.imagePath = imagePath;
		this.carName = carName;
		this.carInfo = carInfo;
		this.carPrice = carPrice;
		this.rentBy = rentBy;
	}
}
