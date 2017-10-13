package guiLayer;

import controlLayer.BookingCtrl;
import controlLayer.InstituteCtrl;
import controlLayer.LoginCtrl;
import controlLayer.TimeKeeper;
import controlLayer.UserCtrl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

	@Override
	public void start(Stage stage)throws Exception {
		
		Parent root = FXMLLoader.load(getClass().getResource("CalendarUI.fxml"));
		Scene myScene = new Scene(root);
		stage.setTitle("BookingBuddy");
		stage.setScene(myScene);
		stage.show();
	}

	public static void main(String[] args) {
		
		InstituteCtrl iCtrl = new InstituteCtrl();
		UserCtrl uCtrl = new UserCtrl();
		BookingCtrl bCtrl = new BookingCtrl();
		LoginCtrl lCtrl = new LoginCtrl();
		
/////////////////////////////////////////////////
////////  HUSK AT KOMMENTER IND IGEN  //////////
///////////////////////////////////////////////		
		

		TimeKeeper tk = new TimeKeeper("23:32:00",
									   "23:32:10",
									   "23:32:20",
									   "23:32:30"
									   );
		tk.addObserver(bCtrl);

		launch(args);	
	}
}

