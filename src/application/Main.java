package application;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.db.DB;
import model.util.Alerts;

public class Main extends Application {

	ArrayList<String> acessos = new ArrayList<String>();
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		
		try {
		Scanner leitor = new Scanner(new FileReader("acessos.txt"));
		while(leitor.hasNext()) {
			String linha = leitor.nextLine();
			acessos.add(linha);
		}
		leitor.close();
		}catch(Exception e) {
		PrintWriter gravador = new PrintWriter(new FileWriter("acessos.txt"));
		gravador.close();
		}
		
		acessos.add(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()));
		PrintWriter gravador = new PrintWriter(new FileWriter("acessos.txt"));
		for (int i = 0; i < acessos.size(); i++) {
			gravador.println(acessos.get(i));
		}
		gravador.close();
		
		
		//User user = new User("Pedro Silva Dos Santos", "061.615.995-11", 0, true);
		//user.setSuperUser(true);
		//user.setPassword("pedrosds");
		//System.out.println(user);
		
		//UserDAOJDBC userDAOJDBC = DaoFactory.createUserDaojdbc();
		//userDAOJDBC.save(user);
		
		try {
			DB DB = new DB();
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
