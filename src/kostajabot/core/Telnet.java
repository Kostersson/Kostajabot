package kostajabot.core;

import org.apache.commons.net.telnet.TelnetClient;

import java.io.InputStream;
import java.io.PrintStream;

public class Telnet implements Runnable {

    private TelnetClient client = new TelnetClient();
    private InputStream in;
    private PrintStream out;
    private ChannelHandler channelHandler;
    private String channel;

    public Telnet() {
        channelHandler = new ChannelHandler(this);
        channel = "#kostajabot";
        try {
            // Connect to the specified server
            client.connect("irc.fi.quakenet.org", 6667);

            // Get input and output stream references
            in = client.getInputStream();
            out = new PrintStream(client.getOutputStream());
            setup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setup() {
        // Log the user on
        handleString(readUntil("\n"));
        write("NICK Kostajabot");
        handleString(readUntil("\n"));
        write("USER  Kostajabot 8 *  : Kostajabot");
    }

    private String readUntil(String pattern) {
        try {
            char lastChar = pattern.charAt(pattern.length() - 1);
            StringBuffer sb = new StringBuffer();
            boolean found = false;
            char ch = (char) in.read();
            while (true) {
                sb.append(ch);
                if (ch == lastChar) {
                    if (sb.toString().endsWith(pattern)) {
                        return sb.toString();
                    }
                }
                ch = (char) in.read();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void write(String value) {
        try {
            out.println(value);
            out.flush();
            System.out.println(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            client.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void pingPong(String str){
        if(str.startsWith("PING")){
            write("PONG" + str.substring(4));
        }
    }

    private void handleString(String str) {
        System.out.print(str);
        pingPong(str);
        if(str.contains("End of /MOTD command")){
            channelHandler.joiner();
        }
    }

    @Override
    public void run() {
        // Log the user on
        handleString(readUntil("\n"));
    }

}
