package gui;

import java.io.File;
import java.util.ArrayList;
import java.util.prefs.BackingStoreException;
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
import model.UserData;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import gui.Table;

public class Main extends Application {

	// TableView<LogEntry> logTable;
	// UserData[] PrefArray = new UserData[1];
	ObservableList<LogEntry> userInfo = FXCollections.observableArrayList();
	TextField newDate, newStart, newStop, newGoal, nameInput, regnrInput;
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
		// the menu
		Menu fileMenu = new Menu("File");
		// menu items
		MenuItem fileLoad = new MenuItem("Load");
		fileLoad.setOnAction(e -> loadLog());
		MenuItem fileSave = new MenuItem("Save");
		fileSave.setOnAction(e -> handleSaveAs());
		MenuItem exit = new MenuItem("Exit");
		exit.setOnAction(e -> System.exit(0));
		fileMenu.getItems().add(fileLoad);
		fileMenu.getItems().add(fileSave);
		fileMenu.getItems().add(exit);
		// actual menubar
		MenuBar newBar = new MenuBar();
		newBar.getMenus().addAll(fileMenu);
		VBox menuBox = new VBox();
		menuBox.getChildren().addAll(newBar, gridPane.getGridPane());
		VBox bottomBox = new VBox();
		bottomBox.getChildren().addAll(makeHBox(), buttonBox());

		BorderPane mainPane = new BorderPane(); // main layout
		mainPane.setTop(menuBox);
		mainPane.setCenter(entryTable);
		mainPane.setBottom(bottomBox);

		Scene scene = new Scene(mainPane, 600, 400); // setting the scene
		Stage window = primaryStage;
		window.setTitle("Drivelogger D99");
		window.setScene(scene);
		window.show();
	}

	/*
	 * public GridPane makePane() { GridPane grid = new GridPane();
	 * grid.setPadding(new Insets(15, 15, 15, 15)); grid.setVgap(8);
	 * grid.setHgap(10); name = new Label("Kasutaja nimi: ");
	 * grid.setConstraints(name, 0, 0); nameInput = new TextField();
	 * grid.setConstraints(nameInput, 0, 1); regnr = new Label("Auto reg. nr: "
	 * ); grid.setConstraints(regnr, 1, 0); regnrInput = new TextField();
	 * grid.setConstraints(regnrInput, 1, 1); Button recButton = new Button(
	 * "Salvesta "); recButton.setOnAction(e -> { recButton();
	 * nameInput.setEditable(false); regnrInput.setEditable(false); });
	 * grid.setConstraints(recButton, 2, 1); grid.getChildren().addAll(name,
	 * nameInput, regnr, regnrInput, recButton); return grid; }
	 * 
	 * // method for "Salvesta" button click public void recButton() { //
	 * ArrayList<String> PrefArray = new ArrayList<String>(); String input =
	 * nameInput.getText(); String regn = regnrInput.getText(); UserData user =
	 * new UserData(input, regn); //PrefArray[0] = user; }
	 */

	public HBox makeHBox() {
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15, 15, 50, 15));
		hbox.setFillHeight(true);
		hbox.setSpacing(8);
		newDate = new TextField(); // uue sissekande: kuupäev
		newDate.setPromptText("Kuupäev");
		newDate.setMinWidth(90);
		newStart = new TextField(); // uue sissekande: algnäit
		newStart.setPromptText("Algnäit");
		newStart.setMinWidth(100);
		newStop = new TextField(); // uue sissekande: lõppnäit
		newStop.setPromptText("Lõppnäit");
		newStop.setMinWidth(100);
		newGoal = new TextField(); // uue sissekande: eesmärk
		newGoal.setPromptText("Eesmärk");
		newGoal.setMinWidth(100);
		newGoal.setMaxWidth(100);
		// sissekande tgemise nupp
		/*Button addEntry = new Button("Sisesta");
		addEntry.setOnAction(e -> {
			addEntryClick();
		});
		Button deleteEntry = new Button("Kustuta");
		deleteEntry.setOnAction(e -> {
			deleteEntryClick();
		}); */
		hbox.getChildren().addAll(newDate, newStart, newStop, newGoal);
		return hbox;
	}
	public HBox buttonBox() {
		HBox buttons = new HBox(8);
		buttons.setPadding(new Insets(15, 15, 15, 15));
		buttons.setAlignment(Pos.CENTER);
		Button addEntry = new Button("Sisesta");
		addEntry.setOnAction(e -> {
			addEntryClick();
		});
		Button deleteEntry = new Button("Kustuta");
		deleteEntry.setOnAction(e -> {
			deleteEntryClick();
		});
		buttons.getChildren().addAll(addEntry, deleteEntry);
		return buttons;
	}

	public void addEntryClick() {
		LogEntry newEntry = new LogEntry();
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
	}

	public void deleteEntryClick() {
		ObservableList<LogEntry> entrySelected, allEntries;
		allEntries = entryTable.getItems();
		entrySelected = entryTable.getSelectionModel().getSelectedItems();
		entrySelected.forEach(allEntries::remove);
	}

	public File getPath() {
		Preferences prefs = Preferences.userNodeForPackage(Main.class);
		String path = prefs.get("filePath", null);
		if (path != null) {
			return new File(path);
		} else {
			return null;
		}
	}

	/**
	 * Sets the file path of the currently loaded file. The path is persisted in
	 * the OS specific registry.
	 * 
	 * @param file
	 *            the file or null to remove the path
	 */
	public void setPath(File file) {
		Preferences prefs = Preferences.userNodeForPackage(Main.class);
		if (file != null) {
			prefs.put("filePath", file.getPath());
			// Update the stage title.
			window.setTitle("Sõidupäevik: " + file.getName());
		} else {
			prefs.remove("filePath");
			// Update the stage title.
			window.setTitle("Sõidupäevik");
		}
	}

	public void saveEntryData(File file) {
		try {
			JAXBContext context = JAXBContext.newInstance(ListXML.class);
			Marshaller marsh = context.createMarshaller();
			marsh.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			// wrapping the list
			ListXML tempList = new ListXML();
			tempList.setEntries(userInfo);
			// marshal and save to file
			marsh.marshal(tempList, file);
			// save the path to registry;
			setPath(file);
		} catch (Exception e) {

		}
	}

	// loads the list from file
	public void loadEntryData(File file) {
		try {
			JAXBContext context = JAXBContext.newInstance(ListXML.class);
			Unmarshaller unm = context.createUnmarshaller();
			// reading xml from file and unmarshalling into list
			ListXML tempList = (ListXML) unm.unmarshal(file);
			// clears table and then adds the entries
			entryTable.getItems().clear();
			entryTable.getItems().addAll(tempList.getEntries());
			setPath(file);
		} catch (Exception e) {

		}
	}

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

	public void handleSaveAs() {
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

	/*
	 * public ObservableList<LogEntry> allEntries() { ObservableList<LogEntry>
	 * entries = FXCollections.observableArrayList(); entries.add(new
	 * LogEntry("1.1.2000", "Sõidu eesmärk", 0, 0)); return entries; }
	 */
}
