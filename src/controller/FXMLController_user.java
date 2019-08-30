package controller;

import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.dao.DaoFactory;
import model.dao.UserDAOJDBC;
import model.entities.User;

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
 
	
	public ObservableList<User> initList() {
		UserDAOJDBC userDAO = DaoFactory.createUserDaojdbc();
		ObservableList<User> obs = FXCollections.observableArrayList();
		
		List<User> list = userDAO.findAll();
		
		for (User user : list) {
			System.out.println(user.getName());
			obs.add(user);
		}
		return obs;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
	
	idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
	nameCOlumn.setCellValueFactory(new PropertyValueFactory<>("name"));
	cpfColumn.setCellValueFactory(new PropertyValueFactory<>("cpf"));
	booksColumn.setCellValueFactory(new PropertyValueFactory<>("borrowedBooksCount"));

	tbView.setItems(initList());
	
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

}
