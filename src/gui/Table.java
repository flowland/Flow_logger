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
		dateColumn.prefWidthProperty().bind(logTable.widthProperty().divide(4));
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
		TableColumn<LogEntry, Integer> startColumn = new TableColumn<>("Algnäit");
		startColumn.setMinWidth(120);
		startColumn.prefWidthProperty().bind(logTable.widthProperty().divide(4));
		startColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
		TableColumn<LogEntry, Integer> stopColumn = new TableColumn<>("Lõppnäit");
		stopColumn.setCellValueFactory(new PropertyValueFactory<>("stop"));
		stopColumn.prefWidthProperty().bind(logTable.widthProperty().divide(4));
		TableColumn<LogEntry, String> goalColumn = new TableColumn<>("Eesmärk");
		goalColumn.setCellValueFactory(new PropertyValueFactory<>("goal"));
		goalColumn.prefWidthProperty().bind(logTable.widthProperty().divide(3));
		//add columns
		logTable.getColumns().addAll(dateColumn, startColumn, stopColumn, goalColumn);
	}
	
	public TableView<LogEntry> getLogTable() {
		makeTable();
		return logTable;
	}

	public void setLogTable(TableView<LogEntry> logTable) {
		this.logTable = logTable;
	}
}
