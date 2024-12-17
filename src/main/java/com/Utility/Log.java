package com.Utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {
	
	//Initialize Log4j instance
    private static final Logger Log =  LogManager.getLogger(Log.class);
    //Info Level Logs
    public static void info (String length) {
        Log.info(length);
    }
    //Warn Level Logs
    public static void warn (String message) {
        Log.warn(message);
    }
 // Error Level Logs - Single argument
    public static void error(String message) {
        Log.error(message); // Calls Logger's single-argument error method
    }
 // Error Level Logs - With Throwable
    public static void error (String message, Throwable throwable) {
    	Log.error(message, throwable);
    }
    //Fatal Level Logs
    public static void fatal (String message) {
        Log.fatal(message);
    }
    //Debug Level Logs
    public static void debug (String message) {
        Log.debug(message);
    }


}
