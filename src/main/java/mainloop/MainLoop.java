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
import mainloop.interaction.GUICommandInterface;

public class MainLoop implements WSConnectionInterface{
	final static Logger logger = Logger.getLogger(MainLoop.class);
	
	private WSConnection wsConn = WSConnection.getInstance(this);
	private GUICommand guiCommand = GUICommand.getInstance();
	private ArrayList<String> jsonStringRequests = new ArrayList<String>();
	private ArrayList<String> jsonStringResponse = new ArrayList<String>();
	private long ultronId = 0;

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


	public long getUltronId() {
		return ultronId;
	}

	public void setUltronId(long ultronId) {
		this.ultronId = ultronId;
	}

	public static void main(String[] args) {
        System.out.println("Bot starting!!!");
        
        @SuppressWarnings("resource")
		Scanner userInput = new Scanner(System.in);
     
        MainLoop mainLoop = new MainLoop();
        
        mainLoop.guiCommand.setListener(new GUICommandInterface() {
			@Override
			public void onEnterCommand(String command) {
	    		if (command != null) {
	    			if (command.equals("CONNECT")) {
	    				mainLoop.wsConn.connect();
	    			} else if (command.equals("DISCONNECT")) {
	    				mainLoop.wsConn.disconnect();
	    			} else {
	    				mainLoop.processMsgWithSend(command);
	    			}
	    		}
			}
		});
        
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
    			} else if (input.equals("DISCONNECT")) {
    				mainLoop.wsConn.disconnect();
    			} else {
    				mainLoop.processMsgWithSend(input);
    			}
    		}
        }
    }
	
	private void processMsgWithSend(String data) {
		System.out.printf("Got data: %s%n",data);
		if (this.wsConn.getStatus() != WSConnectionStatus.CONNECTED) {
			this.guiCommand.setMessage(data);
			return;
		}
		
        JSONParser parser = new JSONParser();
        JSONObject resObj = new JSONObject();
        JSONObject jsonObject = null;
        try {
        	Object obj = parser.parse(data);
            jsonObject = (JSONObject) obj;

            String message = (String) jsonObject.get("message");
            if (message.equals(WSConfiguration.AI_REQUEST_STATEMENT)) {
            	this.setUltronId((long) jsonObject.get("ultronId"));
            	this.guiCommand.setMessage(data);
            	return;
            }
            long ultronId = (long) jsonObject.get("ultronId");
            resObj.put("message", message);
            resObj.put("ultronId", new Long(ultronId));
			
        } catch (ParseException e) {
            resObj.put("message", data);
            resObj.put("ultronId", new Long(this.getUltronId()));
        }
        if ((long) resObj.get("ultronId") == this.getUltronId()) {
        	this.guiCommand.setMessage(resObj.toJSONString());
        	return;
        }
        System.out.println("AUTO SEND!");
        this.wsConn.sendString(resObj.toJSONString());
	}

	@Override
	public void onIncomingData(String data) {
		// TODO Auto-generated method stub
		this.processMsgWithSend(data);
	}

	@Override
	public void onConnect() {
		// TODO Auto-generated method stub
		this.guiCommand.setConnectivity(WSConnectionStatus.CONNECTED);
	}

	@Override
	public void onDisconnected() {
		// TODO Auto-generated method stub
		this.guiCommand.setConnectivity(WSConnectionStatus.DISCONNECTED);
	}

}
