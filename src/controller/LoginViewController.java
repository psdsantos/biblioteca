package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mysql.cj.x.protobuf.MysqlxExpect.Open.Condition.Key;

import application.MainSuperUser;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.dao.DaoFactory;
import model.dao.UserDAOJDBC;
import model.db.DbException;
import model.entities.User;
import model.util.Alerts;
import model.util.Constraints;

public class LoginViewController implements Initializable {

	@FXML
	ImageView loginImage;

	@FXML
	PasswordField passwordField;

	@FXML
	Label passwordLabel;

	@FXML
	Label CPFLabel;

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

	public void onKeyPressed() {
		try {
		passwordField.setOnKeyPressed(evt -> {

			if (evt.getCode() == KeyCode.ENTER) {
				if (CPFField.getText().equalsIgnoreCase("") || passwordField.getText().equalsIgnoreCase("")) {
					Alerts.showAlert("Null field", "You didn't enter the login CPF or password", AlertType.INFORMATION);
				} else {

					UserDAOJDBC userDAOJDBC = DaoFactory.createUserDaojdbc();

					User user=null;
					try {
						user = userDAOJDBC.findByCPF(CPFField.getText());
					} catch (Exception e) {
						// TODO Auto-generated catch block

						Alerts.showAlert("Incorrect data", "Incorrect cpf or password", AlertType.WARNING);
					}
					if(user!= null) {
					if (user.getPassword().equals(passwordField.getText())) {
						if (user.isSuperUser()) {

							Stage stage = (Stage) enterButton.getScene().getWindow();
							

							MainSuperUser mainSuperUser = new MainSuperUser();
							mainSuperUser.startGUI();
							stage.close();

						} else {

						}

					} else {
						Alerts.showAlert("Incorrect data", "Incorrect cpf or password", AlertType.WARNING);
					}
				}
				else {
					Alerts.showAlert("Incorrect data", "Incorrect cpf or password", AlertType.WARNING);
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
				} else {

					UserDAOJDBC userDAOJDBC = DaoFactory.createUserDaojdbc();

					User user=null;
					try {
						user = userDAOJDBC.findByCPF(CPFField.getText());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						Alerts.showAlert("Incorrect data", "Incorrect cpf or password", AlertType.WARNING);
					}

					if (user.getPassword().equals(passwordField.getText())) {
						if (user.isSuperUser()) {

							Stage stage = (Stage) enterButton.getScene().getWindow();
							

							MainSuperUser mainSuperUser = new MainSuperUser();
							mainSuperUser.startGUI();
							stage.close();
							}else {
							
						}
						
					}else {
						Alerts.showAlert("Incorrect data", "Incorrect cpf or password", AlertType.WARNING);
					}
				}
			}
		});
		
	}

}
