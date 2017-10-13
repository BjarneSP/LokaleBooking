package controlLayer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Timer;
import modelLayer.EnumRoomStatus;

/**
 * Timer klasse der planlægger notifikationer til TimeKeeper omkring dato og tid.
 * Bruges til at sætte deadlines for checkin, "rengøring" i gamle bookinger og 
 * opdatering af GUI til at vise korrekt ugedag.  
 * 
 * SOURCE: http://stackoverflow.com/questions/11361332/how-to-call-a-method-on-specific-time-in-java
 * 
 * @author Bjarne, Frederik, Kristoffer, Ramanan (Gruppe 2)
 * @version 0.1
 * @since 19-12-2016
 *  
 */

public class TimerSchedule {

	private DateFormat dateFormatter;		// Format af dato
	private String s;	
	private Timer timer1;
	private Timer timer2;
	private Timer timer3;
	private Timer timer4;

	
	public TimerSchedule(String deadline1, String deadline2, String deadline3, 
			String deadline4){
		
		dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DateTimeFormatter currentDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");	// Dato format
		LocalDate localDate = LocalDate.now();
		s = currentDate.format(localDate);		
		
		timer1 = new Timer();		// Timer for starten af firstSlice (checkin)
		timer2 = new Timer();		// Timer for slutningen af firstSlice (ryd op)
		timer3 = new Timer();		// Timer for starten af secondSlice (checkin)
		timer4 = new Timer();		// Timer for starten af secondSlice (ryd op)
		
		setTimers(deadline1, deadline2, deadline3, deadline4);
	}
	
	public void setTimers(String deadline1, String deadline2, String deadline3, 
			String deadline4) {
		try {
			Date firstDeadline = dateFormatter.parse(s + " " + deadline1);
			timer1.schedule(new TimeKeeper(EnumRoomStatus.FirstSlice), firstDeadline);

			Date secondDeadline = dateFormatter.parse(s + " " + deadline2);
			timer2.schedule(new TimeKeeper(EnumRoomStatus.FirstSlice), secondDeadline);

			Date thirdDeadline = dateFormatter.parse(s + " " + deadline3);
			timer3.schedule(new TimeKeeper(EnumRoomStatus.SecondSlice), thirdDeadline);
			
			Date fourthDeadline = dateFormatter.parse(s + " " + deadline4);
			timer4.schedule(new TimeKeeper(EnumRoomStatus.SecondSlice), fourthDeadline);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
