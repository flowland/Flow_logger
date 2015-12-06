package data;

import java.io.*;

public class DataWrite {
	private Object data;
	private String filename;
	private String path;

	public DataWrite(Object LogEntry, String filename, String path) {
		this.data = LogEntry;
		this.filename = filename;
		this.path = path;
	}

	/* Writes an object to disk */
	public void writeToDisk() {
		FileOutputStream output = null;
		ObjectOutputStream objectOutput;
		try {
			output = new FileOutputStream(this.path + this.filename);
		} catch (FileNotFoundException e) {
			// TODO catch statement
		}
		// Writes object
		try {
			objectOutput = new ObjectOutputStream(output);
			objectOutput.writeObject(this.data);

		} catch (IOException e) {
			System.out.println("Input/Output error" + e);
		}

	}

}
