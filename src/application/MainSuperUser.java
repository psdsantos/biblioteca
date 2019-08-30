package application;

import java.net.URL;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainSuperUser {

	public void startGUI() {
		try 
		{
			AnchorPane root;
			
			Stage primaryStage = new Stage();
			System.out.println("AAAAAAAA");
			URL thisURL = this.getClass().getClassLoader().getResource("view/FXML_view.fxml");
			
			root = new FXMLLoader(thisURL).load();
			
			Scene myScene = new Scene(root);
			
			primaryStage.setTitle("Data game");
			
			primaryStage.setScene(myScene);
			
			primaryStage.show();
			
			}catch(Exception e) {
				e.printStackTrace();
			}
		
	}

}
