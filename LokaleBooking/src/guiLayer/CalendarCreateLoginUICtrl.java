package guiLayer;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import modelLayer.BookingLine;

public class CalendarCreateLoginUICtrl implements Initializable {
	
	@FXML
	private AnchorPane calendarLoginPane;
	@FXML
	private TextField snTextField;

	private ArrayList<BookingLine> bls = new ArrayList<>();  
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	private void goToCalendar(ActionEvent event)throws IOException{
		Parent pane = FXMLLoader.load(getClass().getResource("CalendarUI.fxml"));
		calendarLoginPane.getChildren().setAll(pane);
	}
	@FXML
	private void confirmBtn(ActionEvent event)throws IOException{
//		UserCtrl uc = new UserCtrl(); 
//		if(uc.studentLogin(snTextField.getText())){
		System.out.println("Succes");
		Parent pane = FXMLLoader.load(getClass().getResource("MapUI.fxml"));
		calendarLoginPane.getChildren().setAll(pane);
		}

	public ArrayList<BookingLine> getBls() {
		return bls;
	}

	public void setBls(ArrayList<BookingLine> bls) {
		this.bls = bls;
	}	
}
