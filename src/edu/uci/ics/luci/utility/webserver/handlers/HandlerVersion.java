/*
	Copyright 2007-2013
		University of California, Irvine (c/o Donald J. Patterson)
*/
/*
	This file is part of the Laboratory for Ubiquitous Computing java Utility package, i.e. "Utilities"

    Utilities is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Utilities is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Utilities.  If not, see <http://www.gnu.org/licenses/>.
*/

package edu.uci.ics.luci.utility.webserver.handlers;

import java.net.InetAddress;
import java.util.Map;

import net.minidev.json.JSONObject;

import org.apache.log4j.Logger;


import edu.uci.ics.luci.utility.datastructure.Pair;
import edu.uci.ics.luci.utility.webserver.HandlerAbstract;
import edu.uci.ics.luci.utility.webserver.RequestDispatcher.HTTPRequest;

public class HandlerVersion extends HandlerAbstract {
	
	private static transient volatile Logger log = null;
	public static Logger getLog(){
		if(log == null){
			log = Logger.getLogger(HandlerVersion.class);
		}
		return log;
	}
	
	private String version;

	public HandlerVersion(String version) {
		super();
		this.version = version;
	}

	@Override
	public HandlerAbstract copy() {
		return new HandlerVersion(this.version);
	}
	
	/**
	 * This returns the version number.
	 * @param parameters a map of key and value that was passed through the REST request
	 * @return a pair where the first element is the content type and the bytes are the output bytes to send back
	 */
	@Override
	public Pair<byte[], byte[]> handle(InetAddress ip, HTTPRequest httpRequestType, Map<String, String> headers, String restFunction, Map<String, String> parameters) {
		Pair<byte[], byte[]> pair = null;
		
		JSONObject ret = new JSONObject();
		ret.put("version", version);
		ret.put("error", "false");
		
		pair = new Pair<byte[],byte[]>(HandlerAbstract.getContentTypeHeader_JSON(),wrapCallback(parameters,ret.toString()).getBytes());
		
		getLog().info("Version is "+version);
		return pair;
	}
}


