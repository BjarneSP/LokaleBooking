package guiLayer;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import controlLayer.BookingCtrl;
import controlLayer.InstituteCtrl;
import controlLayer.LoginCtrl;
import controlLayer.UserCtrl;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import modelLayer.Booking;
import modelLayer.BookingLine;
import modelLayer.CompositeLine;
import modelLayer.Department;
import modelLayer.EnumRoomStatus;
import modelLayer.EnumWeekDay;
import modelLayer.Room;
import modelLayer.Student;

public class CalendarUICtrl implements Initializable {
	//IFBookingDB bDB = new BookingDB();
	InstituteCtrl iCtrl = new InstituteCtrl();
	BookingCtrl bCtrl = new BookingCtrl();
//	UserCtrl uCtrl = new UserCtrl();

	// ChoiceBox
	ObservableList<String> departmentsOb = FXCollections.observableArrayList();
	ArrayList<Department> departments = iCtrl.getAllComposites();
	ArrayList<String> departmentNames = new ArrayList<String>();
	// Table
	ObservableList<RoomRow> roomRows = FXCollections.observableArrayList();
	ArrayList<BookingLine> bookingLines = bCtrl.getAllBookingLines();
	ArrayList<Room> rooms = iCtrl.getAllRooms();

	///////// test /////////
	// ArrayList<BookingLine> bookingLines = new ArrayList<>();
	// ArrayList<Room> rooms = new ArrayList<>();
	//
	// Room r = new Room(1,"hej",1,true,true,EnumRoomType.Auditorium);
	// Room r2 = new Room(1,"hej",1,true,true,EnumRoomType.Auditorium);
	//
	// BookingLine bl = new
	// BookingLine(EnumWeekDay.Fredag,r,false,EnumRoomStatus.FirstSlice);
	// BookingLine bl2 = new
	// BookingLine(EnumWeekDay.Mandag,r,false,EnumRoomStatus.FirstSlice);
	///////// test /////////

	@FXML
	private AnchorPane calendarPane;
	@FXML
	private Button showMapBtn;
	@FXML
	private Button createBtn;
	@FXML
	private ChoiceBox<String> dDropDown;
	@FXML
	private TableView<RoomRow> calendarTable;
	@FXML
	private TextField studenNoField;
	@FXML
	private Label studentNoErrorLabel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

//		studentNoErrorLabel.setDisable(true);
		studentNoErrorLabel.setVisible(false);

		for (Department d : departments) {
			departmentsOb.add(d.getName());
			departmentNames.add(d.getName());
		}

		dDropDown.setItems(departmentsOb);

