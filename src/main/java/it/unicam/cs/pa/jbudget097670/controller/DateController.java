package it.unicam.cs.pa.jbudget097670.controller;

import java.text.*;
import java.util.Calendar;
import java.util.Date;

public class DateController {
	public static Date getDate() {
		Date today = Calendar.getInstance().getTime();
		return today;
	}
	
	public static Date getFinalDate(int mesi) {
	Calendar cal = Calendar.getInstance(); 
	cal.add(Calendar.MONTH, mesi);
	Date finalDate = cal.getTime();
	return finalDate;
	}
}
