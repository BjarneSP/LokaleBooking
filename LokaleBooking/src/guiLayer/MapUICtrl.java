package guiLayer;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import controlLayer.BookingCtrl;
import controlLayer.InstituteCtrl;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import modelLayer.BookingLine;
import modelLayer.CompositeLine;
import modelLayer.Department;
import modelLayer.EnumRoomStatus;
import modelLayer.Room;

public class MapUICtrl implements Initializable {

	BookingCtrl bCtrl = new BookingCtrl();
	InstituteCtrl iCtrl = new InstituteCtrl();

	ArrayList<BookingLine> bookings = bCtrl.getBookingLinesCurrentUser();
	ArrayList<Department> composites;
	ArrayList<Circle> pane2Circles = new ArrayList<Circle>();
	ArrayList<Circle> pane1Circles = new ArrayList<Circle>();

	ObservableList<String> department = FXCollections.observableArrayList();
	
	@FXML
	private Rectangle square1;
	@FXML
	private Rectangle square2;
	@FXML
	private Rectangle square3;

	@FXML
	private Button puLoginBtn;

	@FXML
	private Button showCalendarBtn;
	@FXML
	private AnchorPane mapPane;
	@FXML
	private Button createBtn;
	@FXML
	private Button myBookingsBtn;
	@FXML
	private ChoiceBox<String> dropDown;

	// SubPane for UCN - Sofiendalsvej
	@FXML
	private Pane pane;
	@FXML
	private ImageView imageView;
	@FXML
	private Circle circle1;
	@FXML
	private Circle circle2;

	// SubPane for UCN - Hobrovej
	@FXML
	private Pane pane2;
	@FXML
	private ImageView imageView2;
	@FXML
	private Circle circle6;
	@FXML
	private Circle circle7;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		square1.setFill(Color.LIMEGREEN);
		square2.setFill(Color.YELLOW);
		square3.setFill(Color.RED);

		composites = iCtrl.getAllComposites();

		for (Department d : composites) { // Tilføj hver composite til en
											// oberservable list
			department.add(d.getName());
		}

		pane1Circles.add(circle1);
		pane1Circles.add(circle2);
		// pane1Circles.add(circle3);
		// pane1Circles.add(circle4);
		// pane1Circles.add(circle5);

		pane2Circles.add(circle6);
		pane2Circles.add(circle7);
		// pane2Circles.add(circle8);
		// pane2Circles.add(circle9);
		// pane2Circles.add(circle10);
		// pane2Circles.add(circle11);

		dropDown.setItems(department);// Tilføj observablelist til choicebox
		// dropDown.getSelectionModel().selectFirst(); // Sæt det første
		// element som valgt som start
		pane.setVisible(false);
		pane2.setVisible(false);

		ChangeListener<String> changeListener = new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue.equals("UCN")) {
					Image img = new Image("https://drive.google.com/uc?id=0Bx8LWftm6_wcLVhSRHF3bU5aYk0");
					pane2.setVisible(false);
					imageView.setImage(img);
					pane.setVisible(true);

					// hent alle rooms for department i array MED rooms inde i
					// sig selv
					ArrayList<Room> r = new ArrayList<Room>();
					for (CompositeLine cl : composites.get(0).getCl()) {
						r.add((Room) cl.getInstitution());
					}
					colorCircles(pane1Circles, r);
				}
				if (newValue.equals("AAU")) {
					Image img = new Image("https://drive.google.com/uc?id=0Bx8LWftm6_wcT296Wk9YMHlrckU");
					pane.setVisible(false);
					imageView2.setImage(img);
					pane2.setVisible(true);

					// hent alle rooms for department i array MED rooms inde i
					// sig selv
					ArrayList<Room> r = new ArrayList<Room>();
					for (CompositeLine cl : composites.get(1).getCl()) {
						r.add((Room) cl.getInstitution());
					}
					colorCircles(pane2Circles, r);
				}
			}
		};
		dropDown.getSelectionModel().selectedItemProperty().addListener(changeListener);

	}

	private void colorCircles(ArrayList<Circle> circles, ArrayList<Room> rooms) {
		int a = rooms.size();
		int i = 0;
		if (rooms.get(0).equals(null)) {
			System.out.println("awf");
		}
			for (Circle c : pane1Circles) {
				while (a  > 0) {
				if(bookings.get(i).getrStatus().equals(EnumRoomStatus.Available)){
//				if (rooms.get(i).getrStatus().equals(EnumRoomStatus.Available)) {
					c.setFill(Color.LIMEGREEN);
				} else if (bookings.get(i).getrStatus().equals(EnumRoomStatus.FullyBooked)) {
					c.setFill(Color.RED);
				} else {
					c.setFill(Color.YELLOW);
				}
				a--;
				i++;
			}
		}
	}

	@FXML
	private void goToCalendar(ActionEvent event) throws IOException {
		Parent pane = FXMLLoader.load(getClass().getResource("CalendarUI.fxml"));
		mapPane.getChildren().setAll(pane);
	}

	@FXML
	private void goToMyBookings(ActionEvent event) throws IOException {
		Parent pane = FXMLLoader.load(getClass().getResource("BookingLoginUI.fxml"));
		mapPane.getChildren().setAll(pane);
	}

	@FXML
	private void mapCreateBtn(ActionEvent event) throws IOException {
		Parent pane = FXMLLoader.load(getClass().getResource("MapCreateLoginUI.fxml"));
		mapPane.getChildren().setAll(pane);
	}
}
