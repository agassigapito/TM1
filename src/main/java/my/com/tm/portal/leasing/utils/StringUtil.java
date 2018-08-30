package my.com.tm.portal.leasing.utils;

import java.math.BigDecimal;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.uuid.Generators;

public final class StringUtil {

	private StringUtil() {}

	public static String resetStr(String value) {
		return value == null ? "" : value;
	}

	public static String replaceValueIfBlank(String value, String newValue) {
		return value == null ? newValue : value;
	}

	public static String resetAmount(Object value) {
		if(value == null)
			return "0.00";

		if(value instanceof Double)
			return ((Double) value).toString();
		else if(value instanceof BigDecimal)
			return ((BigDecimal) value).toString();
		else if(value instanceof Integer)
			return ((Integer) value).toString();
		return "0.00";
	}

	public static String getAutoSwitch(String priority1, String priority2) {
		return StringUtils.isEmpty(priority1) ? priority2 : priority1;
	}

	public static String getNumbersOnly(String value) {
		return !StringUtils.isEmpty(value) ? value.replaceAll("\\D+", "") : "0";
	}

	public static String bigDecimalToString(BigDecimal num) {
		return (num != null) ? String.valueOf(num) : "" ;
	}

	public static String generateUUID() {
		UUID uuid = Generators.timeBasedGenerator().generate();
		return uuid.toString();
	}

	public static String generateUUID(int length) {
		UUID uuid = Generators.timeBasedGenerator().generate();
		return uuid.toString().substring(0, length);
	}

	public static String getEID(String email) {
		if (email == null) {
			return "";
		}
		int i = email.indexOf("@");
		return email.substring(0, i);
	}
}
