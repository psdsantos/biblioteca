package controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main2;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import model.dao.BookDAOJDBC;
import model.dao.DaoFactory;
import model.dao.UserDAOJDBC;
import model.entities.Book;
import model.entities.User;
import model.util.Alerts;
import model.util.Constraints;
import voiceSpeak.Trying_Different_Languages;

public class FXMLController_lending implements Initializable{
	
	@FXML
	private AnchorPane lendingPane;
	
	@FXML
	TextField cpfField;
	
	@FXML
	TextField bookField;
	
	@FXML
	TextField searchUserField;
	
	@FXML
	TextField searchBookField;
	
	@FXML
	TableView<Book> tbBookView;
	
	@FXML
	TableView<User> tbUserView;
	
	@FXML
	TableColumn<User, String> cpfColumn;
	
	@FXML
	TableColumn<User, String> userNameColumn;
	
	@FXML
	TableColumn<User, Integer> idColumn;
	
	@FXML
	TableColumn<User, String> nameColumn;
	
	@FXML 
	Button confirmButton;
	
	private Boolean permission = LoginViewController.permission;
	
	private Trying_Different_Languages tdl = new Trying_Different_Languages();
	
	private BookDAOJDBC bookDAOJDBC = DaoFactory.createBookDAOJDBC();
	private UserDAOJDBC userDAOJDBC = DaoFactory.createUserDaojdbc();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Constraints.setTextFieldMaxLength(searchBookField, 40);
		Constraints.setTextFieldMaxLength(cpfField, 14);
		Constraints.setTextFieldMaxLength(searchUserField, 40);
		Constraints.setTextFieldMaxLength(bookField, 40);
		
		
		cpfColumn.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		userNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		attTbBookView();
		attTbUserView();
		
		
	}
	
	public AnchorPane loadThis() throws IOException {
		AnchorPane root = new AnchorPane();
		
		URL thisURL = this.getClass().getClassLoader().getResource("view/FXML_lendingView.fxml");
		root = FXMLLoader.load(thisURL);
		return root;
	}
	
	public void onSearchUserField() {
		searchUserField.setOnKeyPressed(evt -> {
			if(evt.getCode()==KeyCode.ENTER) {
				if(!searchUserField.getText().equalsIgnoreCase("")) {
				
					ObservableList<User> obs = FXCollections.observableArrayList();
					
					try {
						obs.add(userDAOJDBC.findByCPF(searchUserField.getText()));
					} catch (Exception e) {
						Alerts.showAlert("Not found", "User not found", AlertType.WARNING);
						tdl.speak("User not found, sorry sir");
						
					} 
					
					tbUserView.setItems(obs);
					
				}
			}
		});
	}
	
	public void onCancel() {
		if(permission) {
			tdl.speak("cancel");
		}
	}
	
	public void onConfirm() {
		if(permission) {
			tdl.speak("Confirm");
		}
	}
	
	public void onSearchCPF() {
		if(permission) {
			tdl.speak("Type the user cpf here");
		}
	}
	
	public void onSearchBook() {
		if(permission) {
			tdl.speak("Type the book name here");
		}
	}
	
	public void onMouse_BookClicked() {
		
		Book book = bookDAOJDBC.findByID(tbBookView.getSelectionModel().getSelectedItem().getId());
		
		
		bookField.setText(book.getName());
	}
	
	public void onMouse_UserClicked() {
		
		User user = userDAOJDBC.findByID(tbUserView.getSelectionModel().getSelectedItem().getID());

		cpfField.setText(user.getCpf());
		
	}
	
	
	
	public void onConfirmButton() {
		User user = userDAOJDBC.findByID(tbUserView.getSelectionModel().getSelectedItem().getID());
		Book book = bookDAOJDBC.findByID(tbBookView.getSelectionModel().getSelectedItem().getId());
		
		if(book.isStatus() && user.isStatus()) {
		user.addBooks(book);
		book.setUser(user);
		
		book.setStatus(false);
		
		
		userDAOJDBC.update(user);
		bookDAOJDBC.update(book);
		
		Alerts.showAlert("One book borrowed", "You took the "+book.getName(), AlertType.INFORMATION);
		tdl.speak(user.getName()+" took the: "+book.getName()+" book");
		
		}
		else {
			Alerts.showAlert("Already Borrowed", "This book "+book.getName()+" is already borrowed or you already have 3 books", AlertType.INFORMATION);
			tdl.speak("Already borrowed, try again in few days");
					
		}
		}
	
	public void onSearchBookField() {
		searchBookField.setOnKeyPressed(evt -> {
			if(evt.getCode()==KeyCode.ENTER) {
				if(!searchBookField.getText().equalsIgnoreCase("")) {
				
					ObservableList<Book> obs = FXCollections.observableArrayList();
					
					try {
						obs.addAll(bookDAOJDBC.findByName(searchBookField.getText()));
					} catch (Exception e) {
						Alerts.showAlert("Not found", "User not found", AlertType.WARNING);
						tdl.speak("User not found, sorry sir");
						
					} 
					
					tbBookView.setItems(obs);
					
				}
			}
		});
	}
	
	public void attTbUserView() {
		tbUserView.setItems(initUserList());
	}
	
	private ObservableList<User> initUserList() {
	
		List<User> list = userDAOJDBC.findAll();
		ObservableList<User> obs = FXCollections.observableArrayList();
		obs.addAll(list);
		return obs;
		
	}

	public void attTbBookView() {
		tbBookView.setItems(initBookList());
	
	}
	
	
	private ObservableList<Book> initBookList() {
		List<Book> list = bookDAOJDBC.findAll();
		ObservableList<Book> obs = FXCollections.observableArrayList();
		obs.addAll(list);
		return obs;
	}

	@FXML
	private void loadView(ActionEvent event) {
		try {
			AnchorPane pane = new AnchorPane();
			FXMLController a = new FXMLController();
			lendingPane.getChildren().setAll(a.loadThis());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
