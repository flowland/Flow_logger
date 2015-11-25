package application;
	
import java.util.ArrayList;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;


public class Main extends Application{
	
	Stage window;
	Button button1;
	ArrayList<UserData> UserInfo = new ArrayList<UserData>();
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			window = primaryStage;
			window.setTitle("Drivelogger D99");
			
			
			//ylemine menyy - kasutaja + reg.nr
			
			GridPane grid = new GridPane();
			grid.setPadding(new Insets(15, 15, 15, 15));
			grid.setVgap(8);
			grid.setHgap(10);
			
			//auto kasutaja nimi
			Label name = new Label("Kasutaja nimi: ");
			grid.setConstraints(name, 0, 0);
			
			TextField nameInput = new TextField();
			grid.setConstraints(nameInput, 0, 1);
			
			//reg. nr
			Label regnr = new Label("Auto reg. nr: ");
			grid.setConstraints(regnr, 1, 0);
			
			TextField regnrInput = new TextField();
			grid.setConstraints(regnrInput, 1, 1);
			
			//nupp kasutaja andmete salvestamiseks
			Button button1 = new Button("Salvesta ");
			button1.setOnAction(e -> {
				String input = nameInput.getText();
				String regn = regnrInput.getText();
				UserData user = new UserData(input, regn);
				UserInfo.add(user);
			});
			
			grid.setConstraints(button1, 2, 1);
			grid.getChildren().addAll(name, nameInput, regnr, regnrInput, button1);
			
			Scene scene = new Scene(grid, 500, 400);
			window.setScene(scene);
			window.show();
			
		
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public HBox addHBox() {
	    HBox hbox = new HBox();
	    hbox.setPadding(new Insets(15, 12, 15, 12));
	    hbox.setSpacing(10);
	    hbox.setStyle("-fx-background-color: #336699;");

	    Label label1 = new Label("Kasutaja nimi: ");
	    label1.setPrefSize(100, 20);

	    Label label2 = new Label("Auto reg. nr: ");
	    label2.setPrefSize(100, 20);
	    hbox.getChildren().addAll(label1, label2);

	    return hbox;
	}
}
