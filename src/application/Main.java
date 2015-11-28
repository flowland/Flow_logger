package application;

import java.util.ArrayList;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class Main extends Application {

	TableView<LogEntry> logTable;
	String[] PrefArray = new String[2];
	ArrayList<UserData> UserInfo = new ArrayList<UserData>();
	TextField newDate, newStart, newStop, newGoal, nameInput, regnrInput;
	Label name, regnr;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			TableColumn<LogEntry, String> dateColumn = new TableColumn<>("Kuupäev"); // kuupäeva
																						// column
			dateColumn.setMinWidth(100);
			dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
			TableColumn<LogEntry, Integer> startColumn = new TableColumn<>("Algnäit"); // algnäidu
																						// column
			startColumn.setMinWidth(120);
			startColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
			TableColumn<LogEntry, Integer> stopColumn = new TableColumn<>("Lõppnäit"); // lõppnäidu
																						// column
			stopColumn.setMinWidth(120);
			stopColumn.setCellValueFactory(new PropertyValueFactory<>("stop"));
			TableColumn<LogEntry, String> goalColumn = new TableColumn<>("Eesmärk"); // eesmärgi
																						// column
			goalColumn.setMinWidth(120);
			goalColumn.setCellValueFactory(new PropertyValueFactory<>("goal"));

			logTable = new TableView<>(); // loon tabeli
			logTable.setItems(allEntries());
			logTable.getColumns().addAll(dateColumn, startColumn, stopColumn, goalColumn);

			BorderPane mainPane = new BorderPane(); // yldine layout
			mainPane.setTop(makePane());
			mainPane.setCenter(logTable);
			mainPane.setBottom(makeHBox());

			Scene scene = new Scene(mainPane, 600, 400); // setting the scene
			Stage window = primaryStage;
			window.setTitle("Drivelogger D99");
			window.setScene(scene);
			window.show();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public GridPane makePane() {

		GridPane grid = new GridPane();
		grid.setPadding(new Insets(15, 15, 15, 15));
		grid.setVgap(8);
		grid.setHgap(10);
		name = new Label("Kasutaja nimi: "); // auto kasutaja nimi
		grid.setConstraints(name, 0, 0);
		nameInput = new TextField();
		grid.setConstraints(nameInput, 0, 1);
		regnr = new Label("Auto reg. nr: "); // auto reg. nr
		grid.setConstraints(regnr, 1, 0);
		regnrInput = new TextField();
		grid.setConstraints(regnrInput, 1, 1);
		
		Button recButton = new Button("Salvesta "); // nupp kasutaja andmete
													// salvestamiseks
		recButton.setOnAction(e -> {
			recButton();
			nameInput.setEditable(false);
			regnrInput.setEditable(false);
			
		});
		grid.setConstraints(recButton, 2, 1);
		grid.getChildren().addAll(name, nameInput, regnr, regnrInput, recButton);
		return grid;
	}

	// method for "Salvesta" button click
	public void recButton() {
		// ArrayList<String> PrefArray = new ArrayList<String>();
		String input = nameInput.getText();
		String regn = regnrInput.getText();
		UserData user = new UserData(input, regn);
		
		UserPrefs prefs = new UserPrefs();
		try {
			PrefArray = prefs.setPreference(user);
		} catch (BackingStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// UserInfo.add(user);
		// PrefArray.add(input);
		// PrefArray.add(regn);
		// this.PrefArray = PrefArray;
		// return PrefArray;
	}

	public HBox makeHBox() {
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
		Button addEntry = new Button("Sisesta");
		addEntry.setOnAction(e -> {
			addEntryClick();
		});
		Button deleteEntry = new Button("Kustuta");
		deleteEntry.setOnAction(e -> {
			deleteEntryClick();
		});
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15, 15, 50, 15));
		hbox.setFillHeight(true);
		hbox.setSpacing(8);
		hbox.getChildren().addAll(newDate, newStart, newStop, newGoal, addEntry, deleteEntry);
		return hbox;
	}

	public void addEntryClick() {
		LogEntry newEntry = new LogEntry();
		newEntry.setDate(newDate.getText());
		newEntry.setStart(Integer.parseInt(newStart.getText()));
		newEntry.setStop(Integer.parseInt(newStop.getText()));
		newEntry.setGoal(newGoal.getText());
		logTable.getItems().add(newEntry);
		newDate.clear();
		newStart.clear();
		newStop.clear();
		newGoal.clear();
	}

	public void deleteEntryClick() {
		ObservableList<LogEntry> entrySelected, allEntries;
		allEntries = logTable.getItems();
		entrySelected = logTable.getSelectionModel().getSelectedItems();
		entrySelected.forEach(allEntries::remove);
	}

	public ObservableList<LogEntry> allEntries() {
		ObservableList<LogEntry> entries = FXCollections.observableArrayList();
		entries.add(new LogEntry("01.01.2000", "Sõidu eesmärk", 000000000, 000000000));
		return entries;
	}
	/**
	 * Returns the person file preference, i.e. the file that was last opened.
	 * The preference is read from the OS specific registry. If no such
	 * preference can be found, null is returned.
	 * 
	 * @return
	 */
	/*
	 * public String getUserPrefs() { Preferences prefs =
	 * Preferences.userRoot().node(this.getClass().getName()); String userName =
	 * UserData.get(0); String regNr = UserData.get(1);
	 * System.out.println(prefs.get(userName, "Hello World")); }
	 */

}
