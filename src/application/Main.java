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
import voiceSpeak.Trying_Different_Languages;

public class Main extends Application {

	ArrayList<String> acessos = new ArrayList<String>();
	
	public static Trying_Different_Languages tdl = new Trying_Different_Languages();

	private static Scene mainScene;
	
	private void helloG1() throws InterruptedException {
		tdl.speak("Hello G1 Group, My name is Arya and, I am preparing all for you. If you have some visual deficience. I am here to help you. Kisses. By Arya");
		Thread.sleep(14000);
	}
	
	private void initTXT() throws IOException {
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
			
			
			
	}
	
	@Override
	public void start(Stage primaryStage) throws IOException, InterruptedException {
		helloG1();
		initTXT();
		
		try {
			VBox root = new VBox();
			
			URL thisURL = this.getClass().getClassLoader().getResource("view/FXML_loginView.fxml");
			root = FXMLLoader.load(thisURL);
			
			
			mainScene = new Scene(root);
			
			primaryStage.setScene(mainScene);
			primaryStage.setResizable(false);
			primaryStage.show();
			tdl.speak("Welcome to the Solidarity Library System. Here you can take the maximum of three bookswithout return date. but. to take,  more you have to, return one book ");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			Alerts.showAlert("Error to start app", e.getMessage(), AlertType.ERROR);
			tdl.speak("Error in start app, sorry sir");
			
		}
		
	}


	public static Scene getMainScene() {
		return mainScene;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
