package guiLayer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controlLayer.LoginCtrl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class BookingLoginUICtrl implements Initializable {
	
	@FXML
	private AnchorPane bookingLoginPane;
	@FXML
	private TextField snTextField;
	@FXML
	private Label messageLabel;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	private void goToMap(ActionEvent event)throws IOException{
		Parent pane = FXMLLoader.load(getClass().getResource("MapUI.fxml"));
		bookingLoginPane.getChildren().setAll(pane);
	}
	@FXML
	private void confirmBtn(ActionEvent event)throws IOException{
		if(LoginCtrl.studentLogin(Integer.valueOf(snTextField.getText()))){
		Parent pane = FXMLLoader.load(getClass().getResource("BookingUI.fxml"));
		bookingLoginPane.getChildren().setAll(pane);
		}
		else 
		messageLabel.setText("Forkert studienummer!");
		messageLabel.setTextFill(Color.RED);
	}
}