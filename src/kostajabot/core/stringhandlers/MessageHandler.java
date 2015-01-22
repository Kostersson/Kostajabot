
package kostajabot.core.stringhandlers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Kostersson
 */
public class MessageHandler {

    public MessageHandler() {
    }
    
    public void handleMessage(String str) {
        String username = null;
        String channel = null;
        String message;
        Pattern p = Pattern.compile(":(.*)!");
        Matcher matcher = p.matcher(str);
        if (matcher.find()) {
            username = matcher.group(1);
        }
        message = str.replaceAll(":(.*) PRIVMSG ", "");
        p = Pattern.compile("(.*):");
        matcher = p.matcher(message);
        if (matcher.find()) {
            channel = matcher.group(1).trim();
        }
        message = message.replaceFirst("(.*):", "");
        System.out.println(channel + " < " + username + " > " + message);
    }

}
