/*
 * MIT License
 *
 * Copyright (c) 2017 Nedelosk, Mezz
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
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
