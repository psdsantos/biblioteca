package controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.MainSuperUser;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import model.dao.DaoFactory;
import model.dao.UserDAOJDBC;
import model.entities.User;
import model.util.Alerts;
import model.util.Constraints;
import voiceSpeak.Trying_Different_Languages;

public class LoginViewController implements Initializable {

	public static  Boolean permission = false;
	
	@FXML
	private CheckBox ckBox;
	
	@FXML
	private ImageView loginImage;

	@FXML
	private PasswordField passwordField;

	@FXML
	private Label passwordLabel;

	@FXML
	private 	Label CPFLabel;

	@FXML
	private ImageView myCorpImg;

	@FXML
	private Label sdsPedro;

	@FXML
	private Label and;

	@FXML
	private Label EddieSCJ;

	@FXML
	private Button enterButton;

	@FXML
	private TextField CPFField;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		Constraints.setTextFieldMaxLength(CPFField, 14);
		Constraints.setTextFieldMaxLength(passwordField, 30);

	}

	private Trying_Different_Languages tdl = new Trying_Different_Languages();
	
	public void onKeyPressed() {
		try {
		passwordField.setOnKeyPressed(evt -> {

			if (evt.getCode() == KeyCode.ENTER) {
				if (CPFField.getText().equalsIgnoreCase("") || passwordField.getText().equalsIgnoreCase("")) {
					Alerts.showAlert("Null field", "You didn't enter the login CPF or password", AlertType.INFORMATION);
					tdl.speak("You didn't enter the login CPF or password");
					
				} else {

					UserDAOJDBC userDAOJDBC = DaoFactory.createUserDaojdbc();

					User user=null;
					try {
						user = userDAOJDBC.findByCPF(CPFField.getText());
					} catch (Exception e) {
						// TODO Auto-generated catch block

							}
					if(user!= null) {
					if (user.getPassword().equals(passwordField.getText())) {
						if (user.isSuperUser()) {
							permission = ckBox.isSelected();
							Stage stage = (Stage) enterButton.getScene().getWindow();
							

							MainSuperUser mainSuperUser = new MainSuperUser();
							mainSuperUser.startGUI();
							stage.close();

						} else {
							Alerts.showAlert("Only for adminstration users", "Only adminstration users can login", AlertType.CONFIRMATION);
							tdl.speak("Only adminstration users can login, SO CURIOUS. WHAT DO YOU WANT HERE?");
						}

					} else {
						Alerts.showAlert("Incorrect data", "Incorrect cpf or password", AlertType.WARNING);
						tdl.speak("Incorrect CPF or Passsword");
					}
				}
				else {
					Alerts.showAlert("Not found", "Data not found", AlertType.WARNING);
					tdl.speak("Data not found");
				}

			}
			}
		});
		}catch(Exception e) {
			Alerts.showAlert("Configuring JVM", "Try again", AlertType.INFORMATION);
		}
	}

	public void onEnterAction() {
		enterButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				if (CPFField.getText().equalsIgnoreCase("") || passwordField.getText().equalsIgnoreCase("")) {
					Alerts.showAlert("Null field", "You didn't enter the login CPF or password", AlertType.INFORMATION);
					tdl.speak("You didn't enter the login CPF or password");
				} else {

					UserDAOJDBC userDAOJDBC = DaoFactory.createUserDaojdbc();

					User user=null;
					try {
						user = userDAOJDBC.findByCPF(CPFField.getText());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						Alerts.showAlert("Incorrect data", "Incorrect cpf or password", AlertType.WARNING);
						tdl.speak("Incorrect CPF or Passsword");
					}

					if (user.getPassword().equals(passwordField.getText())) {
						if (user.isSuperUser()) {

							permission = ckBox.isSelected();
							Stage stage = (Stage) enterButton.getScene().getWindow();
							

							MainSuperUser mainSuperUser = new MainSuperUser();
							mainSuperUser.startGUI();
							stage.close();
							}else {

								Alerts.showAlert("Only admins", "Only admins, normal users can't login", AlertType.WARNING);
								tdl.speak("Only admins, normal users can't login");
						}
						
					}else {
						Alerts.showAlert("Not found", "Data not found", AlertType.WARNING);
						tdl.speak("Data not found");
						
					}
				}
			}
		});
		
	}

}
