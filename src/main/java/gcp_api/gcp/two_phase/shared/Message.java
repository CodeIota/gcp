package gcp_api.gcp.two_phase.shared;

public class Message {
    public String type;
	
	public Message(String type){
		this.type = type;
	}
	
	public boolean equals(Object other){
		if(other instanceof Message){
			return (((Message) other).type.equals(type));
		}else{
			return false;
		}
		
	}
}