		ChangeListener<String> changeListener = new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue.equals(dDropDown.getSelectionModel().getSelectedIndex())) {
					Department depa = departments.get(dDropDown.getSelectionModel().getSelectedIndex());
					for (CompositeLine cl : depa.getCl()) {
						Room room = (Room) cl.getInstitution();
					}
				}
			}
		};
		dDropDown.getSelectionModel().selectedItemProperty().addListener(changeListener);
		
		
		calendarTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		calendarTable.getSelectionModel().setCellSelectionEnabled(true);
		calendarTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	
		TableColumn<RoomRow, String> room = new TableColumn<>("Lokale");
		room.setCellValueFactory(cellData -> cellData.getValue().roomProperty());

		TableColumn<RoomRow, String> monday = new TableColumn<>("Mandag");
		TableColumn<RoomRow, String> firslice1 = new TableColumn<>("08-12");
		TableColumn<RoomRow, String> secondSlice1 = new TableColumn<>("12-16");
		monday.getColumns().addAll(firslice1, secondSlice1);
		firslice1.setCellValueFactory(cellData -> cellData.getValue().monday1Property());
		secondSlice1.setCellValueFactory(cellData -> cellData.getValue().monday1Property());

		TableColumn<RoomRow, String> tuesday = new TableColumn<>("Tirsdag");
		// tuesday.prefWidthProperty().bind(calendarTable.widthProperty().divide(6));
		TableColumn<RoomRow, String> firslice2 = new TableColumn<>("08-12");
		TableColumn<RoomRow, String> secondSlice2 = new TableColumn<>("12-16");
		tuesday.getColumns().addAll(firslice2, secondSlice2);
		tuesday.setCellValueFactory(cellData -> cellData.getValue().roomProperty());

		TableColumn<RoomRow, String> wednesday = new TableColumn<>("Onsdag");
		// wednesday.prefWidthProperty().bind(calendarTable.widthProperty().divide(6));
		TableColumn<RoomRow, String> firslice3 = new TableColumn<>("08-12");
		TableColumn<RoomRow, String> secondSlice3 = new TableColumn<>("12-16");
		wednesday.getColumns().addAll(firslice3, secondSlice3);
		wednesday.setCellValueFactory(cellData -> cellData.getValue().roomProperty());

		TableColumn<RoomRow, String> thursday = new TableColumn<>("Torsdag");
		// thursday.prefWidthProperty().bind(calendarTable.widthProperty().divide(6));
		TableColumn<RoomRow, String> firslice4 = new TableColumn<>("08-12");
		TableColumn<RoomRow, String> secondSlice4 = new TableColumn<>("12-16");
		thursday.getColumns().addAll(firslice4, secondSlice4);
		thursday.setCellValueFactory(cellData -> cellData.getValue().roomProperty());

		TableColumn<RoomRow, String> friday = new TableColumn<>("Fredag");
		// friday.prefWidthProperty().bind(calendarTable.widthProperty().divide(6));
		TableColumn<RoomRow, String> firslice5 = new TableColumn<>("08-12");
		TableColumn<RoomRow, String> secondSlice5 = new TableColumn<>("12-16");
		friday.getColumns().addAll(firslice5, secondSlice5);
		friday.setCellValueFactory(cellData -> cellData.getValue().roomProperty()); 

		room.setCellValueFactory(new PropertyValueFactory<RoomRow, String>("room"));
		firslice1.setCellValueFactory(new PropertyValueFactory<RoomRow, String>("monday1"));
		secondSlice1.setCellValueFactory(new PropertyValueFactory<RoomRow, String>("monday2"));
		firslice2.setCellValueFactory(new PropertyValueFactory<RoomRow, String>("tuesday1"));
		secondSlice2.setCellValueFactory(new PropertyValueFactory<RoomRow, String>("tuesday2"));
		firslice3.setCellValueFactory(new PropertyValueFactory<RoomRow, String>("wednesday1"));
		secondSlice3.setCellValueFactory(new PropertyValueFactory<RoomRow, String>("wednesday2"));
		firslice4.setCellValueFactory(new PropertyValueFactory<RoomRow, String>("thursday1"));
		secondSlice4.setCellValueFactory(new PropertyValueFactory<RoomRow, String>("thursday2"));
		firslice5.setCellValueFactory(new PropertyValueFactory<RoomRow, String>("friday1"));
		secondSlice5.setCellValueFactory(new PropertyValueFactory<RoomRow, String>("friday2"));

		for (Room r : rooms) {
			roomRows.add(new RoomRow(r));
		}

		for (RoomRow rr : roomRows) {
			for (BookingLine bl : bookingLines) {
				if (rr.getR().getRoomID() == bl.getLineRoom().getRoomID()) {
					setDayTime(roomRows.indexOf(rr), bl.getrStatus(), bl.getWeekDay());
				}
			}
		}


		// // Cell factory - Sætte farve på cellebaggrund. Virker ikke fordi
		// baggrunden overskygger en selection
		
		// firslice1.setCellFactory(column -> {
		// return new TableCell<RoomRow, String>() {
		// @Override
		// protected void updateItem(String item, boolean empty) {
		// if (item == null || empty) {
		// setText(null);
		// setStyle("-fx-background-color: green; -fx-selection-bar: black;");
		//
		// } else {
		// // Format date.
		// setText(item);
		//
		// // Style all dates in March with a different color.
		// if (item.equals(" ")) {
		// setTextFill(Color.WHITE);
		// setStyle("-fx-background-color: rgb(255, 100, 100);
		// -fx-selection-bar: black;");
		//
		// } else {
		// setTextFill(Color.BLACK);
		//
		// }
		// }
		//
		// }
		// };
		// });
		

		
		calendarTable.setItems(roomRows);
		calendarTable.getColumns().addAll(room, monday, tuesday, wednesday, thursday, friday);
		calendarTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

	}

	@FXML
	private void goToMap(ActionEvent event) throws IOException {
		Parent pane = FXMLLoader.load(getClass().getResource("MapUI.fxml"));
		calendarPane.getChildren().setAll(pane);
	}

	@FXML
	private void createBtnClicked(ActionEvent event) throws IOException {
		

		TablePosition pos = calendarTable.getSelectionModel().getSelectedCells().get(0);
		RoomRow item = calendarTable.getItems().get(pos.getRow());
		Boolean loggedIn = LoginCtrl.studentLogin(Integer.valueOf(studenNoField.getText()));
				
		if((String) pos.getTableColumn().getCellObservableValue(item).getValue() == null && loggedIn){
			
			// "Parent" kolonnen på den valgte celle (weekday)
			EnumWeekDay selectedWDay = EnumWeekDay.valueOf(pos.getTableColumn().getParentColumn().getText());
			// "Child" kolonnen på den valgte celle (timeSlice) 
			EnumRoomStatus timeSlice = null;
			String subColumn = pos.getTableColumn().getText();
			if(subColumn.equals("08-12")){
				timeSlice = EnumRoomStatus.FirstSlice;
			}
			else if(subColumn.equals("12-16")){
				timeSlice = EnumRoomStatus.SecondSlice;
			}
			ArrayList<BookingLine> bls = new ArrayList<>();
			BookingLine bl = new BookingLine(selectedWDay,item.getR(),false,timeSlice);
			bls.add(bl);

			for(BookingLine bl2 : bls){
				System.out.println(bl2.toString());
			}
			bCtrl.createBooking(bls);
			
			Parent pane = FXMLLoader.load(getClass().getResource("CalendarUI.fxml"));
			calendarPane.getChildren().setAll(pane);

			
			// Statisk reference til BookingCtrl så vi kan bruge lines når der er logget ind
//			BookingCtrl.setCurrentBookingLines(bls);
		}
		else{
//			System.out.println("Er allerede booked (lav lige en label)");
			studentNoErrorLabel.setVisible(true);

		}		
		
//		studentNoErrorLabel.setDisable(true);
//		
//		if((checkPattern(studenNoField.getText(),digitPattern)) && !uCtrl.selectStudent(Integer.valueOf(studenNoField.getText())).equals(null)){
//			ObservableList<RoomRow> blr = calendarTable.getSelectionModel().getSelectedItems();
////			calendarTable.getSelectionModel().getse
//			
//			ArrayList<BookingLine> bl = new ArrayList<>();
//			Student s = uCtrl.selectStudent(Integer.valueOf(studenNoField.getText()));
//			LoginCtrl.setCurrentUser(s);
//			
//			for (RoomRow rr : blr) {
//				Room r = new Room(rr.getRoomId(), rr.getName(), rr.getCapacity(), rr.isTv(), rr.isProjector(),rr.getrType());
//				BookingLine bls = new BookingLine(rr.getWeekDays(), r, false, rr.getrStatus());
//				bl.add(bls);
//			}	
//			bCtrl.createBooking(bl);
//			Parent pane = FXMLLoader.load(getClass().getResource("MapUI.fxml"));
//			calendarPane.getChildren().setAll(pane);
//		}
//		else{
//			studentNoErrorLabel.setDisable(false);
//		}	
	}
	
