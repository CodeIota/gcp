package gcp_api.gcp.two_phase.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


public class RunClient {
    public static String COORDINATOR_IP = "localhost";
	public static int COORDINATOR_PORT = 5476;
	public static boolean VOTE_ABORT_FLAG = false;
	public static int TIMEOUT = 5000;
	public static int SLEEP_TIME = 0;
	
	public static void main(String[] args) {
		try {
			Socket socket = new Socket(COORDINATOR_IP, COORDINATOR_PORT);

			ClientTwoPhase client = new ClientTwoPhase(socket);
			client.startParticipating();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
