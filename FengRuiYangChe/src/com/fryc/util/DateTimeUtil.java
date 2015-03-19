package com.fryc.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtil {

	public static String convertTimestamp(Timestamp timestamp, String pattern) {
		String result = null;
		if (timestamp != null) {
			if (pattern != null) {
				SimpleDateFormat formatter = new SimpleDateFormat(pattern);
				result = formatter.format(timestamp);
			} else {
				SimpleDateFormat formatter = new SimpleDateFormat(AppConstant.PATTERN_MDYHMS);
				result = formatter.format(timestamp);
			}
		}
		return result;
	}
	
	public static String convertDate(Date date, String pattern) {
		String result = null;
		if (date != null && pattern != null) {
			SimpleDateFormat formatter = new SimpleDateFormat(pattern);
			result = formatter.format(date);
		}
		return result;
	}
	
	public static int getGapAsDay(Timestamp earlyTime, Timestamp laterTime) {
		int result = -1;
		if (earlyTime != null && laterTime != null) {
			Calendar early = Calendar.getInstance();
			early.setTimeInMillis(earlyTime.getTime());
			
			Calendar later = Calendar.getInstance();
			later.setTimeInMillis(laterTime.getTime());
			
			if (later.after(early)) {
				int earlyDay = early.get(Calendar.DAY_OF_YEAR);
				int laterDay = later.get(Calendar.DAY_OF_YEAR);
				result = laterDay - earlyDay;
			}
		}
		return result;
	}
	
	public static Date getNextWeekStart(Date dateAsRef) {
		Calendar c = Calendar.getInstance();
		c.setTime(dateAsRef);
		
		int firstDayOfWeek = 1;
		int totalDaysOfWeek = 7;
		
		c.setFirstDayOfWeek(firstDayOfWeek);
		
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		int dayOfYear = c.get(Calendar.DAY_OF_YEAR);
		int daysUtilNextWeek = totalDaysOfWeek - (dayOfWeek - 1) + 1;
		
		c.set(Calendar.DAY_OF_YEAR, dayOfYear + daysUtilNextWeek);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 1);
		
		return c.getTime();
	}
	
	public static Date getNextWeekEnd(Date dateAsRef) {
		Calendar c = Calendar.getInstance();
		c.setTime(dateAsRef);
		
		int firstDayOfWeek = 1;
		int totalDaysOfWeek = 7;
		
		c.setFirstDayOfWeek(firstDayOfWeek);
		
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		int dayOfYear = c.get(Calendar.DAY_OF_YEAR);
		int daysUtilNextWeek = totalDaysOfWeek - (dayOfWeek - 1) + 1;
		
		c.set(Calendar.DAY_OF_YEAR, dayOfYear + daysUtilNextWeek + 6);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		
		return c.getTime();
	}
	
	public static boolean isLeapYear(Date date) {
		boolean isLeapYear = false;
		
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		
		int year = c.get(Calendar.YEAR);
		if (year > 0) {
			if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
				isLeapYear = true;
			}
		}
		
		return isLeapYear;
	}
	
	public static boolean isLeapYear(int year) {
		boolean isLeapYear = false;
		if (year > 0) {
			if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
				isLeapYear = true;
			}
		}
		return isLeapYear;
	}
	
	/**
	 * Return the month according to the given date,
	 * if the date stands for '2013/03/21', 
	 * this method will return 3.
	 * 
	 * @param date
	 * @return int
	 */
	public static int getMonth(Date date) {
		int month = -1;
		if (date != null) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			
			month = c.get(Calendar.MONTH) + 1; // 0 means the first month
		}
		return month;
	}
	
	/**
	 * Check the given date is February 29th or not
	 * 
	 * @param dateStr
	 * @param formatPattern
	 * @return boolean
	 * @throws ParseException
	 */
	public static boolean isSpecialDayInFebruary(String dateStr, String formatPattern) throws ParseException {
		boolean flag = false;
		
		if (dateStr != null && formatPattern != null) {
			SimpleDateFormat df = new SimpleDateFormat(formatPattern);
			Date date = df.parse(dateStr);
			
			if (isLeapYear(date)) {
				Calendar c = Calendar.getInstance();
				c.setTime(date);
				
				int month = c.get(Calendar.MONTH) + 1;
				int dayInMonth = c.get(Calendar.DAY_OF_MONTH);
				
				if (month == 2 && dayInMonth == 29) {
					flag = true;
				}
			}
			
		}
		
		return flag;
	}
	
	public static Date getDateByString(String dateStr) throws Exception {
		Date result = null;
		if (dateStr != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			result = sdf.parse(dateStr);
		}
		return result;
	}
	
}
