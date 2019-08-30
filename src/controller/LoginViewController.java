package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import model.util.Constraints;

public class LoginViewController implements Initializable{

	@FXML
	ImageView loginImage;
	
	@FXML
	PasswordField passwordField;
	
	@FXML
	Label passwordLabel;
	
	@FXML
	Hyperlink registerAction;
	
	@FXML
	Label accountQuestion;
	
	@FXML
	Label CPFLabel;

	@FXML
	ProgressIndicator ProgressIndicator;
	
	@FXML
	ImageView myCorpImg;
	
	@FXML
	Label sdsPedro;
	
	@FXML
	Label and;
	
	@FXML
	Label EddieSCJ;
	
	@FXML
	Button enterButton;
	
	@FXML
	TextField CPFField;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		Constraints.setTextFieldMaxLength(CPFField, 14);
		Constraints.setTextFieldMaxLength(passwordField, 30);
	}
	
	public void onEnterAction() {
		enterButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
			if(CPFField.getText().equalsIgnoreCase(null)){
				throw new RuntimeException("You didn't enter the login CPF");
			
			}
				
					
				
			}
		});
	}
	
	
	
}
