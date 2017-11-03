package midterm;

import java.io.*;
import java.net.Socket;

import blackjack.message.ChatMessage;
import blackjack.message.LoginMessage;
import blackjack.message.MessageFactory;

public class Client {
	
	private int port = 8989;
	private String URL = "ec2-54-172-123-164.compute-1.amazonaws.com";
	private Socket client = null;
    private ObjectOutputStream out = null;
    private ObjectInputStream in = null;

    public Client(String loginName) {
        startClient(loginName); 
    }
    
    protected void startClient(String loginName) {
        try {
        	client = new Socket(URL, port);
        	out = new ObjectOutputStream(client.getOutputStream());
        	in = new ObjectInputStream(client.getInputStream());
        	
        	System.out.println("Sending login message");
			LoginMessage Login = MessageFactory.getLoginMessage(loginName);
			out.writeObject(Login);
			out.flush();
			System.out.println("Sent login messages");
                                 
		} catch (IOException e) {
			System.out.println("Client encountered an error during initialization");
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
    
    protected void readMessageFromServer() {

    }

	public static void main(String[] args) {
		Client test = new Client("Jonathan");
		test.sendChatMessage("Chat message 1", "Jonathan");
	} // end main
	
} // end class
