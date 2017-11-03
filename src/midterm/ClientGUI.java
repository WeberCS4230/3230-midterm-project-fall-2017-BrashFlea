package midterm;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import midterm.Client;

public class ClientGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;
    private JTextArea chatArea = null;
    private JTextArea chatInput = null;
    private JButton inputSend = null;
    private GridBagConstraints gbc1 = new GridBagConstraints();
    private Font hv = new Font("Helvetica", Font.PLAIN, 14);
    private Font co = new Font("Courier", Font.ITALIC, 12);
    
    protected boolean clientConnected = false;
    Client client = null;
    
    
    public ClientGUI() {
    	client = new Client("Jonathan");
        initUI();
        showTextArea();
        addScrollBar();
        showChatInput();
        showInputButton();
    }

    private void initUI() {
        setTitle("CS3230 Midterm - Jonathan Mirabile");
        setLayout(new GridBagLayout());
        setSize(400,505);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
       
    }
    
   
    private void showTextArea() {
        chatArea = new JTextArea(null, 10, 25);
        chatArea.setFont(hv);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        chatArea.setEditable(false);
        
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        gbc1.gridwidth = 6;
        gbc1.gridheight = 7;
        gbc1.weightx = 1;
        gbc1.weighty = 1;
        gbc1.anchor = GridBagConstraints.CENTER;
        gbc1.fill = GridBagConstraints.HORIZONTAL;
        
        add(chatArea, gbc1);
                
    }
    
    private void addScrollBar() {
        JScrollPane scroll = new JScrollPane(chatArea);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        gbc1.gridwidth = 1;
        gbc1.gridheight = 1;
        gbc1.weightx = 1;
        gbc1.weighty = 1;
        gbc1.anchor = GridBagConstraints.CENTER;
        gbc1.fill = GridBagConstraints.HORIZONTAL;
        
        add(scroll, gbc1);
        
    }
    
    private void showChatInput() {
        chatInput = new JTextArea("Enter message here", 15, 10);
        chatInput.setFont(co);
        chatInput.setLineWrap(true);
        chatInput.setWrapStyleWord(true);
        chatInput.setEditable(true);
        
        gbc1.gridx = 0;
        gbc1.gridy = 1;
        gbc1.gridwidth = 6;
        gbc1.gridheight = 1;
        gbc1.anchor = GridBagConstraints.CENTER;
        gbc1.fill = GridBagConstraints.HORIZONTAL;
        
        add(chatInput, gbc1);
        
        chatInput.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if(chatInput.getText().trim().equals("Enter message here")) {
                    chatInput.setText("");
                }
            }
        });
        
        chatInput.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if((e.getKeyCode() == KeyEvent.VK_ENTER && e.getModifiers() == KeyEvent.CTRL_MASK)) {
                	updateChatBox();
                }
            }
        });
                      
    }
    
    private void showInputButton() {
        inputSend = new JButton("Send");
        
        gbc1.gridx = 0;
        gbc1.gridy = 2;
        gbc1.gridwidth = 6;
        gbc1.gridheight = 3;
        gbc1.anchor = GridBagConstraints.CENTER;
        gbc1.fill = GridBagConstraints.HORIZONTAL;
        add(inputSend, gbc1);
        
        inputSend.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            	updateChatBox();
            }
        });
             
    }
    
	private void displayChatText(Object chatMessage) {
        if(client.getLoginName() != "") {
            chatArea.append(client.getLoginName() + ": " + chatMessage.toString() + '\n');
        }
        else {
            chatArea.append(chatMessage.toString() + '\n');
        }

	}
    
    
    private void updateChatBox() {
        //Send the message to the chat server
        client.sendChatMessage(chatInput.getText(), client.getLoginName());
        //Clear the input box
        chatInput.setText("");
        //Read the message from the server and display it
        displayChatText(client.readMessageFromServer());
        //Scroll to the bottom of the chat box
        chatArea.setCaretPosition(chatArea.getDocument().getLength());      
        
    }



    

}
