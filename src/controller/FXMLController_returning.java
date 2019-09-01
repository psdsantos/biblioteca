package controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
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

public class FXMLController_returning implements Initializable{
	
	@FXML
	private AnchorPane returningPane;
	
	@FXML
	private TextField bookIDReturn;
	
	@FXML
	private TextField bookNameSearch;
	
	@FXML
	private TableView<Book> tbView;
	
	@FXML
	private TableColumn<Book, Integer> idColumn;
	
	@FXML 
	private  TableColumn<Book, String> nameColumn;
	
	private BookDAOJDBC bookDAOJDBC = DaoFactory.createBookDAOJDBC();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		tbView.setItems(initList());
	}
	
	private ObservableList<Book>  initList() {
		List<Book> list = bookDAOJDBC.findAll();
		ObservableList<Book> obs = FXCollections.observableArrayList();
		
		for (Book book : list) {
			if(!book.isStatus()) {
				obs.add(book);		
			}
		}
		return obs;
		
		
	}
	
	
	
	public void onConfirmButton() {
		UserDAOJDBC userDAOJDBC = DaoFactory.createUserDaojdbc();
		Book book = bookDAOJDBC.findByID(Integer.parseInt(bookIDReturn.getText()));
		User user = userDAOJDBC.findByID(book.getUser().getID());
		
		book.setStatus(true);
		user.rmBooks(book);
		
		userDAOJDBC.update(user);
		bookDAOJDBC.update(book);
		
		attTbView();
		
		Alerts.showAlert("Success", "Returned: "+book.getName()+" By: "+user.getName(), AlertType.INFORMATION);
		Main.tdl.speak("Returned: "+book.getName()+" By: "+user.getName());
		
	}
	
	public void onKeyPressed(){
		bookNameSearch.setOnKeyPressed(evt -> {
			if(evt.getCode() == KeyCode.ENTER) {
				ObservableList<Book> obs = FXCollections.observableArrayList();
				List<Book> list = bookDAOJDBC.findByName(bookNameSearch.getText());
				
				for (Book book : list) {
					if(!book.isStatus()) {
						obs.add(book);
					}
				}
				
				tbView.setItems(obs);
			}
		});
	}
	
	public void onMouseClicked(){
		Integer id = tbView.getSelectionModel().getSelectedItem().getId();
		bookIDReturn.setText(bookDAOJDBC.findByID(id).getId().toString());
		
	}
	
	private void attTbView() {
		List<Book> list = bookDAOJDBC.findAll();
		ObservableList<Book> obs = FXCollections.observableArrayList();
		
		for (Book book : list) {
			if(!book.isStatus()) {
				obs.add(book);		
			}
		}
	
		tbView.setItems(obs);
	}
	
	public AnchorPane loadThis() throws IOException {
		AnchorPane root = new AnchorPane();
		
		URL thisURL = this.getClass().getClassLoader().getResource("view/FXML_returningView.fxml");
		root = FXMLLoader.load(thisURL);
		return root;
	}
	
	
	@FXML
	private void loadView(ActionEvent event) {
		try {
			AnchorPane pane = new AnchorPane();
			FXMLController a = new FXMLController();
			returningPane.getChildren().setAll(a.loadThis());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

	

}
