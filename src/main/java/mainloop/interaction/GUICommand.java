package mainloop.interaction;

import javax.swing.*;  

public class GUICommand {
	
	public static GUICommand instance = null;
	public static GUICommand getInstance() {
		if (instance == null) {
			instance = new GUICommand();
		}
		return instance;
	}
	
	private JTextField textField = new JTextField();
	private JTextArea textArea = new JTextArea();
	
	private GUICommand() {
        // Creating instance of JFrame
        JFrame frame = new JFrame("EASYAI");
        // Setting the width and height of frame
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* Creating panel. This is same as a div tag in HTML
         * We can create several panels and add them to specific 
         * positions in a JFrame. Inside panels we can add text 
         * fields, buttons and other components.
         */
        JPanel panel = new JPanel();  
        panel.setLayout(null);
        // adding panel to frame
        frame.add(panel);
        
        /* calling user defined method for adding components
         * to the panel.
         */
//        placeComponents(panel);
        
       textArea.setBounds(10, 10, 780, 400);
       int textFieldHeight = frame.getBounds().height - 3 * textArea.getBounds().y - textArea.getBounds().height;
       int textFieldY = 2 * textArea.getBounds().y + textArea.getBounds().height;
       textField.setBounds(textArea.getBounds().x, textFieldY, textArea.getBounds().width, textFieldHeight);
       
       panel.add(textArea);
       panel.add(textField);
        // Setting the frame visibility to true
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
//        			frame.pack();
        			frame.setVisible(true);
            }
        });
	}
	
}
