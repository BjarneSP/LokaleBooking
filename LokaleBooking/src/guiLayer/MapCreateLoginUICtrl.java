package guiLayer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class MapCreateLoginUICtrl implements Initializable {
	
	@FXML
	private AnchorPane mapLoginPane;
	@FXML
	private TextField snTextField;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	private void goToMap(ActionEvent event)throws IOException{
		Parent pane = FXMLLoader.load(getClass().getResource("MapUI.fxml"));
		mapLoginPane.getChildren().setAll(pane);
	}
	@FXML
	private void confirmBtn(ActionEvent event)throws IOException{
//		UserCtrl uc = new UserCtrl(); 
//		if(uc.studentLogin(snTextField.getText())){
		System.out.println("Succes");
		Parent pane = FXMLLoader.load(getClass().getResource("MapUI.fxml"));
		mapLoginPane.getChildren().setAll(pane);
		}
	}
//}