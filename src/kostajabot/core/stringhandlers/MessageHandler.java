
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
        String message = null;
        Pattern p = Pattern.compile(":(.*)!");
        Matcher matcher = p.matcher(str);
        if (matcher.find()) {
            username = matcher.group(1);
        }
        message = str.replaceAll(":(.*) PRIVMSG ", "");
        System.out.println("<" + username + "> " + message);
    }

}
