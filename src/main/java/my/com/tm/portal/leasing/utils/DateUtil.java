package my.com.tm.portal.leasing.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DateUtil {

	private final static Logger log = LoggerFactory.getLogger(DateUtil.class);
	private final static String SOA_UT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private final static String UT_MONTHLY_INPUT_FORMAT = "MMM yyyy";
	private final static String UT_DAILY_INPUT_FORMAT = "dd/MM/yyyy";

	private DateUtil() {}

	/*
	 * [Utilities Trend] Setting start month to first day of the month and end month
	 * to last day of month.
	 */
	public static String convertStartEndMonthToSoaDate(String startEndMonth, boolean isEndMonth) {

		String convertedDate = "";
		if (isEndMonth) {
			LocalDateTime endDate = LocalDateTime.of(
					YearMonth.parse(startEndMonth, DateTimeFormatter.ofPattern(UT_MONTHLY_INPUT_FORMAT)).atEndOfMonth(),
					LocalTime.of(0, 0, 0));
			convertedDate = endDate.format(DateTimeFormatter.ofPattern(SOA_UT_DATE_FORMAT));
		} else {
			LocalDateTime startDate = LocalDateTime.of(
					YearMonth.parse(startEndMonth, DateTimeFormatter.ofPattern(UT_MONTHLY_INPUT_FORMAT)).atDay(1),
					LocalTime.of(0, 0, 0));
			convertedDate = startDate.format(DateTimeFormatter.ofPattern(SOA_UT_DATE_FORMAT));
		}

		log.debug("Converted Date " + convertedDate);
		return convertedDate;
	}

	public static String convertDailyStartEndDateToSoaDate(String inputDate) {
		LocalDate date = LocalDate.parse(inputDate, DateTimeFormatter.ofPattern(UT_DAILY_INPUT_FORMAT));
		return LocalDateTime.of(date, LocalTime.of(0, 0, 0)).format(DateTimeFormatter.ofPattern(SOA_UT_DATE_FORMAT));
	}

	//Convert Util Date to String
	public static String getDateStr(Date currentDate, String datePattern) {
		DateTimeFormatter dTF = DateTimeFormatter.ofPattern(datePattern);
		return dTF.format(currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
	}

	//Convert String to String
	public static String getDateStr(String currentDate, String datePattern) {
		LocalDate aLD = LocalDate.parse(currentDate);
		DateTimeFormatter dTF = DateTimeFormatter.ofPattern(datePattern);
		return dTF.format(aLD);
	}
	//Convert Local Date/tr to Util Date
	public static Date getDate(String currentDate) {
		if(currentDate == null || currentDate.trim().length() == 0)
			return null;
		LocalDate aLD = null;
		try {
			aLD = LocalDate.parse(currentDate);
			return Date.from(aLD.atStartOfDay(ZoneId.systemDefault()).toInstant());
		}catch(DateTimeException e) {
			log.error(e.getMessage(), e);
			return null;
		}
	}

	public static String getFormattedDate(Date date) {
		if(date == null)
			return "";
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return  df.format(date);
	}

	public static String getFormattedDate(Date date, String format) {
		if(date == null || format == null)
			return "";
		DateFormat df = new SimpleDateFormat(format);
		return  df.format(date);
	}


	public static String getDate12hFormat(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
		return  df.format(date);
	}

	public static String getDate24hFormat(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return  df.format(date);
	}

	public static Date getParsedDate24hFormat(String date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date utilDate = null;
		try {
			utilDate = df.parse(date);
		} catch (ParseException e) {
			log.error(e.getMessage(), e);
		}
		return utilDate;
	}

	//Convert Util Date to Local Date
	public static LocalDate getLocalDate(Date date){
		return date != null ? date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null;
	}


	public static Date getBeginDate(Date date) {
		if(date == null) return null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date); 
		cal.set(Calendar.DATE, 1);
		cal.set(Calendar.MONTH, 0);
		return cal.getTime();
	}

	public static Date getDatePlusOne(Date date) {
		if(date == null) return null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date); 
		cal.add(Calendar.DATE, 1);
		return cal.getTime();
	}


	public static int getMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date); 
		return cal.get(Calendar.MONTH)+1;
	}

	public static LocalDate getLocalDate(String dateStr, String format) {
		if(dateStr == null) return null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		return LocalDate.parse(dateStr, formatter);
	}

	public static LocalDate getCurrentLocalDate() {
		return LocalDate.now();
	}

	public static String getLocalDateToString(LocalDate localDate, String format) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		return  localDate.format(formatter);
	}

	public static Date getCurrentSimpleDate(){
		Date currentDate = new Date();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd h:mm a");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd h:mm a");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date newcurrentdate = null;
		try {
			newcurrentdate = sdf.parse(sdf1.format(currentDate));
		} catch (ParseException e) {
			log.error(e.getMessage(), e);
			return null;
		}
		return newcurrentdate;
	}

	public static Date getDateFromStringWithPattern(final String dateString, final String format) {
		try {
			final DateFormat dateFormat = new SimpleDateFormat(format);
			return dateFormat.parse(dateString);
		} catch (final ParseException e) {
			log.error(e.getMessage(), e);
			return null;
		}
	}

	public static LocalTime extractLocalTime(final String dateString) {
		final String input = dateString.replace(" ", "T");
		final LocalDateTime ldt = LocalDateTime.parse(input);
		return ldt.toLocalTime();
	}
}
