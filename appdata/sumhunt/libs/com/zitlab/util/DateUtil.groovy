package com.zitlab.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import groovy.transform.CompileStatic

@CompileStatic
public class DateUtil {
	public static Date getDate(String date, String format) {
		if(null == date || 0 == date.length())
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}	
	
	public static long getDiffDays(Date from, Date to) {
		long diffInMillies = to.getTime() - from.getTime();
		return TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
	}
	
	public static long getDiffMonths(Date from, Date to) {
		LocalDate fromLocal = getDate(from);
		LocalDate toLocal = getDate(to);
		return ChronoUnit.MONTHS.between(fromLocal, toLocal);
	}
	
	public static long getDiffYears(Date from, Date to) {
		LocalDate fromLocal = getDate(from);
		LocalDate toLocal = getDate(to);
		return ChronoUnit.YEARS.between(fromLocal, toLocal);
	}
	
	public static long getDiffHours(Date from, Date to) {
		long diffInMillies = to.getTime() - from.getTime();
		return TimeUnit.HOURS.convert(diffInMillies, TimeUnit.MILLISECONDS);
	}
	
	public static LocalDate getDate(Date value) {
		return Instant.ofEpochMilli(value.getTime())
	      .atZone(ZoneId.systemDefault())
	      .toLocalDate();
	}
}
