package data;

public class Message {
	private String commenter;
	private String messageContent;
	private String time;
	
	public String getCommenter(){
		return commenter;
	}
	public void setCommenter(String commenter){
		this.commenter = commenter;
	}
	
	public String getMessageContent(){
		return messageContent;
	}
	public void setMessageContent(String messageContent){
		this.messageContent = messageContent;
	}
	
	public String getTime(){
		return time;
	}
	public void setTime(String time){
		this.time=time;
	}
	public Message(){
		super();
	}
	public Message(String commenter,String messageContent,String time){
		super();
		this.commenter = commenter;
		this.messageContent = messageContent;
		this.time = time;
	}
}
