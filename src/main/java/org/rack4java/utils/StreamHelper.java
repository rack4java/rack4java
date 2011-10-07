package org.rack4java.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class StreamHelper {

	private static final int BUFFER_SIZE = 4096;

	public static byte[] readAsBytes(InputStream in) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			int byteCount = 0;
			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead;

			while ((bytesRead = in.read(buffer)) != -1) {
				out.write(buffer, 0, bytesRead);
				byteCount += bytesRead;
			}
			out.flush();
			return out.toByteArray();
		} finally {
			try {
				in.close();
			} catch (IOException ex) {}
			try {
				out.close();
			} catch (IOException ex) {}
		}
	}

	public static String readAsString(InputStream in) throws IOException {
		return new String(readAsBytes(in));
	}

	public static String readAsString(InputStream in, Charset charset) throws IOException {
		return new String(readAsBytes(in), charset);
	}
}
