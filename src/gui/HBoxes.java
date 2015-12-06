package gui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import gui.Main;

public class HBoxes {
	
	Main mainApp = new Main();
	TextField newDate, newStart, newStop, newGoal;
	HBox hbox;
	
	public HBox makeHBox() {
		hbox = new HBox();
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
		Button addEntry = new Button("Sisesta");
		addEntry.setOnAction(e -> {
			mainApp.addEntryClick();
		});
		Button deleteEntry = new Button("Kustuta");
		deleteEntry.setOnAction(e -> {
			mainApp.deleteEntryClick();
		});
		hbox.getChildren().addAll(newDate, newStart, newStop, newGoal, addEntry, deleteEntry);
		return hbox;
	}
	public HBox getHBox() {
		HBoxes hbox = new HBoxes();
		return hbox.makeHBox();
	}
}
