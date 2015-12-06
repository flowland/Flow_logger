package gui;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;

public class TopMenu {
	Main refMain = new Main();
	public MenuBar topMenuBar() {
	//the menu
	Menu fileMenu = new Menu("File");
	//menu items
	MenuItem fileLoad = new MenuItem("Load");
	fileLoad.setOnAction(e -> refMain.loadLog());
	MenuItem fileSave = new MenuItem("Save");
	fileSave.setOnAction(e -> refMain.handleSaveAs());
	MenuItem exit = new MenuItem("Exit");
	exit.setOnAction(e -> System.exit(0));
	fileMenu.getItems().add(fileLoad);
	fileMenu.getItems().add(fileSave);
	fileMenu.getItems().add(exit);
	// actual menubar
	MenuBar newBar = new MenuBar();
	newBar.getMenus().addAll(fileMenu);
	VBox menuBox = new VBox();
	menuBox.getChildren().addAll(newBar, gridPane.getGridPane());
	}
}
