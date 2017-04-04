package com.tcs;

import java.io.IOException;
import java.util.Map;

//import org.nanohttpd.NanoHTTPD;
// NOTE: If you're using NanoHTTPD < 3.0.0 the namespace is different,
//       instead of the above import use the following:
import fi.iki.elonen.NanoHTTPD;

public class App extends NanoHTTPD {

	public App(int portNumber) throws IOException {
		super(portNumber);
		start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
		System.out.println("\nSimple Web Server Running!\n");
	}

	public static void main(String[] args) {
		try {
			new App(Integer.parseInt(args[0]));
		} catch (IOException ioe) {
			System.err.println("Couldn't start server:\n" + ioe);
		}
	}

	@Override
	public Response serve(IHTTPSession session) {
		String msg = "<html><head><title>Simple Web Server</title></head><body><h1 id='h1_id'>Simple Web Server is running.</h1><h3>Welcome!!!</h3></body></html>\n";
		Map<String, String> parms = session.getParms();
		if(parms.get("stop") != null){
			System.out.println("\nClosing Web Server \n");
			System.exit(0);
		}
		return newFixedLengthResponse(msg);
	}
}