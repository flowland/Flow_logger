package gui;

import java.io.File;
import java.util.prefs.Preferences;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import data.ListXML;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.LogEntry;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import gui.Table;

public class Main extends Application {

	File cssfile = new File("JMetroLightTheme.css");
	ObservableList<LogEntry> userInfo = FXCollections.observableArrayList();
	TextField newDate, newStart, newStop, newGoal, nameInput, regnrInput;
	Button addEntry, deleteEntry;
	Label name, regnr;
	Table table = new Table();
	TableView<LogEntry> entryTable = table.getLogTable();
	GridPanez gridPane = new GridPanez();
	Stage window;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		VBox menuBox = new VBox();
		menuBox.getChildren().addAll(topMenuBar(), gridPane.getGridPane());
		VBox bottomBox = new VBox();
		bottomBox.getChildren().addAll(makeHBox(), buttonBox());
		// the main layout
		BorderPane mainPane = new BorderPane();
		mainPane.setTop(menuBox);
		mainPane.setCenter(entryTable);
		mainPane.setBottom(bottomBox);
		Scene scene = new Scene(mainPane, 700, 500);
		scene.getStylesheets().clear();
		scene.getStylesheets().add("file:///" + cssfile.getAbsolutePath().replace("\\", "/"));
		Stage window = primaryStage;
		window.setTitle("Drivelogger D99");
		window.setScene(scene);
		window.show();
	}

	// Makes the File menu
	public MenuBar topMenuBar() {
		// the menu
		Menu fileMenu = new Menu("File");
		// menu items
		MenuItem fileLoad = new MenuItem("Load");
		fileLoad.setOnAction(e -> loadLog());
		MenuItem fileSave = new MenuItem("Save");
		fileSave.setOnAction(e -> saveLog());
		MenuItem fileSaveAs = new MenuItem("Save As");
		fileSaveAs.setOnAction(e -> saveLogAs());
		MenuItem exit = new MenuItem("Exit");
		exit.setOnAction(e -> System.exit(0));
		fileMenu.getItems().add(fileLoad);
		fileMenu.getItems().add(fileSave);
		fileMenu.getItems().add(fileSaveAs);
		fileMenu.getItems().add(exit);
		// actual menubar
		MenuBar newBar = new MenuBar();
		newBar.getMenus().addAll(fileMenu);
		return newBar;
	}

	// makes the HBox for the new entry textfields
	public HBox makeHBox() {
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15, 15, 15, 15));
		hbox.setFillHeight(true);
		hbox.setSpacing(8);
		newDate = new TextField();
		newDate.setPromptText("Kuupäev");
		newDate.setMinWidth(90);
		hbox.setHgrow(newDate, Priority.ALWAYS);
		newStart = new TextField();
		newStart.setPromptText("Algnäit");
		newStart.setMinWidth(100);
		hbox.setHgrow(newStart, Priority.ALWAYS);
		newStop = new TextField();
		newStop.setPromptText("Lõppnäit");
		newStop.setMinWidth(100);
		hbox.setHgrow(newStop, Priority.ALWAYS);
		newGoal = new TextField();
		newGoal.setPromptText("Eesmärk");
		newGoal.setMinWidth(100);
		hbox.setHgrow(newGoal, Priority.ALWAYS);
		hbox.getChildren().addAll(newDate, newStart, newStop, newGoal);
		return hbox;
	}

	// Add and delete button for new log entry
	public HBox buttonBox() {
		HBox buttons = new HBox(8);
		buttons.setPadding(new Insets(5, 5, 20, 5));
		buttons.setAlignment(Pos.CENTER);
		addEntry = new Button("Sisesta");
		addEntry.setOnAction(e -> addEntryClick());
		deleteEntry = new Button("Kustuta");
		deleteEntry.setOnAction(e -> deleteEntryClick());
		buttons.getChildren().addAll(addEntry, deleteEntry);
		return buttons;
	}

	// adds the new entry to the table after doing some data validation
	public void addEntryClick() {
		LogEntry newEntry = new LogEntry();
		if (newDate.getText().trim().equals("")) {
			;
		} else if (intCheck(newStart) && intCheck(newStop)) {
			newEntry.setDate(newDate.getText());
			newEntry.setStart(Integer.parseInt(newStart.getText()));
			newEntry.setStop(Integer.parseInt(newStop.getText()));
			newEntry.setGoal(newGoal.getText());
			// adds entry to arraylist
			userInfo.add(newEntry);
			// adds it to the tableview
			entryTable.getItems().add(newEntry);
			// clears the text input areas
			newDate.clear();
			newStart.clear();
			newStop.clear();
			newGoal.clear();
		} else {
			Alert errors = new Alert(AlertType.ERROR);
			newStart.clear();
			newStop.clear();
			errors.initOwner(window);
			errors.setTitle("Viga sisestamisel");
			errors.setHeaderText("Alg- ja lõppnäidu väljad peavad sisaldama ainult (täis)arve!");
			errors.show();
		}
	}

	// checks a textfield for integer-ness
	public boolean intCheck(TextField field) {
		try {
			int start = Integer.parseInt(field.getText());
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	// deletes an entry from the list
	public void deleteEntryClick() {
		DeleteAlert alert = new DeleteAlert();
		int index = entryTable.getSelectionModel().getSelectedIndex();
		if (index < 0) {
			Alert redAlert = new Alert(AlertType.ERROR);
			redAlert.initOwner(window);
			redAlert.setTitle("Palun tähelepanu!");
			redAlert.setHeaderText("Väike probleem...");
			redAlert.setContentText("Ühtegi kirjet pole valitud");
			redAlert.showAndWait();
		} else if (alert.showDeleteConfirmation() == true) {
			entryTable.getItems().remove(index);
		}
	}

	// Returns the file that was last opened or null if it is not found
	public File getPath() {
		Preferences prefs = Preferences.userNodeForPackage(Main.class);
		String path = prefs.get("filePath", null);
		if (path != null) {
			return new File(path);
		} else {
			return null;
		}
	}

	// Sets the file path of the currently loaded file.
	public void setPath(File file) {
		Preferences prefs = Preferences.userNodeForPackage(Main.class);
		if (file != null) {
			prefs.put("filePath", file.getPath());
		} else {
			prefs.remove("filePath");
		}
	}

	// save list into file(save as)
	public void saveEntryData(File file) {
		try {
			JAXBContext context = JAXBContext.newInstance(ListXML.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			ListXML tempList = new ListXML();
			tempList.setEntries(userInfo);
			// marshal and save to file
			m.marshal(tempList, file);
			// save the file path to registry;
			setPath(file);
		} catch (Exception e) {

		}
	}

	// loads the list from file
	public void loadEntryData(File file) {
		try {
			JAXBContext context = JAXBContext.newInstance(ListXML.class);
			Unmarshaller unM = context.createUnmarshaller();
			// reading xml from file and unmarshalling into list
			ListXML tempList = (ListXML) unM.unmarshal(file);
			// clears table and then adds the entries to the table
			entryTable.getItems().clear();
			entryTable.getItems().addAll(tempList.getEntries());
			// adds all the entries to the list
			userInfo.addAll(tempList.getEntries());
			setPath(file);
		} catch (Exception e) {

		}
	}

	// opens filechooser to pick a file and feeds it to the loadEntryData method
	public void loadLog() {
		FileChooser pickOne = new FileChooser();
		// set filter
		FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("XML files", "*.xml");
		pickOne.getExtensionFilters().add(filter);
		// show dialog
		File file = pickOne.showOpenDialog(window);
		if (file != null) {
			loadEntryData(file);
		}
	}
	// gets the opened filepath and saves it, if it doesn't exist - save as with filechooser
	public void saveLog() {
        File file = getPath();
        if (file != null) {
            saveEntryData(file);
        } else {
            saveLogAs();
        }
    }
	// opens filechooser and creates file to go into saveEntryData method
	public void saveLogAs() {
		FileChooser fileChooser = new FileChooser();
		// Set extension filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);
		// Show save file dialog
		File file = fileChooser.showSaveDialog(window);
		if (file != null) {
			// Make sure it has the correct extension
			if (!file.getPath().endsWith(".xml")) {
				file = new File(file.getPath() + ".xml");
			}
			saveEntryData(file);
		}
	}
}
