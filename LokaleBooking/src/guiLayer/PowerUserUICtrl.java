package guiLayer;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import controlLayer.BookingCtrl;
import controlLayer.UserCtrl;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import modelLayer.Booking;
import modelLayer.BookingLine;
import modelLayer.PowerUser;
import modelLayer.Room;
import modelLayer.Student;

/**
 * Class "PowerUserUICtrl": Controller for the user interface of powerusers.
 * @author Bjarne, Frederik, Kristoffer, Ramanan (Gruppe 2)
 * @version 0.1
 * @since 19-12-2016
 */

public class PowerUserUICtrl implements Initializable {

	// relevant ctrl instantiation
	private UserCtrl uCtrl = new UserCtrl();
	private BookingCtrl bCtrl = new BookingCtrl();
	
	// List containing all the current bookinglines in the database
	private ArrayList<BookingLine> bookingLines = bCtrl.getAllBookingLines();
	
	// Observable list with input for the dropdown userTypeChoiceBox
	private ObservableList<String> userType = FXCollections.observableArrayList("Student", "PowerUser");
	
	// Regular expressions for input checking. 7 digit regex and email regex.  
	String digitPattern = "\\d{7}";
	String emailPattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";

	@FXML
	private TabPane tabPane;
	@FXML
	private Tab userTab;
	@FXML
	private AnchorPane userPane;
	@FXML
	private ChoiceBox<String> userTypeChoiceBox;
	@FXML
	private TextField emailTxtField;
	@FXML
	private TextField lastNameTxtField;
	@FXML
	private TextField firstNameTxtField;
	@FXML
	private Pane powerUserPane;
	@FXML
	private TextField puIDTxtField;
	@FXML
	private TextField puPassTxtField;
	@FXML
	private Pane studentPane;
	@FXML
	private TextField studentNoTxtField;
	@FXML
	private Tab institutionTab;
	@FXML
	private AnchorPane institutionPane;
	@FXML
	private Tab bookingsTab;
	@FXML
	private AnchorPane bookingPane;
	@FXML
	private Button createBtn;
	@FXML
	private Button deleteBtn;
	@FXML
	private Button updateBtn;
	@FXML 
	private TreeView<Booking> treeView;
	@FXML
	private TextField searchTxtField;
	@FXML
	private Button searchBtn;
	@FXML
	private Label errorLabel;
	@FXML
	private TableView<PowerUserBookings> bookingsTable;

