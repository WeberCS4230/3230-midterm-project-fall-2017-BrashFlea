package midterm;

import java.io.*;
import java.net.Socket;

import blackjack.message.ChatMessage;
import blackjack.message.LoginMessage;
import blackjack.message.MessageFactory;

public class Client {
	private int port = 8989;
	private String URL = "ec2-54-91-0-253.compute-1.amazonaws.com";
	private Socket client = null;
    private ObjectOutputStream out = null;
    private ObjectInputStream in = null;
	
	protected void startClient() {
		try {
			client = new Socket(URL, port);
            out = new ObjectOutputStream(client.getOutputStream());
            
            System.out.println("Sending messages");
            System.out.println("opening in.read");
            in = new ObjectInputStream(client.getInputStream());
            System.out.println("opened in.read");
            
    		sendLoginName("Jonathan");
    		sendChatMessage("This is a test", "Jonathan");
            System.out.println("Sent messages");

            in.readObject();

            System.out.println("Started client");
		} catch (IOException e) {
			System.out.println("Unable to connect to server");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	protected void sendLoginName(String username) {
		try {
			LoginMessage Login = MessageFactory.getLoginMessage(username);
			out.writeObject(Login);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void sendChatMessage(String message, String userName) {
		try {
			ChatMessage Message = MessageFactory.getChatMessage(message, userName);
			out.writeObject(Message);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Client test = new Client();
		test.startClient();
		//test.sendLoginName("Jonathan");
		test.sendChatMessage("Chat message 2", "Jonathan");
	}
	
} // end class
