package com.chb.utils;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.Chronology;
import java.time.chrono.MinguoChronology;
import java.time.chrono.MinguoDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DecimalStyle;
import java.util.Locale;

public class DateUtil {

	private String ADDateFomat;
	private String MiDateFomat;

	/*
	 * 西元年 yyyyMMdd 轉 民國年 yyyMMdd
	 * 
	 * @param dateString
	 * 
	 * @return the string
	 */
	public String transferADDateToMinguoDate(String dateString) {
		pasePattern(dateString);
		LocalDate localDate = LocalDate.parse(dateString, DateTimeFormatter.ofPattern(ADDateFomat));
		return MinguoDate.from(localDate).format(DateTimeFormatter.ofPattern(MiDateFomat));
	}

	/*
	 * 民國年 yyyMMdd 轉 西元年 yyyyMMdd
	 * 
	 * @param dateString the String dateString
	 * 
	 * @return the string
	 */
	public String transferMinguoDateToADDate(String dateString) {
		Chronology chrono = MinguoChronology.INSTANCE;
		pasePattern(dateString);
		DateTimeFormatter df = new DateTimeFormatterBuilder().parseLenient().appendPattern(MiDateFomat).toFormatter()
				.withChronology(chrono).withDecimalStyle(DecimalStyle.of(Locale.getDefault()));

		ChronoLocalDate chDate = chrono.date(df.parse(dateString));
		return LocalDate.from(chDate).format(DateTimeFormatter.ofPattern(ADDateFomat));
	}

	private void pasePattern(String pattern) {
		if (pattern.contains("/")) {
			ADDateFomat = "yyyy/MM/dd";
			MiDateFomat = "yyy/MM/dd";
		} else if (pattern.contains("-")) {
			ADDateFomat = "yyyy-MM-dd";
			MiDateFomat = "yyy-MM-dd";
		} else if (pattern.contains("_")) {
			ADDateFomat = "yyyy_MM_dd";
			MiDateFomat = "yyy_MM_dd";
		} else {
			ADDateFomat = "yyyyMMdd";
			MiDateFomat = "yyyMMdd";
		}
	}
}
