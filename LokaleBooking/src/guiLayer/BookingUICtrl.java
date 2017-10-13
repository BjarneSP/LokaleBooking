package guiLayer;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import controlLayer.BookingCtrl;
import controlLayer.IFObserver;
import controlLayer.LoginCtrl;
import controlLayer.TimeKeeper;
import databaseLayer.BookingDB;
import databaseLayer.IFBookingDB;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import modelLayer.Booking;
import modelLayer.BookingLine;
import modelLayer.EnumRoomStatus;
import modelLayer.EnumRoomType;
import modelLayer.EnumWeekDay;
import modelLayer.Room;
import modelLayer.Student;

public class BookingUICtrl implements Initializable{
	BookingCtrl bCtrl = new BookingCtrl();
	ArrayList<BookingLine> bookings = bCtrl.getBookingLinesCurrentUser();
	BookingLine currentBookingLine = null;
	int currentBookingId;
	
	Student s = (Student)LoginCtrl.getCurrentUser();

	@FXML
	private ListView<String> listView;
	@FXML
	private TableView<BookingLineRow> table;

	@FXML
	private Label currentRoomLbl;

	@FXML
	private Button checkInBtn;

	@FXML
	private Button deleteBtn;

	@FXML
	private Button finishBtn;

	@FXML
	private AnchorPane bookingPane;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		ArrayList<BookingLine> booking = bCtrl.getBookingLinesCurrentUser();

		table.setColumnResizePolicy(table.CONSTRAINED_RESIZE_POLICY);
		table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		TableColumn<BookingLineRow, String> name = new TableColumn<>("Navn");
		name.prefWidthProperty().bind(table.widthProperty().divide(6));

		TableColumn<BookingLineRow, String> roomType = new TableColumn<>("Type af rum");
		roomType.prefWidthProperty().bind(table.widthProperty().divide(6));

		TableColumn<BookingLineRow, String> capacity = new TableColumn<>("Kapacitet");
		capacity.prefWidthProperty().bind(table.widthProperty().divide(6));

		TableColumn<BookingLineRow, String> weekDay = new TableColumn<>("Ugedag");
		weekDay.prefWidthProperty().bind(table.widthProperty().divide(6));

		TableColumn<BookingLineRow, String> tv = new TableColumn<>("Tv");
		tv.prefWidthProperty().bind(table.widthProperty().divide(6));

		TableColumn<BookingLineRow, String> projector = new TableColumn<>("Projector");
		projector.prefWidthProperty().bind(table.widthProperty().divide(6));

		final ObservableList<BookingLineRow> data = FXCollections.observableArrayList();

		name.setCellValueFactory(new PropertyValueFactory<BookingLineRow, String>("name"));

		roomType.setCellValueFactory(new PropertyValueFactory<BookingLineRow, String>("rType"));

		capacity.setCellValueFactory(new PropertyValueFactory<BookingLineRow, String>("capacity"));

		weekDay.setCellValueFactory(new PropertyValueFactory<BookingLineRow, String>("weekDay"));

		tv.setCellValueFactory(new PropertyValueFactory<BookingLineRow, String>("tv"));

		projector.setCellValueFactory(new PropertyValueFactory<BookingLineRow, String>("projector"));

		for (BookingLine bl : bookings) {
			Room r = bl.getLineRoom();
			data.add(new BookingLineRow(r.getName(), String.valueOf(bl.getWeekDay()), String.valueOf(r.getCapacity()),
					String.valueOf(r.isTv()), String.valueOf(r.isProjector()), r.getrType().toString(),
					bl.getrStatus().toString(), String.valueOf(bl.isCheckedIn()),
					String.valueOf(bl.getLineRoom().getRoomID())));
		}

		table.setItems(data);

		table.getColumns().addAll(name, roomType, capacity, weekDay, tv, projector);

		// Current room

		for (BookingLine lr : bookings) {
			if (lr.getWeekDay().equals(TimeKeeper.getCurrentWDay()) && lr.getrStatus().equals(switchStatus(TimeKeeper.getCurrentSlice()))) {
				currentBookingLine = lr;
				currentRoomLbl.setText(lr.getLineRoom().getName() + " " + lr.getWeekDay() + " " + lr.getrStatus());
				if (lr.isCheckedIn()) {
					checkInBtn.setText("Check ud");
				} 
				else if (!lr.isCheckedIn()) {
					checkInBtn.setText("Check ind");
				}
			}
		}
		if(currentRoomLbl.getText().isEmpty()) {
			checkInBtn.setDisable(true);
			} 
	}
	

	@FXML
	public void checkInBtnClicked(ActionEvent event) {
		if (checkInBtn.getText().equals("Check ind") && bCtrl.checkIn(currentBookingLine)) {
			System.out.println("True");
			System.out.println(currentBookingLine.toString() + currentBookingId);
			checkInBtn.setText("Check ud");
		}

		else {
			bCtrl.checkOut(currentBookingLine.getWeekDay(), currentBookingLine.getrStatus(), s.getStudentNo());
			currentBookingLine = null;
			Parent pane;
			try {
				pane = FXMLLoader.load(getClass().getResource("BookingUI.fxml"));
				bookingPane.getChildren().setAll(pane);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@FXML
	void deleteBtnClicked(ActionEvent event) {
		ObservableList<BookingLineRow> blr = table.getSelectionModel().getSelectedItems();
		for (BookingLineRow bl : blr) {
			bCtrl.deleteBookingLine(EnumWeekDay.valueOf(bl.getWeekDay()),EnumRoomStatus.valueOf(bl.getTimeSlice()), s.getStudentNo());
		}

		Parent pane;
		try {
			pane = FXMLLoader.load(getClass().getResource("BookingUI.fxml"));
			bookingPane.getChildren().setAll(pane);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void finishBtnClicked(ActionEvent event) throws IOException {
		Parent pane = FXMLLoader.load(getClass().getResource("MapUI.fxml"));
		bookingPane.getChildren().setAll(pane);
	}
	
	public EnumRoomStatus switchStatus(EnumRoomStatus rStatus){
		if(rStatus.equals(EnumRoomStatus.Available)){
			return EnumRoomStatus.FirstSlice;
		}
		else if(rStatus.equals(EnumRoomStatus.FirstSlice)){
			return EnumRoomStatus.SecondSlice;
		}
		else if(rStatus.equals(EnumRoomStatus.SecondSlice)){
			return EnumRoomStatus.FullyBooked;
		}
		return null;
	}

	// @FXML
	// public void deleteBtnClicked(ActionEvent event) {
	// for (Booking bl : bookings) {
	// ArrayList<BookingLine> bookingLines = bl.getBookingLines();
	// for (BookingLine lr : bookingLines) {
	// if (lr.getWeekDay().equals(rowData)) {
	// bCtrl.deleteBookingLine(bl.getBookingID(), lr.getLineRoom().getRoomID(),
	// EnumWeekDay.valueOf(rowData));
	// }
	// }
	// }
	// }
}
