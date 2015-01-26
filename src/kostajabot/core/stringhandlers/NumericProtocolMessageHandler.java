
package kostajabot.core.stringhandlers;

import configurations.exceptions.NoPropertyFoundException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kostajabot.core.Core;
import kostajabot.core.stringhandlers.protocol.NumericProtocolMessageAutoloader;
import kostajabot.core.stringhandlers.protocol.NumericProtocolMessageInterface;

/**
 *
 * @author Kostersson
 */
public class NumericProtocolMessageHandler {
    private Core core;
    Map<Integer, NumericProtocolMessageInterface> numericProtocolMessageHandlers;

    public NumericProtocolMessageHandler(Core core) {
        this.core = core;
        NumericProtocolMessageAutoloader numericProtocolMessageAutoloader = new NumericProtocolMessageAutoloader(core);
        numericProtocolMessageHandlers = numericProtocolMessageAutoloader.getNumericProtocolMessageHandlers();
    }
    
    public void handleMessage(String str) throws Exception{
        Pattern p = Pattern.compile("(\\d*) " + core.getName());
        Matcher matcher = p.matcher(str);
        if (matcher.find()) {
             int number = Integer.parseInt(matcher.group(1));
             NumericProtocolMessageInterface numericProtocolMessageHandler = numericProtocolMessageHandlers.get(number);
             if(numericProtocolMessageHandler != null){
                 numericProtocolMessageHandler.handleMessage(str);
             }
        }
    }
}
