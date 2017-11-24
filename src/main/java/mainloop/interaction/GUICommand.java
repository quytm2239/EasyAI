package mainloop.interaction;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.DefaultCaret;
import javax.swing.text.Document;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import connectivity.configuration.WSConfiguration;
import connectivity.configuration.WSConnectionStatus;

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
	private JTextField textFieldCommand = new JTextField();
	private JTextArea textAreaCommand = new JTextArea();
	private JTextArea textAreaBotAutoRespond = new JTextArea();
	private JLabel labelConnectionSatus = new JLabel("DISCONNECTED");
	JScrollPane scroll1 = new JScrollPane (textAreaCommand, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	JScrollPane scroll2 = new JScrollPane (textAreaBotAutoRespond, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	
	private int INSET = 10;
	private int TEXTFIELD_HEIGHT = 40;
	private int LABEL_HEIGHT = 20;
	private int LABEL_WIDTH = 150;
	private int TEXTAREA_BOT_AUTORESPOND_WIDTH = 500;
	private GUICommandInterface listener = null;

	public GUICommandInterface getListener() {
		return listener;
	}

	public void setListener(GUICommandInterface listener) {
		this.listener = listener;
	}

	public void setConnectivity(WSConnectionStatus status) {
		switch (status) {
		case CONNECTED:
			labelConnectionSatus.setText("CONNECTED");
			labelConnectionSatus.setForeground(new Color(0, 255, 0));
			break;
		case CONNECTING:
			labelConnectionSatus.setText("CONNECTING");
			labelConnectionSatus.setForeground(new Color(255, 255, 0));
			break;
		case DISCONNECTED:
			labelConnectionSatus.setText("DISCONNECTED");
			labelConnectionSatus.setForeground(new Color(255, 0, 0));
			break;
		default:
			break;
		}
	}

	public void setRespondCommand(String message) {
		this.textAreaCommand.append(message + "\n");
//		JScrollBar vertical = scroll1.getVerticalScrollBar();
//		System.out.println(vertical.getMaximum());
//		vertical.setValue(vertical.getMaximum());
	}
	public void setAutoRespond(String message) {
		this.textAreaBotAutoRespond.append(message + "\n");
//		JScrollBar vertical = scroll2.getVerticalScrollBar();
//		System.out.println(vertical.getMaximum());
//		vertical.setValue(vertical.getMaximum());
//		scroll2.setVerticalScrollBar(vertical);
	}

	private GUICommand() {
		// Creating instance of JFrame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Setting the width and height of frame
		Dimension reqContentSize = new Dimension(800, 400);
		frame.setResizable(false);
		frame.getContentPane().setPreferredSize(reqContentSize);
		frame.setLocationRelativeTo(null);
		
		panel.setLayout(null);
		frame.add(panel);
		
		// Show frame center of screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);

		int COMMAND_PART_WIDTH = reqContentSize.width - TEXTAREA_BOT_AUTORESPOND_WIDTH - 2 * INSET;
		labelConnectionSatus.setBounds((COMMAND_PART_WIDTH - LABEL_WIDTH) / 2, INSET, LABEL_WIDTH, LABEL_HEIGHT);
		int textAreaHeight = reqContentSize.height - 4 * INSET - LABEL_HEIGHT - TEXTFIELD_HEIGHT;
		int textFieldY = reqContentSize.height - (INSET + TEXTFIELD_HEIGHT);
		int width = COMMAND_PART_WIDTH - 2 * INSET;
		textFieldCommand.setBounds(INSET, textFieldY, width, TEXTFIELD_HEIGHT);
		textAreaCommand.setBounds(INSET, 2 * INSET + LABEL_HEIGHT, width, textAreaHeight);
		textAreaBotAutoRespond.setBounds(
				reqContentSize.width - (TEXTAREA_BOT_AUTORESPOND_WIDTH + INSET), 
				INSET, 
				TEXTAREA_BOT_AUTORESPOND_WIDTH, 
				reqContentSize.height - 2 * INSET);

		panel.add(labelConnectionSatus);
		panel.add(textAreaCommand);
		panel.add(textFieldCommand);
		panel.add(textAreaBotAutoRespond);

		Font font1 = new Font(textFieldCommand.getFont().getFontName(), textFieldCommand.getFont().getStyle(), 25);
		textFieldCommand.setFont(font1);
		Font font2 = new Font(textFieldCommand.getFont().getFontName(), textFieldCommand.getFont().getStyle(), 15);
		textAreaCommand.setFont(font2);
		textAreaCommand.setEditable(false);
		textAreaBotAutoRespond.setFont(font2);
		textAreaBotAutoRespond.setEditable(false);
		labelConnectionSatus.setForeground(new Color(255, 0, 0));
		labelConnectionSatus.setHorizontalTextPosition(SwingConstants.CENTER);

		// Add scroll pane for each Textarea
		JScrollPane scroll1 = new JScrollPane (textAreaCommand, 
		   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll1.setBounds(textAreaCommand.getBounds());
		panel.add(scroll1);
		
		JScrollPane scroll2 = new JScrollPane (textAreaBotAutoRespond, 
				   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				scroll2.setBounds(textAreaBotAutoRespond.getBounds());
		panel.add(scroll2);
		
		// Auto scroll to bottom
		 DefaultCaret caretCommand = (DefaultCaret)textAreaCommand.getCaret();
		 caretCommand.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		 
		 DefaultCaret caretAutoRespond = (DefaultCaret)textAreaBotAutoRespond.getCaret();
		 caretAutoRespond.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		textFieldCommand.addKeyListener(new KeyListener() {

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
					if (listener != null)
						listener.onEnterCommand(textFieldCommand.getText());
					// textArea.append(textField.getText() + "\n");
					textFieldCommand.setText(null);
				}
			}
		});

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				frame.pack();
				frame.setVisible(true);
				textFieldCommand.requestFocusInWindow();
			}
		});
	}

}
