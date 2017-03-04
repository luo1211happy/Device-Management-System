package model;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateConvertion {
	
	public static Date StringToDate(String dateStr, String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = null;
		try{
			date = sdf.parse(dateStr);
		}catch(ParseException e){
			e.printStackTrace();
		}
		return date;
	}

	

}
