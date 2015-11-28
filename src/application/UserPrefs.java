package application;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class UserPrefs {
	private Preferences prefs;
	
	public String[] setPreference(UserData user) throws BackingStoreException {
		prefs = Preferences.userRoot().node(this.getClass().getName());
		String[] returnKeys = new String[2];
		//String[] keys = prefs.keys();
		//String username = prefs.get("user", "default user");
		String regnr = prefs.get("regnr", user.getRegnr());
		String username = prefs.get("user", user.getUserName());
				returnKeys[0] = username;
				returnKeys[1] = regnr;
		// prefs.remove(ID1);
		return returnKeys;
	}

	//public String[] getPreference(Preferences prefs) {
		//String[] prefArray = 

	//}

	/*
	 * // First we will get the values // Define a boolean value
	 * System.out.println(prefs.getBoolean(ID1, true)); // Define a string with
	 * default "Hello World System.out.println(prefs.get(ID2, "Hello World"));
	 * // Define a integer with default 50 System.out.println(prefs.getInt(ID3,
	 * 50));
	 * 
	 * // now set the values prefs.putBoolean(ID1, false); prefs.put(ID2,
	 * "Hello Europa"); prefs.putInt(ID3, 45);
	 */

	// Delete the preference settings for the first value
	// prefs.remove(ID1);

}
