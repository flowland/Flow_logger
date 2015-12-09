package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import gui.Main;

public class HBoxes {
	
	Main mainApp = new Main();
	TextField newDate, newStart, newStop, newGoal;
	public HBoxes() {
		this.makeButtonBox();
		this.makeHBox();
	}
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
		hbox.getChildren().addAll(newDate, newStart, newStop, newGoal);
		return hbox;
	}
	public HBox makeButtonBox() {
		HBox buttons = new HBox(8);
		buttons.setPadding(new Insets(5, 5, 5, 5));
		buttons.setAlignment(Pos.CENTER);
		Button addEntry = new Button("Sisesta");
		addEntry.setOnAction(e -> {
			mainApp.addEntryClick();
		});
		Button deleteEntry = new Button("Kustuta");
		deleteEntry.setOnAction(e -> {
			mainApp.deleteEntryClick();
		});
		buttons.getChildren().addAll(addEntry, deleteEntry);
		return buttons;
	}
	public HBox getHBox() {
		return makeHBox();
	}
	public HBox getButtonBox() {
		return makeButtonBox();
	}
}
