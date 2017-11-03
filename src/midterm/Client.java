package midterm;

import java.awt.EventQueue;
import java.io.*;
import java.net.Socket;

import blackjack.message.ChatMessage;
import blackjack.message.LoginMessage;
import blackjack.message.MessageFactory;

public class Client {
	
	private int port = 8989;
	private String URL = "ec2-54-172-123-164.compute-1.amazonaws.com";
	private String loginName = "";
	private Socket client = null;
    private ObjectOutputStream out = null;
    private ObjectInputStream in = null;

    public Client(String loginName) {
        startClient(loginName);
        this.setLoginName(loginName);
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
			System.out.println("Sending chat message: " + message);
			ChatMessage Message = MessageFactory.getChatMessage(message, userName);
			out.writeObject(Message);
			out.flush();
			System.out.println("Sent chat message: " + message);
		} catch (IOException e) {
			System.out.println("Client encountered an error while sending a chat message");
		}
	}
    
    public String readMessageFromServer() {
        String message = "";

        try {
            while(in.readObject() != null) {
            	message += in.toString();
            }
        } catch (IOException e) {
        	System.out.println("Client encountered an error while reading from the server");
        } catch (ClassNotFoundException e) {
        	System.out.println("Client encountered an error while reading from the server");
		} 
        System.out.println("Message Recieved from Server: "  + "\n" + ": " + message);
        return message;
    }
    
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public static void main(String[] args) {
		//Client test = new Client("Jonathan");
		//test.sendChatMessage("Chat message 1", "Jonathan");
		
    	ClientGUI testGUI = new ClientGUI();
    	testGUI.setVisible(true);

	} // end main


	
} // end class
