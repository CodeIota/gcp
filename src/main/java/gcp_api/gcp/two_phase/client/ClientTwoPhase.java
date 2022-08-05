package gcp_api.gcp.two_phase.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;

import gcp_api.gcp.two_phase.shared.Message;

public class ClientTwoPhase {
    
	private Socket socket;
	
	ClientTwoPhase(Socket socket){
		this.socket = socket;
	}
	public void startParticipating(){
		try {
			Thread.sleep(RunClient.SLEEP_TIME);
			
			socket.setSoTimeout(RunClient.TIMEOUT);
			Message o = receive();
			
			if(o.type.equals("VOTE_REQUEST")){
				if(RunClient.VOTE_ABORT_FLAG){ //vote abort
					sendMessage(new Message("VOTE_ABORT"));
                    System.out.println("ABORTED");
					return;
				}else{//vote commit
					sendMessage(new Message("VOTE_COMMIT"));
					
				}
			}
			try{
				o = receive();
				
				if(o.type.equals("GLOBAL_COMMIT")){
					System.out.println("COMMITED");
				}else if(o.type.equals("GLOBAL_ABORT")){
					// log.write(Log.GLOBAL_ABORT);
                    System.out.println("ABORTED");

					
				}else{
					// log.write(Log.VOTE_ABORT);
					sendMessage(new Message("VOTE_ABORT"));
                    System.out.println("ABORTED");

					return;
				}
				
			}catch(SocketTimeoutException e){
				return;
			}
			
		}catch(SocketTimeoutException e){
            System.out.println("ABORTED");
			return;
		}catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
	
	public void sendMessage(Message message){
		try {
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(message);
			System.out.println("Sent " + message.type);
		} catch (IOException e) {
			finish();
			e.printStackTrace();
		}
	}
	public void finish(){
		System.out.println("closing " + toString() + ".");

		try {
			socket.close();
		} catch (IOException e) {}
	}
	
	private Message receive() throws SocketTimeoutException{
		ObjectInputStream ois;
		Message o = null;
		try {
			ois = new ObjectInputStream(socket.getInputStream());
			o = (Message) ois.readObject();
		}catch(SocketTimeoutException e){
			throw e;
		}catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.println("Received " + o.type);
		return o;
	}
    
}
