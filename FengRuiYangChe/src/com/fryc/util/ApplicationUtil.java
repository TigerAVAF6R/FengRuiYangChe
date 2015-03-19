package com.fryc.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.UUID;

public final class ApplicationUtil {

	public static String generateNewID() {
		return UUID.randomUUID().toString();
	}
	
	public static <T> List<T> deepCopyList(List<T> src) throws IOException, ClassNotFoundException {

		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(byteOut);
		out.writeObject(src);
		ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
		ObjectInputStream in = new ObjectInputStream(byteIn);

		@SuppressWarnings("unchecked")
		List<T> dest = (List<T>) in.readObject();

		return dest;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
