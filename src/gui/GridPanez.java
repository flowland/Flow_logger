package gui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.UserData;

public class GridPanez {
	private Label name, regnr;
	private TextField nameInput, regnrInput;
	public GridPane makePane() {
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(15, 15, 15, 15));
		grid.setVgap(8);
		grid.setHgap(10);
		name = new Label("Kasutaja nimi: ");
		grid.setConstraints(name, 0, 0);
		nameInput = new TextField();
		grid.setConstraints(nameInput, 0, 1);
		regnr = new Label("Auto reg. nr: ");
		grid.setConstraints(regnr, 1, 0);
		regnrInput = new TextField();
		grid.setConstraints(regnrInput, 1, 1);
		Button recButton = new Button("Salvesta ");
		recButton.setOnAction(e -> {
			nameInput.setEditable(false);
			regnrInput.setEditable(false);
		});
		grid.setConstraints(recButton, 2, 1);
		grid.getChildren().addAll(name, nameInput, regnr, regnrInput, recButton);
		return grid;
	}
	public GridPane getGridPane() {
		GridPanez gridPane = new GridPanez();
		return gridPane.makePane();
	}
	// method for "Salvesta" button click
		/*public void recButton() {
			String input = nameInput.getText();
			String regn = regnrInput.getText();
			UserData user = new UserData(input, regn);
		}*/
}