//	public boolean checkPattern(String input, String regex) {
//		Pattern pattern = Pattern.compile(regex);
//		Matcher m = pattern.matcher(input);
//		if (m.matches()) {
//			return true;
//		}
//		return false;
//	}

	private void setDayTime(int index, EnumRoomStatus rStatus, EnumWeekDay weekDays){
		if (weekDays.equals(EnumWeekDay.Mandag)) {
			if (rStatus.equals(EnumRoomStatus.FirstSlice)) {
				roomRows.get(index).setMonday1("Booked");
			} else if (rStatus.equals(EnumRoomStatus.SecondSlice)) {
				roomRows.get(index).setMonday2("Booked");
			} else if (rStatus.equals(EnumRoomStatus.FullyBooked)) {
				roomRows.get(index).setMonday1("Booked");
				roomRows.get(index).setMonday2("Booked");
			}
		} 
		else if (weekDays.equals(EnumWeekDay.Tirsdag)) {
			if (rStatus.equals(EnumRoomStatus.FirstSlice)) {
				roomRows.get(index).setTuesday1("Booked");
			} else if (rStatus.equals(EnumRoomStatus.SecondSlice)) {
				roomRows.get(index).setTuesday2("Booked");
			} else if (rStatus.equals(EnumRoomStatus.FullyBooked)) {
				roomRows.get(index).setTuesday1("Booked");
				roomRows.get(index).setTuesday2("Booked");
			} 
		}
		else if (weekDays.equals(EnumWeekDay.Onsdag)) {
			if (rStatus.equals(EnumRoomStatus.FirstSlice)) {
				roomRows.get(index).setWednesday1("Booked");
			} else if (rStatus.equals(EnumRoomStatus.SecondSlice)) {
				roomRows.get(index).setWednesday2("Booked");
			} else if (rStatus.equals(EnumRoomStatus.FullyBooked)) {
				roomRows.get(index).setWednesday1("Booked");
				roomRows.get(index).setWednesday2("Booked");
			}
		} 
		else if (weekDays.equals(EnumWeekDay.Torsdag)) {
			if (rStatus.equals(EnumRoomStatus.FirstSlice)) {
				roomRows.get(index).setThursday1("Booked");
			} else if (rStatus.equals(EnumRoomStatus.SecondSlice)) {
				roomRows.get(index).setThursday2("Booked ");
			} else if (rStatus.equals(EnumRoomStatus.FullyBooked)) {
				roomRows.get(index).setThursday1("Booked");
				roomRows.get(index).setThursday2("Booked");
			}
		} 
		else if (weekDays.equals(EnumWeekDay.Fredag)) {
			if (rStatus.equals(EnumRoomStatus.FirstSlice)) {
				roomRows.get(index).setFriday1("Booked");
			} else if (rStatus.equals(EnumRoomStatus.SecondSlice)) {
				roomRows.get(index).setFriday2("Booked");
			} else if (rStatus.equals(EnumRoomStatus.FullyBooked)) {
				roomRows.get(index).setFriday1("Booked");
				roomRows.get(index).setFriday2("Booked");
			}
		}
	}
}
