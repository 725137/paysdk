package app.bufa.paysdk.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {
	
	public static final String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";

	public static String getCurrentDateString(String formater) {

		DateTimeFormatter fmt = DateTimeFormatter.ofPattern(formater);

		LocalDateTime dateTime = LocalDateTime.now();

		return dateTime.format(fmt);

	}

	public static Date parse(String str, String formater) {

		if (StringUtils.isTrimBlank(str)) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(formater);
		try {
			return sdf.parse(str);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

}
