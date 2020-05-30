package it.unicam.cs.pa.jbudget097670;

import java.text.*;
import java.util.Calendar;
import java.util.Date;

public class DateController {
	public static Date getDate() {
		Date today = Calendar.getInstance().getTime();
		return today;
	}
	
	public static Date getFinalDate() {
	Calendar cal = Calendar.getInstance(); 
	cal.add(Calendar.MONTH, (int) InputController.inputInt(""));
	Date finalDate = cal.getTime();
	return null;
	}

	
}
