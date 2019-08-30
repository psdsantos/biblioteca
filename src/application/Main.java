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
import model.entities.Book;
import model.util.Alerts;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
	BookDAOJDBC bookDAOJDBC = DaoFactory.createBookDAOJDBC();
		
		List<Book> book = new ArrayList<Book>();
		book = bookDAOJDBC.findByID_Name(new Book("Sun Dreams", true));
		System.out.println(book.get(0).getName());
		
			
		try {
			
			VBox root = new VBox();
			
			
			URL fxmlUrl;
			fxmlUrl = Paths.get("src/view/FXML_loginView.fxml").toUri().toURL();
			root = FXMLLoader.<VBox>load(fxmlUrl);
			
			
			Scene scene = new Scene(root);
			
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (Exception e) {
			Alerts.showAlert("Error to start app", e.getMessage(), AlertType.ERROR);
		}
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
