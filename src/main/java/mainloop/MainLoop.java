package mainloop;

import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import connectivity.configuration.*;
import connectivity.inteface.WSConnectionInterface;
import connectivity.websocket.WSConnection;
import mainloop.interaction.GUICommand;

public class MainLoop implements WSConnectionInterface{
	final static Logger logger = Logger.getLogger(MainLoop.class);
	
	private WSConnection wsConn = WSConnection.getInstance(this);
	
	private ArrayList<String> jsonStringRequests = new ArrayList<String>();
	private ArrayList<String> jsonStringResponse = new ArrayList<String>();
	
    /**
	 * @return the jsonStringRequests
	 */
	public ArrayList<String> getJsonStringRequests() {
		return jsonStringRequests;
	}

	/**
	 * @param jsonStringRequests the jsonStringRequests to set
	 */
	public void setJsonStringRequests(ArrayList<String> jsonStringRequests) {
		this.jsonStringRequests = jsonStringRequests;
	}

	/**
	 * @return the jsonStringResponse
	 */
	public ArrayList<String> getJsonStringResponse() {
		return jsonStringResponse;
	}

	/**
	 * @param jsonStringResponse the jsonStringResponse to set
	 */
	public void setJsonStringResponse(ArrayList<String> jsonStringResponse) {
		this.jsonStringResponse = jsonStringResponse;
	}


	public static void main(String[] args) {
        System.out.println("Bot starting!!!");
        
        @SuppressWarnings("resource")
		Scanner userInput = new Scanner(System.in);
     
        MainLoop mainLoop = new MainLoop();
        
        GUICommand guiCommand = GUICommand.getInstance();
        
        // Start mainloop to show that EASYAI alive!
        while (true ) {
        		String input = null;
        		try {
        			input = userInput.nextLine();
			} catch (Exception e) {
				e.printStackTrace();
			}
        		if (input != null) {
        			if (input.equals("CONNECT")) {
        				mainLoop.wsConn.connect();
        			}
        			if (input.equals("DISCONNECT")) {
        				mainLoop.wsConn.disconnect();
        			}
        		}
        }
    }
	
	private void processMsgWithSend(String data) {
		System.out.printf("Got data: %s%n",data);
		
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(data);
            JSONObject jsonObject = (JSONObject) obj;

            String message = (String) jsonObject.get("message");
            if (message.equals(WSConfiguration.AI_REQUEST_STATEMENT)) return;
            
            long ultronId = (long) jsonObject.get("ultronId");
            
            JSONObject resObj = new JSONObject();
            resObj.put("message", message);
            resObj.put("ultronId", new Long(ultronId));
    			this.wsConn.sendString(resObj.toJSONString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
	}

	@Override
	public void onIncomingData(String data) {
		this.processMsgWithSend(data);
	}
}
