package controller;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import voiceSpeak.Trying_Different_Languages;

public class FXMLController implements Initializable {

	private Boolean permission = LoginViewController.permission;
	
	@FXML
	private AnchorPane root;
	
	private Trying_Different_Languages tdl = new Trying_Different_Languages();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public AnchorPane loadThis() throws IOException {
		AnchorPane root = new AnchorPane();
		
		URL thisURL = this.getClass().getClassLoader().getResource("view/FXML_view.fxml");
		root = FXMLLoader.load(thisURL);
		return root;
	}
	
	
	public void onManageUsers() {
		if(permission) {
		tdl.speak("Manage users");
	}
	}
	
	public void onLend() {
		if(permission) {
			tdl.speak("Lend books");
		}
	}
	
	public void onReturn() {
		if(permission) {
			tdl.speak("Return books");
		}
	}
	
	public void onManageBooks() {
		if(permission) {
			tdl.speak("Manage books");
		}
	}
	@FXML
	private void loadLendingView(ActionEvent event) {
		try {
			AnchorPane pane = new AnchorPane();
			FXMLController_lending a = new FXMLController_lending();
			root.getChildren().setAll(a.loadThis());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@FXML
	private void loadReturningView(ActionEvent event) {
		try {
			AnchorPane pane = new AnchorPane();
			FXMLController_returning a = new FXMLController_returning();
			root.getChildren().setAll(a.loadThis());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@FXML
	private void loadUserView(ActionEvent event) {
		try {
			AnchorPane pane = new AnchorPane();
			FXMLController_user a = new FXMLController_user();
			root.getChildren().setAll(a.loadThis());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@FXML
	private void loadBookView(ActionEvent event) {
		try {
			AnchorPane pane = new AnchorPane();
			FXMLController_book a = new FXMLController_book();
			root.getChildren().setAll(a.loadThis());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
