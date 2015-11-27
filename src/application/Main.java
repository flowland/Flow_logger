package application;

import java.util.ArrayList;

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


	Button button1;
	TableView<LogEntry> logTable;
	//ArrayList<UserData> UserInfo = new ArrayList<UserData>();
	TextField newDate, newStart, newStop, newGoal;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			//kuupäev!!
			TableColumn<LogEntry, String> dateColumn = new TableColumn<>("Kuupäev");
			dateColumn.setMinWidth(100);
			dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
			//algnäit!!
			TableColumn<LogEntry, Integer> startColumn = new TableColumn<>("Algnäit");
			startColumn.setMinWidth(120);
			startColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
			//lõppnäit!
			TableColumn<LogEntry, Integer> stopColumn = new TableColumn<>("Lõppnäit");
			stopColumn.setMinWidth(120);
			stopColumn.setCellValueFactory(new PropertyValueFactory<>("stop"));
			//eesmärk!!
			TableColumn<LogEntry, String> goalColumn = new TableColumn<>("Eesmärk");
			goalColumn.setMinWidth(120);
			goalColumn.setCellValueFactory(new PropertyValueFactory<>("goal"));
			logTable = new TableView<>();
			logTable.setItems(allEntries());
			logTable.getColumns().addAll(dateColumn, startColumn, stopColumn, goalColumn);
	
	
			BorderPane mainPane = new BorderPane();
			mainPane.setTop(makePane());
			mainPane.setCenter(logTable);
			mainPane.setBottom(makeHBox());
			
			Scene scene = new Scene(mainPane, 600, 400);
			Stage window = primaryStage;
			window.setTitle("Drivelogger D99");
			window.setScene(scene);
			window.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public HBox makeHBox() {
		//uue sissekande lisamine: kuupäev
		newDate = new TextField();
		newDate.setPromptText("Kuupäev");
		newDate.setMinWidth(90);
		//uus sissekanne: algnäit
		newStart = new TextField();
		newStart.setPromptText("Algnäit");
		newStart.setMinWidth(100);
		//uus sissekanne: lõppnäit
		newStop = new TextField();
		newStop.setPromptText("Lõppnäit");
		newStop.setMinWidth(100);
		//uus sissekanne: eesmärk
		newGoal = new TextField();
		newGoal.setPromptText("Eesmärk");
		newGoal.setMinWidth(100);
		//sissekande tgemise nupp
		Button addEntry = new Button("Sisesta");
		addEntry.setOnAction(e -> {
			addEntryClick();
		//	application.DataSave.writeFile(user);
		});
		Button deleteEntry = new Button("Kustuta");
		deleteEntry.setOnAction(e -> {
			deleteEntryClick();
		});
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15, 15, 15, 15));
		hbox.setSpacing(10);
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
		//entries.add(new LogEntry());
		return entries;
	}
	  public GridPane makePane() {
		  
		  GridPane grid = new GridPane();
			grid.setPadding(new Insets(15, 15, 15, 15));
			grid.setVgap(8);
			grid.setHgap(10);
			// auto kasutaja nimi
			Label name = new Label("Kasutaja nimi: ");
			grid.setConstraints(name, 0, 0);
			TextField nameInput = new TextField();
			grid.setConstraints(nameInput, 0, 1);
			// reg. nr
			Label regnr = new Label("Auto reg. nr: ");
			grid.setConstraints(regnr, 1, 0);
			TextField regnrInput = new TextField();
			grid.setConstraints(regnrInput, 1, 1);
			// nupp kasutaja andmete salvestamiseks
			Button button1 = new Button("Salvesta ");
			button1.setOnAction(e -> {
				String input = nameInput.getText();
				String regn = regnrInput.getText();
				UserData user = new UserData(input, regn);
				application.DataSave.writeFile(user);
				//UserInfo.add(user);
			});
			grid.setConstraints(button1, 2, 1);
			grid.getChildren().addAll(name, nameInput, regnr, regnrInput, button1);
			return grid;
	  }
	 

}
