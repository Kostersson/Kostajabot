package kostajabot.core.stringhandlers.protocol;

import java.util.HashMap;

/**
 *
 * @author Kostersson
 */
public class NumericProtocolMessageAutoloader {

    private HashMap<Integer, NumericProtocolMessageInterface> numericProtocolMessageHandlers;
    ChannelNames channelNames;

    public NumericProtocolMessageAutoloader() {
        numericProtocolMessageHandlers = new HashMap<Integer, NumericProtocolMessageInterface>();
        addClassesToMap();
    }

    private void addClassesToMap() {
        channelNames = new ChannelNames();
        numericProtocolMessageHandlers.put(channelNames.getResponseNumber(), channelNames);
    }

    public HashMap<Integer, NumericProtocolMessageInterface> getNumericProtocolMessageHandlers() {
        return numericProtocolMessageHandlers;
    }

}
