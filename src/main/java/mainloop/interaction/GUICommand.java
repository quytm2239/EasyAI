package mainloop.interaction;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

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
	private JTextField textField = new JTextField();
	private JTextArea textArea = new JTextArea();
	private JLabel labelConnectionSatus = new JLabel("DISCONNECTED");
	private int INSET = 10;
	private int TEXTFIELD_HEIGHT = 40;
	private int LABEL_HEIGHT = 20;
	private int LABEL_WIDTH = 100;
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

	public void setMessage(String message) {
		this.textArea.append(message + "\n");
	}

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

		labelConnectionSatus.setBounds((reqContentSize.width - LABEL_WIDTH) / 2, INSET, LABEL_WIDTH, LABEL_HEIGHT);
		int textAreaHeight = reqContentSize.height - 4 * INSET - LABEL_HEIGHT - TEXTFIELD_HEIGHT;
		int textFieldY = reqContentSize.height - (INSET + TEXTFIELD_HEIGHT);
		int width = reqContentSize.width - 2 * INSET;
		textField.setBounds(INSET, textFieldY, width, TEXTFIELD_HEIGHT);
		textArea.setBounds(INSET, 2 * INSET + LABEL_HEIGHT, width, textAreaHeight);

		panel.add(labelConnectionSatus);
		panel.add(textArea);
		panel.add(textField);

		Font font1 = new Font(textField.getFont().getFontName(), textField.getFont().getStyle(), 25);
		textField.setFont(font1);
		Font font2 = new Font(textField.getFont().getFontName(), textField.getFont().getStyle(), 15);
		textArea.setFont(font2);
		textArea.setEditable(false);
		labelConnectionSatus.setForeground(new Color(255, 0, 0));

		textField.addKeyListener(new KeyListener() {

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
						listener.onEnterCommand(textField.getText());
					// textArea.append(textField.getText() + "\n");
					textField.setText(null);
				}
			}
		});

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				frame.pack();
				frame.setVisible(true);
				textField.requestFocusInWindow();
			}
		});
	}

}
