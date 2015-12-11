package gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DeleteAlert {
	Button okButton, cancelButton;
	boolean areuOk = false;
	Stage confirmStage;

	public boolean showDeleteConfirmation() {
		confirmStage = new Stage();
		confirmStage.setTitle("Kustuta kirje?");
		confirmStage.initModality(Modality.APPLICATION_MODAL);
		okButton = new Button("OK");
		okButton.setOnAction(e -> okClicked());
		cancelButton = new Button("Pigem ei!");
		cancelButton.setOnAction(e -> cancelClicked());
		Label warningLabel = new Label();
		warningLabel.setText("Oled kindel, et tahad kirje kustutada?");
		VBox alert = new VBox(20);
		HBox buttons = new HBox(5);
		buttons.getChildren().addAll(okButton, cancelButton);
		alert.getChildren().addAll(warningLabel, buttons);
		alert.setAlignment(Pos.CENTER);
		buttons.setAlignment(Pos.CENTER);
		Scene scene = new Scene(alert, 280, 150);
		confirmStage.setScene(scene);
		confirmStage.showAndWait();
		return areuOk;
	}

	public boolean okClicked() {
		areuOk = true;
		confirmStage.close();
		return areuOk;
	}

	public boolean cancelClicked() {
		areuOk = false;
		confirmStage.close();
		return areuOk;
	}
}
