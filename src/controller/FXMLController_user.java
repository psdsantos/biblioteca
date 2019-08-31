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
	
	public User onMouseClicked() {
		Integer id = tbView.getSelectionModel().getSelectedItem().getID();
		User userBD = userDAO.findByID(id);
		
		updateCPFField.setText(userBD.getCpf());
		updateNameField.setText(userBD.getName());
		updatePasswordField.setText(userBD.getPassword());
		updateSuperUserCK.setSelected(userBD.isSuperUser());
		
		
		return userBD;
		
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
	
	public void onUpdateButton() {
		User user = new User();
		
		user = onMouseClicked();
		
		user.setCpf(updateCPFField.getText());
		user.setPassword(updatePasswordField.getText());
		user.setName(updateNameField.getText());
		user.setSuperUser(Boolean.parseBoolean(updateSuperUserCK.getText()));
		userDAO.update(user);
	
	}

}
