package controller;

import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class FXMLController implements Initializable {

	@FXML
	private AnchorPane root;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	@FXML
	private void loadLendingView(ActionEvent event) {
		try {
			AnchorPane pane = new AnchorPane();
			pane = FXMLLoader.<AnchorPane>load(Paths.get("src/view/FXML_lendingView.fxml").toUri().toURL());
			root.getChildren().setAll(pane);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@FXML
	private void loadReturningView(ActionEvent event) {
		try {
			AnchorPane pane = new AnchorPane();
			pane = FXMLLoader.<AnchorPane>load(Paths.get("src/view/FXML_returningView.fxml").toUri().toURL());
			root.getChildren().setAll(pane);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@FXML
	private void loadUserView(ActionEvent event) {
		try {
			AnchorPane pane = new AnchorPane();
			pane = FXMLLoader.<AnchorPane>load(Paths.get("src/view/FXML_userView.fxml").toUri().toURL());
			root.getChildren().setAll(pane);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@FXML
	private void loadBookView(ActionEvent event) {
		try {
			AnchorPane pane = new AnchorPane();
			pane = FXMLLoader.<AnchorPane>load(Paths.get("src/view/FXML_bookView.fxml").toUri().toURL());
			root.getChildren().setAll(pane);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@FXML
	private void loadPenaltiesView(ActionEvent event) {
		try {
			AnchorPane pane = new AnchorPane();
			pane = FXMLLoader.<AnchorPane>load(Paths.get("src/view/FXML_penaltiesView.fxml").toUri().toURL());
			root.getChildren().setAll(pane);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
