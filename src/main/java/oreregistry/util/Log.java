/*
 * Copyright (c) 2017 Nedelosk, Mezz
 *
 * This work (the MOD) is licensed under the "MIT" License, see LICENSE for details.
 */
package oreregistry.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import oreregistry.config.Constants;

public class Log {

	private static void log(Level logLevel, String message, Object... params) {
		LogManager.getLogger(Constants.MOD_ID).log(logLevel, message, params);
	}

	public static void trace(String message, Object... params) {
		log(Level.TRACE, message, params);
	}

	public static void debug(String message, Object... params) {
		log(Level.DEBUG, message, params);
	}

	public static void info(String message, Object... params) {
		log(Level.INFO, message, params);
	}

	public static void warning(String message, Object... params) {
		log(Level.WARN, message, params);
	}
	
	public static void error(String message, Object... params) {
		log(Level.ERROR, message, params);
	}

	public static void error(String message, Throwable t) {
		LogManager.getLogger(Constants.MOD_ID).log(Level.ERROR, message, t);
	}
}
