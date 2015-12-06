package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.LogEntry;

public class Table {
	private TableView<LogEntry> logTable;

	public void makeTable() {
		logTable = new TableView<>();
		// Create columns
		TableColumn<LogEntry, String> dateColumn = new TableColumn<>("Kuupäev");
		dateColumn.setMinWidth(100);
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
		TableColumn<LogEntry, Integer> startColumn = new TableColumn<>("Algnäit");
		startColumn.setMinWidth(120);
		startColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
		TableColumn<LogEntry, Integer> stopColumn = new TableColumn<>("Lõppnäit");
		stopColumn.setCellValueFactory(new PropertyValueFactory<>("stop"));
		TableColumn<LogEntry, String> goalColumn = new TableColumn<>("Eesmärk");
		goalColumn.setCellValueFactory(new PropertyValueFactory<>("goal"));
		// create Table and 
		//logTable.setItems(allEntries());
		logTable.getColumns().addAll(dateColumn, startColumn, stopColumn, goalColumn);

	}
	public ObservableList<LogEntry> allEntries() {
		ObservableList<LogEntry> entries = FXCollections.observableArrayList();
		entries.add(new LogEntry("1.1.2000", "Sõidu eesmärk", 0, 0));
		return entries;
	}  

	public TableView<LogEntry> getLogTable() {
		makeTable();
		return logTable;
	}

	public void setLogTable(TableView<LogEntry> logTable) {
		this.logTable = logTable;
	}
}
