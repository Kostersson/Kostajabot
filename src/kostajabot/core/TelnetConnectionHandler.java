package kostajabot.core;

import configurations.ConfigurationPropertiesLoader;
import java.io.IOException;
import org.apache.commons.net.telnet.TelnetClient;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TelnetConnectionHandler implements Runnable {

    private final TelnetClient client = new TelnetClient();
    private final InputStream in;
    private final PrintStream out;
    private String server;
    private Core core;
    private final ConfigurationPropertiesLoader configuration;

    public TelnetConnectionHandler(Core coreObject,ConfigurationPropertiesLoader conf) throws Exception {
        core = coreObject;
        configuration = conf;
        server = null;
        client.connect(configuration.getProperty("quakenetServer"), 
                Integer.parseInt(configuration.getProperty("ircPort")));
        in = client.getInputStream();
        out = new PrintStream(client.getOutputStream());
    }

    private String readLine() throws IOException, Exception {
        StringBuilder sb = new StringBuilder();
        char ch = (char) in.read();
        while (true) {
            sb.append(ch);
            if (ch == '\n') {
                return sb.toString();
            }
            ch = (char) in.read();
        }
    }

    public void write(String value) throws IOException {
        out.println(value);
        out.flush();
        System.out.println(value);
    }

    public void disconnect() throws IOException {
        client.disconnect();
    }

    private void pingPong(String str) throws Exception {
        if (str.startsWith("PING")) {
            write("PONG" + str.substring(4));
        }
    }

    private void handleString(String str) throws Exception {
        System.out.print(str);
        pingPong(str);
        if (str.contains("End of /MOTD command") || str.contains("MOTD File is missing")) {
            server = str.split(" ")[0].substring(1);
            core.autoJoinChannels();
        }
        else{
            core.handleString(str);
        }
    }
    
    public String getServer(){
        return server;
    }

    @Override
    public void run() {
        try {
            handleString(readLine());
        } catch (Exception ex) {
            Logger.getLogger(TelnetConnectionHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
