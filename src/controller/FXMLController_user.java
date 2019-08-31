package controller;


import java.net.URL;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.dao.DaoFactory;
import model.dao.UserDAOJDBC;
import model.entities.User;
import model.util.Alerts;

public class FXMLController_user implements Initializable{

	
	@FXML
	private AnchorPane userPane;
	
	@FXML
	private TableView<User> tbView;

	@FXML
	private TableColumn<User, Integer> idColumn;
	
	@FXML
	private TableColumn<User, String> nameCOlumn;
	
	@FXML
	private TableColumn<User, String> cpfColumn;
	
	@FXML
	private TableColumn<User, Integer> booksColumn ;
	
	@FXML 
	TextField nameField;
	
	@FXML
	TextField CPFField;
	
	@FXML
	PasswordField passwordField;
	
	@FXML 
	Button registerButton;
	
	@FXML
	CheckBox ckSuperUser;
	
	UserDAOJDBC userDAO = DaoFactory.createUserDaojdbc();;
	
	public ObservableList<User> initList() {

		ObservableList<User> obs = FXCollections.observableArrayList();
		
		List<User> list = this.userDAO.findAll();
		
		for (User user : list) {
			obs.add(user);
		}
		return obs;
	}

	public void attTableView() {		
		tbView.setItems(initList());
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
		nameCOlumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		cpfColumn.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		booksColumn.setCellValueFactory(new PropertyValueFactory<>("borrowedBooksCount"));

		attTableView();
		
	}
	
	@FXML
	private void loadView(ActionEvent event) {
		try {
			AnchorPane pane = new AnchorPane();
			pane = FXMLLoader.<AnchorPane>load(Paths.get("src/view/FXML_view.fxml").toUri().toURL());
			userPane.getChildren().setAll(pane);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void OnRegisterUser() {
	if(nameField.getText().equals(null) || passwordField.getText().equals(null ) || CPFField.getText().equals(null)) {
		Alerts.showAlert("Fill in all fields", "We need all this data", AlertType.WARNING);
	}else {
		User user = new User(nameField.getText(), CPFField.getText(), 0, true);
		user.setPassword(passwordField.getText());
		user.setSuperUser(Boolean.parseBoolean(ckSuperUser.getText()));
	
		System.out.println(user);
		
		userDAO.save(user);
		attTableView();
		Alerts.showAlert("Registered", "Registered with success", AlertType.INFORMATION);
	
	}
	}

}
