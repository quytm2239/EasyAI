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
	
	private JLabel labelCommand = new JLabel("Enter command here!");
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
	
	private void placeComponents(JPanel panel) {

		/* We will discuss about layouts in the later sections
		 * of this tutorial. For now we are setting the layout 
		 * to null
		 */
		panel.setLayout(null);
		
		// Creating JLabel
		JLabel userLabel = new JLabel("User");
		/* This method specifies the location and size
		 * of component. setBounds(x, y, width, height)
		 * here (x,y) are cordinates from the top left 
		 * corner and remaining two arguments are the width
		 * and height of the component.
		 */
		userLabel.setBounds(10,20,80,25);
		panel.add(userLabel);
		
		/* Creating text field where user is supposed to
		 * enter user name.
		 */
		JTextField userText = new JTextField(20);
		userText.setBounds(100,20,165,25);
		panel.add(userText);
		
		// Same process for password label and text field.
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10,50,80,25);
		panel.add(passwordLabel);
		
		/*This is similar to text field but it hides the user
		 * entered data and displays dots instead to protect
		 * the password like we normally see on login screens.
		 */
		JPasswordField passwordText = new JPasswordField(20);
		passwordText.setBounds(100,50,165,25);
		panel.add(passwordText);
		
		// Creating login button
		JButton loginButton = new JButton("login");
		loginButton.setBounds(10, 80, 80, 25);
		panel.add(loginButton);
    }
}
