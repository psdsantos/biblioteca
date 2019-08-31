package controller;


import java.net.URL;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import model.dao.DaoFactory;
import model.dao.UserDAOJDBC;
import model.db.DbException;
import model.entities.User;
import model.util.Alerts;
import model.util.ConferirDados;
import model.util.Constraints;

public class FXMLController_user implements Initializable{

	@FXML
	TextField dropID;
	
	@FXML
	Button dropButton;
	
	@FXML
	Button updateButton;
	
	@FXML 
	TextField updateNameField;
	
	@FXML
	TextField updateCPFField;
	
	@FXML
	PasswordField updatePasswordField;
	
	@FXML 
	CheckBox updateSuperUserCK;
	
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
	private TextField nameField;
	
	@FXML
	private TextField CPFField;
	
	@FXML
	private PasswordField passwordField;
	
	@FXML 
	private Button registerButton;
	
	@FXML
	private CheckBox ckSuperUser;
	
	@FXML
	private TextField searchUser;
	
	private UserDAOJDBC userDAO = DaoFactory.createUserDaojdbc();;
	
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
	
	public User findInTableView() {
		Integer id = tbView.getSelectionModel().getSelectedItem().getID();
		User userBD = userDAO.findByID(id);
		
		return userBD;
	}
	
	public User onMouseClicked() {
		User userBD = findInTableView();
		
		updateCPFField.setText(userBD.getCpf());
		updateNameField.setText(userBD.getName());
		updatePasswordField.setText(userBD.getPassword());
		updateSuperUserCK.setSelected(userBD.isSuperUser());
		
		dropID.setText(userBD.getID().toString());
		
		return userBD;
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Constraints.setTextFieldMaxLength(CPFField, 14);
		Constraints.setTextFieldMaxLength(nameField, 40);
		Constraints.setTextFieldMaxLength(passwordField, 30);
		Constraints.setTextFieldMaxLength(searchUser, 40);
		Constraints.setTextFieldMaxLength(updateCPFField, 14);
		Constraints.setTextFieldMaxLength(updateNameField, 40);
		Constraints.setTextFieldMaxLength(updatePasswordField, 30);
		Constraints.setTextFieldMaxLength(dropID, 11);
		
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
	
	public void onDropButton() {
		User user = findInTableView();
		if(dropID.getText().equals("")) {
			Alerts.showAlert("Required field", "You have to digit some ID", AlertType.WARNING);
		}else {
			userDAO.delete(user.getID());
			attTableView();
		}
	}
	
	public void onSearchUser() {
		searchUser.setOnKeyPressed(evt -> {
			if(evt.getCode() == KeyCode.ENTER) {
	
				String userCPF = searchUser.getText();
				if(userCPF.equals("")) {
					Alerts.showAlert("Fill in all fields", "Required field cpf", AlertType.INFORMATION);
				}else {
					ObservableList<User> obs = FXCollections.observableArrayList();
					try {
						try {
							obs.add(userDAO.findByCPF(userCPF));
						} catch (DbException e) {
							// TODO Auto-generated catch block
								Alerts.showAlert("Not found", "User not found", AlertType.WARNING);
						}
						tbView.setItems(obs);

					} catch (SQLException e) {
						// TODO Auto-generated catch block
					
					
					}
					}
				
			}
		});
	
		
		
	
	
	
	}
	
	public void OnRegisterUser() {
		
	if(nameField.getText().equals("") || passwordField.getText().equals("") || CPFField.getText().equals("")) {
		Alerts.showAlert("Fill in all fields", "We need all this data", AlertType.WARNING);
	}else {
		try {
			if(!userDAO.findByCPF(CPFField.getText()).getCpf().equals(null)) {
			
				Alerts.showAlert("Fill in all fields", "CPF Already in use", AlertType.WARNING);
			}
			
		} catch (Exception e) {
			if(ConferirDados.conferirCPF(CPFField.getText()) == false){
				Alerts.showAlert("Fill in all fields", "Invalid CPF", AlertType.WARNING);
			}
			else {
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
	}
	
	public void onUpdateButton() {
		User user = new User();
		
		user = findInTableView();

		user.setCpf(updateCPFField.getText());
		user.setPassword(updatePasswordField.getText());
		user.setName(updateNameField.getText());
		user.setSuperUser(Boolean.parseBoolean(updateSuperUserCK.getText()));
		
		if(ConferirDados.conferirCPF(user.getCpf()) == false){
			Alerts.showAlert("Fill in all fields", "Invalid CPF", AlertType.WARNING);
		}else {
		
		userDAO.update(user);
		attTableView();
		Alerts.showAlert("Updated", "Updated with success", AlertType.INFORMATION);
		}
	}

}
