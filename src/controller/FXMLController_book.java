package controller;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import model.dao.BookDAOJDBC;
import model.dao.DaoFactory;
import model.entities.Book;
import model.util.Alerts;
import model.util.Constraints;
import voiceSpeak.Trying_Different_Languages;

public class FXMLController_book implements Initializable{
	
	private Boolean permission = LoginViewController.permission;
	
	@FXML
	private AnchorPane bookPane;

	@FXML
	private TextField updateField;
	
	@FXML
	private TextField deleteField;
	
	@FXML
	private TextField searchField;
	
	@FXML
	private TextField registerField;
	
	@FXML
	private Button registerButton;
	
	@FXML
	private Button updateButton;
	
	@FXML
	private Button deleteButton;
	
	@FXML
	TableView<Book> tbView;
	
	@FXML
	TableColumn<Book, Integer> idColumn;
	
	@FXML
	TableColumn<Book, String> nameColumn;
	
	private Trying_Different_Languages tdl = new Trying_Different_Languages();
	private BookDAOJDBC bookDAOJDBC = DaoFactory.createBookDAOJDBC();
	
	public void onDelete() {
		if(permission) {
			tdl.speak("delete");
		}
	}
	
	public void onSearchBook() {
		if(permission) {
			tdl.speak("Type the book name here");
		}
	}
	
	
	public void onCancel() {
		if(permission) {
			tdl.speak("cancel");
		}
	}
	
	public void onUpdate() {
		if(permission) {
			tdl.speak("Update info");
		}
	}
	
	public void onRegister() {
		if(permission) {
			tdl.speak("Register");
		}
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Constraints.setTextFieldMaxLength(deleteField, 30);
		Constraints.setTextFieldMaxLength(registerField, 30);
		Constraints.setTextFieldMaxLength(updateField, 30);
		Constraints.setTextFieldMaxLength(searchField, 30);
		
		
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		tbView.setItems(initTbView());
	}
	
	public ObservableList<Book> initTbView(){
		List<Book > list = bookDAOJDBC.findAll();
		ObservableList<Book> obs = FXCollections.observableArrayList();
		obs.addAll(list);
		return obs;
	}
	
	public AnchorPane loadThis() throws IOException {
		AnchorPane root = new AnchorPane();
		
		URL thisURL = this.getClass().getClassLoader().getResource("view/FXML_bookView.fxml");
		root = FXMLLoader.load(thisURL);
		return root;
	}
	
	
	@FXML
	private void loadView(ActionEvent event) {
		try {
			AnchorPane pane = new AnchorPane();
			FXMLController a = new FXMLController();
			bookPane.getChildren().setAll(a.loadThis());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Book findInTable() throws Exception {
		Integer id = tbView.getSelectionModel().getSelectedItem().getId();
		Book book = bookDAOJDBC.findByID(id);
		if(book==null) {

			tdl.speak("Book not found, sorry sir");
			throw new Exception("Book not found");
		}
		return book;
	}
	
	public void onKeyPressed() {
		searchField.setOnKeyPressed(evt -> {
			if(evt.getCode() == KeyCode.ENTER) {
				if(!searchField.getText().equals("")) {
					List<Book> books = bookDAOJDBC.findByName(searchField.getText());

						ObservableList<Book> obs = FXCollections.observableArrayList();
						obs.addAll(books);
						tbView.setItems(obs);
	
			}
				else {
					Alerts.showAlert("No name", "Enter name", AlertType.INFORMATION);
					tdl.speak("enter a name please");
								
				}
			}
		});
	}
	
	public void onMouseClick() {
		try {
			Book book = findInTable();
			updateField.setText(book.getName());
			deleteField.setText(book.getName());
		
			
		}catch(Exception e) {
			Alerts.showAlert("Not found", "Book not found", AlertType.WARNING);
			tdl.speak("Book not found, sorry sir");
			
			
		}
		
		
	}
	
	public void attTableView() {
		tbView.setItems(initTbView());
	}
	
	public void onUpdateButton()  {
		try{
		if(!updateField.getText().equals("")) {
			Book book = findInTable();
			book.setName(updateField.getText());
			bookDAOJDBC.update(book);
			attTableView();
			Alerts.showAlert("Updated with success", "YEAH", AlertType.INFORMATION);

			tdl.speak("UPDATED WITH SUCCESS. YEAHHH");
		}else {
			Alerts.showAlert("Name", "Enter a book name", AlertType.INFORMATION);

			tdl.speak("ENTER A BOOK NAME PLEASE");
		}
		}catch(Exception e) {
			Alerts.showAlert("Not found", "Book not found", AlertType.WARNING);

			tdl.speak("Book not found, sorry sir");
		}
		
	}
	public void onRegisterButton() {
		if(!registerField.getText().equals("")) {
			Book book = new Book(registerField.getText(), true);
			bookDAOJDBC.save(book);
			attTableView();
			Alerts.showAlert("Saved with success", "YEAH", AlertType.INFORMATION);
			tdl.speak("SAVED WITH SUCCESS. YEAHHH");
			
		}else {
			Alerts.showAlert("Name", "Enter a book name", AlertType.INFORMATION);
			tdl.speak("ENTER A BOOK NAME PLEASE");
						
		}
	}
	public void onDeleteButton() {
		try {
		Book book = findInTable();
		if(!deleteField.getText().equals("")) {
			bookDAOJDBC.delete(book.getId());
			attTableView();
		}else {
			Alerts.showAlert("Name", "Enter a book name", AlertType.INFORMATION);
			tdl.speak("Book not found, sorry sir");
							
		}
		}catch(Exception e) {
			Alerts.showAlert("Not found", "Book not found", AlertType.WARNING);
			tdl.speak("Book not found, sorry sir");
					
		}
	}
	
	
	
	
	
}
