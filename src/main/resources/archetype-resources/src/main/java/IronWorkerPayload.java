#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class IronWorkerPayload {

	private JsonObject payloadArguments = null;
	private Gson gson = new Gson();
	
	public IronWorkerPayload(String[] args) throws IOException {
		
		/*
         * obtain the payload filename (containing payload in json format) from the passed arguments
         */
        int payloadPos = -1;
        for(int i=0; i < args.length; i++) {
            if(args[i].equals("-payload")) {
                payloadPos = i + 1;
                break;
            }
        }
        
		if (payloadPos >= args.length) {
			throw new IllegalArgumentException("Invalid payload argument.");
        } else if(payloadPos == -1) {
        	throw new IllegalArgumentException("No payload argument.");
        } else {
        	//The payload string looks like this: { "arg1": "Test", "another_arg": ["apples", "oranges"]}
        	String payload = readFile(args[payloadPos]);
        	JsonParser parser = new JsonParser();
            this.payloadArguments = parser.parse(payload).getAsJsonObject();
        }
		
	}
	
	private static String readFile(String path) throws IOException {
        FileInputStream stream = new FileInputStream(new File(path));
        try {
            FileChannel chan = stream.getChannel();
            MappedByteBuffer buf = chan.map(FileChannel.MapMode.READ_ONLY, 0, chan.size());
            return Charset.defaultCharset().decode(buf).toString();
        }
        finally {
            stream.close();
        }
    }
	
	public String getString(String property) {
		return (payloadArguments != null) ? this.gson.fromJson(payloadArguments.get(property), String.class) : null;
	}
	
	public <T> T get(String property, Class<T> classOfT) {
		return (payloadArguments != null) ? this.gson.fromJson(payloadArguments.get(property), classOfT) : null;
	}
}
