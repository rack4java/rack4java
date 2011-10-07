package org.jrack.examples;

import java.io.File;
import java.util.Map;

import org.jrack.JRack;
import org.jrack.RackEnvironment;
import org.jrack.RackResponse;

public class FileServer implements JRack {

	private File root;

	public FileServer(String root) {
		this.root = new File(root);
	}

	@Override public RackResponse call(Map<String, Object> environment) throws Exception {
		String filename = (String) environment.get(RackEnvironment.PATH_INFO);
		File file = new File(root, filename);
		if (file.canRead()) {
			return new RackResponse(200, file, "Content-Type", "text/html");
		}
		return new RackResponse(404, "File [" + filename + "] not found");
	}

}
