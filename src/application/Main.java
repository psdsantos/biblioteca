package application;

import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.dao.BookDAOJDBC;
import model.dao.DaoFactory;
import model.dao.UserDAOJDBC;
import model.db.DB;
import model.entities.Book;
import model.entities.User;
import model.util.Alerts;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
			
		//User user = new User("Pedro Silva Dos Santos", "061.615.995-11", 0, true);
		//user.setSuperUser(true);
		//user.setPassword("pedrosds");
		//System.out.println(user);
		
		//UserDAOJDBC userDAOJDBC = DaoFactory.createUserDaojdbc();
		//userDAOJDBC.save(user);
		
		try {
			DB.getConnection();
			VBox root = new VBox();
			
			URL thisURL = this.getClass().getClassLoader().getResource("view/FXML_loginView.fxml");
			root = FXMLLoader.load(thisURL);
			
			
			Scene scene = new Scene(root);
			
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			Alerts.showAlert("Error to start app", e.getMessage(), AlertType.ERROR);
		}
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
