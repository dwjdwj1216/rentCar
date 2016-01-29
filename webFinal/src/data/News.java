package data;


public class News {
	private String title;
	private String url;
	private String publishTime;
	
	public String getTitle(){
		return title;
	}
	public void setTitle(String title){
		this.title = title;
	}
	
	public String getUrl(){
		return url;
	}
	public void setUrl(String url){
		this.url = url;
	}
	
	public String getPublishTime(){
		return publishTime;
	}
	public void setPublishTime(String publishTime){
		this.publishTime = publishTime;
	}
	
	public News(){
		super();
	}
	public News(String title,String url,String publishTime){
		super();
		this.title = title;
		this.url = url;
		this.publishTime = publishTime;
	}
}
