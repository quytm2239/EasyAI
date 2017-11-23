package mainloop.interaction;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;  

public class GUICommand {
	
	public static GUICommand instance = null;
	public static GUICommand getInstance() {
		if (instance == null) {
			instance = new GUICommand();
		}
		return instance;
	}
	private JFrame frame = new JFrame("EASYAI");
	private JPanel panel = new JPanel();
	private JTextField textField = new JTextField();
	private JTextArea textArea = new JTextArea();
	private int INSET = 10;
	private int TEXTFIELD_HEIGHT = 40;
	
	private GUICommand() {
		// Creating instance of JFrame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Setting the width and height of frame
		Dimension reqContentSize = new Dimension(600, 400);
		frame.setResizable(false);
		frame.getContentPane().setPreferredSize(reqContentSize);
		frame.setLocationRelativeTo(null);
		
		panel.setLayout(null);
		frame.add(panel);
	    
		int textAreaHeight = reqContentSize.height - 3 * INSET - TEXTFIELD_HEIGHT;
		int textFieldY = reqContentSize.height - (INSET + TEXTFIELD_HEIGHT);
		int width = reqContentSize.width - 2 * INSET;
		textField.setBounds(INSET, textFieldY, width, TEXTFIELD_HEIGHT); 
		textArea.setBounds(INSET, INSET, width , textAreaHeight);
	   
		panel.add(textArea);
		panel.add(textField);
		
		Font font = new Font(textField.getFont().getFontName(), textField.getFont().getStyle(), 25);
		textField.setFont(font);
		textArea.setFont(font);
		textArea.setEditable(false);
		
		KeyListener keyListener = new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == 10) {
					textArea.append(textField.getText() + "\n");
					textField.setText(null);
				}
			}
		};
		textField.addKeyListener(keyListener);
	   
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		    	frame.pack();
				frame.setVisible(true);
				textField.requestFocusInWindow();
		    }
		});
	}
	
}