	// innitialize method is run when the UI is loaded
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		bookingsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);	// resizing of caloumns
		bookingsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);		// multiple cells can be selected in table
		
		// Table columns where each column contains a String from the instances of PowerUserBookings
		TableColumn<PowerUserBookings, String> name = new TableColumn<>("Navn");
		name.prefWidthProperty().bind(bookingsTable.widthProperty().divide(6));
		TableColumn<PowerUserBookings, String> roomType = new TableColumn<>("Type af rum");
		roomType.prefWidthProperty().bind(bookingsTable.widthProperty().divide(6));
		TableColumn<PowerUserBookings, String> capacity = new TableColumn<>("Kapacitet");
		capacity.prefWidthProperty().bind(bookingsTable.widthProperty().divide(6));
		TableColumn<PowerUserBookings, String> weekDay = new TableColumn<>("Uge dag");
		weekDay.prefWidthProperty().bind(bookingsTable.widthProperty().divide(6));
		TableColumn<PowerUserBookings, String> tv = new TableColumn<>("Tv");
		tv.prefWidthProperty().bind(bookingsTable.widthProperty().divide(6));
		TableColumn<PowerUserBookings, String> projector = new TableColumn<>("Projector");
		projector.prefWidthProperty().bind(bookingsTable.widthProperty().divide(6));
		TableColumn<PowerUserBookings, String> roomId = new TableColumn<>("roomId");
		roomId.prefWidthProperty().bind(bookingsTable.widthProperty().divide(6));
		
		// Observable list containing table data
		final ObservableList<PowerUserBookings> data = FXCollections.observableArrayList();

		// Setting cell value factories for each column containing Strings from the PowerUserBookings class, with a defined observable value
		name.setCellValueFactory(new PropertyValueFactory<PowerUserBookings, String>("name"));
		roomType.setCellValueFactory(new PropertyValueFactory<PowerUserBookings, String>("rType"));
		capacity.setCellValueFactory(new PropertyValueFactory<PowerUserBookings, String>("capacity"));
		weekDay.setCellValueFactory(new PropertyValueFactory<PowerUserBookings, String>("weekDay"));
		tv.setCellValueFactory(new PropertyValueFactory<PowerUserBookings, String>("tv"));
		projector.setCellValueFactory(new PropertyValueFactory<PowerUserBookings, String>("projector"));
		roomId.setCellValueFactory(new PropertyValueFactory<PowerUserBookings, String>("roomId"));
		
		// Values from each bookingline are used to create instances of PowerUserBookings, which are added to the observable list data
		for (BookingLine bl : bookingLines) {
			Room r = bl.getLineRoom();
			data.add(new PowerUserBookings(r.getName(), String.valueOf(bl.getWeekDay()), String.valueOf(r.getCapacity()),
					String.valueOf(r.isTv()), String.valueOf(r.isProjector()), r.getrType().toString(), String.valueOf(bl.isCheckedIn()),
					String.valueOf(bl.getLineRoom().getRoomID())));
		}
		
		// Observable list data is set for the table and each column is added to the table
		bookingsTable.setItems(data);
		bookingsTable.getColumns().addAll(name, roomType, capacity, weekDay, tv, projector, roomId);
		
		// Buttons are set as disabled
		updateBtn.setDisable(true);
		deleteBtn.setDisable(true);

		tabPane.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) -> {
			if (oldValue == userTab) {
				firstNameTxtField.clear();
				lastNameTxtField.clear();
				emailTxtField.clear();
				studentNoTxtField.clear();
				puIDTxtField.clear();
				puPassTxtField.clear();
			} 
			else if (oldValue == institutionTab) {
				// unimplemented
			} 
			else if (oldValue == bookingsTab) {
				// unimplemented
			}	
			if(newValue == institutionTab){
				// unimplemented				
			}
		});

		// The observable list userType is set for the choicebox
		userTypeChoiceBox.setItems(userType);

		// Listener for the choicebox. Panes are hidden/shown according to the chosen value in the choicebow
		ChangeListener<String> changeListener = new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue.equals("Student")) {
					powerUserPane.setVisible(false);
					studentPane.setVisible(true);
				}
				if (newValue.equals("PowerUser")) {
					studentPane.setVisible(false);
					powerUserPane.setVisible(true);
				}
			}
		};
		// Listener added for the choicebox
		userTypeChoiceBox.getSelectionModel().selectedItemProperty().addListener(changeListener);
	}

	// Text input checking method for regular expressions 
	public boolean checkPattern(String input, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher m = pattern.matcher(input);
		if (m.matches()) {
			return true;
		}
		return false;
	}
	
	// Actionevent - getting user information from input text in textfield
	public void importUser(ActionEvent event){
		String user = searchTxtField.getText();
		if(checkPattern(user, digitPattern) && !uCtrl.selectStudent(Integer.valueOf(user)).equals(null)){
			Student s = uCtrl.selectStudent(Integer.valueOf(user));
			firstNameTxtField.setText(s.getFirstName());
			lastNameTxtField.setText(s.getLastName());
			emailTxtField.setText(s.getEmail());
			userTypeChoiceBox.getSelectionModel().select(0);
			studentNoTxtField.setText(searchTxtField.getText());
			updateBtn.setDisable(false);
			deleteBtn.setDisable(false);
		}
		else if(!uCtrl.selectPowerUser(user).equals(null)){
			PowerUser pu = uCtrl.selectPowerUser(user);
			firstNameTxtField.setText(pu.getFirstName());
			lastNameTxtField.setText(pu.getLastName());
			emailTxtField.setText(pu.getEmail());
			puPassTxtField.setText(pu.getPassword());
			userTypeChoiceBox.getSelectionModel().select(1);
			puIDTxtField.setText(searchTxtField.getText());
			updateBtn.setDisable(false);
			deleteBtn.setDisable(false);
		}
	}

	// Actionevent - updating user information from input text
	public void updateUser(ActionEvent event){
		if(userTypeChoiceBox.getSelectionModel().getSelectedIndex() == 0){
			uCtrl.updateStudent(Integer.valueOf(searchTxtField.getText()), firstNameTxtField.getText(), lastNameTxtField.getText(), emailTxtField.getText());
			searchTxtField.clear();
			firstNameTxtField.clear();
			lastNameTxtField.clear();
			emailTxtField.clear();
			studentNoTxtField.clear();
			userTypeChoiceBox.getSelectionModel().select(null);
		}
		else{
			uCtrl.updatePowerUser(searchTxtField.getText(), puPassTxtField.getText(), firstNameTxtField.getText(), lastNameTxtField.getText(), emailTxtField.getText());
			searchTxtField.clear();
			firstNameTxtField.clear();
			lastNameTxtField.clear();
			emailTxtField.clear();
			puPassTxtField.clear();
			puIDTxtField.clear();
			userTypeChoiceBox.getSelectionModel().select(null);
		}
	}
	
	// Actionevent - deleting user information from input text
	public void deleteUser(ActionEvent event){
		if(userTypeChoiceBox.getSelectionModel().getSelectedIndex() == 0){
			uCtrl.deleteStudent(Integer.valueOf(searchTxtField.getText()));
			searchTxtField.clear();
			firstNameTxtField.clear();
			lastNameTxtField.clear();
			emailTxtField.clear();
			studentNoTxtField.clear();
			userTypeChoiceBox.getSelectionModel().select(null);
		}
		else{
			uCtrl.deletePowerUser(searchTxtField.getText());
			searchTxtField.clear();
			firstNameTxtField.clear();
			lastNameTxtField.clear();
			emailTxtField.clear();
			puPassTxtField.clear();
			puIDTxtField.clear();
			userTypeChoiceBox.getSelectionModel().select(null);
		}
	}
	// Actionevent - creating user information from input text
	public void createUser(ActionEvent event){
		if(userTypeChoiceBox.getSelectionModel().getSelectedIndex() == 0 && checkPattern(studentNoTxtField.getText(), digitPattern)){
			uCtrl.createStudent(Integer.valueOf(studentNoTxtField.getText()), firstNameTxtField.getText(), lastNameTxtField.getText(), emailTxtField.getText());
			searchTxtField.clear();
			firstNameTxtField.clear();
			lastNameTxtField.clear();
			emailTxtField.clear();
			studentNoTxtField.clear();
			userTypeChoiceBox.getSelectionModel().select(null);
		}
		else{
			uCtrl.createPowerUser(firstNameTxtField.getText(), lastNameTxtField.getText(), emailTxtField.getText(), puIDTxtField.getText(), puPassTxtField.getText());
			searchTxtField.clear();
			firstNameTxtField.clear();
			lastNameTxtField.clear();
			emailTxtField.clear();
			puPassTxtField.clear();
			puIDTxtField.clear();
			userTypeChoiceBox.getSelectionModel().select(null);
		}
	}
}
