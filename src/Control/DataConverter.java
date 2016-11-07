package Control;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class DataConverter {

	public static String dateToString(Date date) {
		if (date != null) {
			try {
				SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				return df.format(date);
			} catch (Exception e) {

			}
		}
		return null;
	}

	public static Date stringToDate(String data) {
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date date = null;
		java.sql.Date sqlStartDate = null;
		if (!data.isEmpty() && !data.equals("") && data.length() == 10) {
			data = data.replace("/", "-");
			try {
				date = sdf1.parse(data);
				sqlStartDate = new java.sql.Date(date.getTime());
			} catch (ParseException ex) {

			}
		}
		return sqlStartDate;
	}

	public static Date getDataSistema() {

		return new Date(new java.util.Date().getTime());
	}

	public static Time getHora() {

		// cria um StringBuilder
		StringBuilder sb = new StringBuilder();

		// cria um GregorianCalendar que vai conter a hora atual
		GregorianCalendar d = new GregorianCalendar();
		// anexa do StringBuilder os dados da hora
		sb.append(d.get(GregorianCalendar.HOUR_OF_DAY));
		sb.append(":");
		sb.append(d.get(GregorianCalendar.MINUTE));
		sb.append(":");
		sb.append(d.get(GregorianCalendar.SECOND));

		// retorna a String do StringBuilder
		return Time.valueOf(sb.toString());
	}

}
