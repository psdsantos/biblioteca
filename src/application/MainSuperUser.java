package application;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainSuperUser {

	public AnchorPane loadThis() throws IOException {
		AnchorPane root = new AnchorPane();
		
		URL thisURL = this.getClass().getClassLoader().getResource("view/FXML_view.fxml");
		root = FXMLLoader.load(thisURL);
		return root;
	}
	
	public void startGUI() {
		try 
		{
			AnchorPane root;
			
			Stage primaryStage = new Stage();
			URL thisURL = this.getClass().getClassLoader().getResource("view/FXML_view.fxml");
			
			root = new FXMLLoader(thisURL).load();
			
			Scene myScene = new Scene(root);
			
			primaryStage.setTitle("Data game");
			
			primaryStage.setScene(myScene);
			primaryStage.setResizable(false);
			primaryStage.show();
			
			}catch(Exception e) {
				e.printStackTrace();
			}
		
	}

}
