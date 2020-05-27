package it.unicam.cs.pa.jbudget097670;

import java.text.*;
import java.util.Calendar;
import java.util.Date;

public class DateController {
	public static Date getDate() {
		Date today = Calendar.getInstance().getTime();
		return today;
	}

	
}
