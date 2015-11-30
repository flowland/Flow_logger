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
	UserData[] PrefArray = new UserData[1];
	ArrayList<LogEntry> UserInfo = new ArrayList<LogEntry>();
	TextField newDate, newStart, newStop, newGoal, nameInput, regnrInput;
	Label name, regnr;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			TableColumn<LogEntry, String> dateColumn = new TableColumn<>("Kuupäev"); // kuupäeva column
			dateColumn.setMinWidth(100);
			dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
			TableColumn<LogEntry, Integer> startColumn = new TableColumn<>("Algnäit"); // algnäidu // colum																			
			startColumn.setMinWidth(120);
			startColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
			TableColumn<LogEntry, Integer> stopColumn = new TableColumn<>("Lõppnäit"); // lõppnäidu// column
			stopColumn.setMinWidth(120);
			stopColumn.setCellValueFactory(new PropertyValueFactory<>("stop"));
			TableColumn<LogEntry, String> goalColumn = new TableColumn<>("Eesmärk"); // eesmärgi // column																			
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
		PrefArray[0] = user;
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
		UserInfo.add(newEntry);
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
		entries.add(new LogEntry("1.1.2000", "Sõidu eesmärk", 0, 0));
		return entries;
	}
}
