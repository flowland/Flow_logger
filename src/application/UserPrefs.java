package application;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class UserPrefs {
	public Preferences prefs;

	public void setPreference(UserData user) throws BackingStoreException {
		prefs = Preferences.userRoot().node(this.getClass().getName());
		prefs.put("regnr", user.getRegnr());
		prefs.put("user", user.getUserName());
	}

	public String[] getPreference() {
		String[] returnKeys = new String[2];
		String regnr = prefs.get("regnr", "000ABC");
		String username = prefs.get("user", "default user");
		returnKeys[0] = username;
		returnKeys[1] = regnr;
		return returnKeys;

	}

}
