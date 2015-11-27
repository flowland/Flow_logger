package application;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class DataSave implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 0;
	public DataSave() {

		try {
			Object obj = parser.parse(new FileReader(feedFile));
			feedFile.getParentFile().mkdirs();
		} catch (FileNotFoundException e) {
			feedFile.getParentFile().mkdirs();
			try {
				feedFile.createNewFile();

				FileWriter file = new FileWriter(feedFile);

				JSONObject obj = new JSONObject();
				JSONArray init = new JSONArray();
				obj.put("feeds", init);
				file.write(obj.toJSONString());
				file.flush();
				file.close();

				System.out.println("feeds.json created");
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void writeFile(UserData udata) {
		try {
			
			FileOutputStream output = new FileOutputStream("udata.data"); //write to disk
			ObjectOutputStream writeObj = new ObjectOutputStream(output);

				// Write object out to disk
				writeObj.writeObject(udata);
		} catch (FileNotFoundException e) {
			System.out.println("Error. File not found...");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error.");
			e.printStackTrace();
		} 
		
		
	}
	
}
