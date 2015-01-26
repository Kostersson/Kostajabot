package kostajabot.core.stringhandlers.protocol;

import java.util.ArrayList;
import java.util.HashMap;
import kostajabot.core.Core;

/**
 *
 * @author Kostersson
 */
public class NumericProtocolMessageAutoloader {

    private final HashMap<Integer, NumericProtocolMessageInterface> numericProtocolMessageHandlers;
    ChannelNames channelNames;
    Core core;

    public NumericProtocolMessageAutoloader(Core core) {
        this.core = core;
        this.numericProtocolMessageHandlers = new HashMap<>();
        addClassesToMap(createClassesArray());
    }
    
    private ArrayList createClassesArray(){
        ArrayList<NumericProtocolMessageInterface> classes = new ArrayList<>();
        classes.add(new ChannelNames(core));
        return classes;
    }
    
    private void addClassesToMap(ArrayList<NumericProtocolMessageInterface> arr) {
        for( NumericProtocolMessageInterface val : arr){
            numericProtocolMessageHandlers.put(val.getResponseNumber(), val);
        }
    }

    public HashMap<Integer, NumericProtocolMessageInterface> getNumericProtocolMessageHandlers() {
        return numericProtocolMessageHandlers;
    }

}
