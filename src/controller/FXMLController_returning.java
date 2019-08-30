package controller;

import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class FXMLController_returning implements Initializable{
	
	@FXML
	private AnchorPane returningPane;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	@FXML
	private void loadView(ActionEvent event) {
		try {
			AnchorPane pane = new AnchorPane();
			pane = FXMLLoader.<AnchorPane>load(Paths.get("src/view/FXML_view.fxml").toUri().toURL());
			returningPane.getChildren().setAll(pane);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
