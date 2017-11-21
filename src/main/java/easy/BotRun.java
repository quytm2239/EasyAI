package easy;

import connectivity.WSConnection;

public class BotRun {
    public static void main(String[] args) {
        System.out.println("Bot starting!!!");
        WSConnection wsConn = WSConnection.getInstance();
        wsConn.connect();
//        wsConn.sendString("@@@@@@@");
    }
}
