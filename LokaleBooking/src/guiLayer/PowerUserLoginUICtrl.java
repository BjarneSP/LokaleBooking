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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class PowerUserLoginUICtrl implements Initializable {
	
	@FXML
	private AnchorPane puPane;
	@FXML
	private TextField usernameTextField;
	@FXML
	private PasswordField pwPasswordField;
	@FXML
	private Label messageLabel;
	
	@FXML
	private Button cancelBtn;
	@FXML
	private Button confirmBtn;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	private void cancelBtnClicked(ActionEvent event)throws IOException{
		Parent pane = FXMLLoader.load(getClass().getResource("MapUI.fxml"));
		puPane.getChildren().setAll(pane);
	}
	@FXML
	private void confirmBtnClicked(ActionEvent event)throws IOException{
		if(LoginCtrl.powerUserLogin(usernameTextField.getText(), pwPasswordField.getText())){
		Parent pane = FXMLLoader.load(getClass().getResource("PowerUserUI.fxml"));
		puPane.getChildren().setAll(pane);
		}
		else 
		messageLabel.setTextFill(Color.RED);
		messageLabel.setText("Forkert username eller password!");
	}
}