package workshareassignment.util;

import java.math.*;

public class BigDecimals {

	public static final BigDecimal ONE_MEGABYTE = BigDecimal.valueOf(1024 * 1024);
	public static final BigDecimal DEFAULT_ROUND_TO = new BigDecimal(System.getProperty("workshareassignment.category.roundto", "0.05"));

	private BigDecimals() { }
	
	public static BigDecimal megabyte(double megabyte) {
		return BigDecimal.valueOf(megabyte).multiply(ONE_MEGABYTE);
	}

	public static BigDecimal round(BigDecimal value, BigDecimal roundTo, RoundingMode roundingMode) {
		if (roundTo.signum() == 0)
			return value;
		BigDecimal divided = value.divide(roundTo, 0, roundingMode);
		BigDecimal result = divided.multiply(roundTo);
		return result;
	}

	public static BigDecimal round(BigDecimal value) {
		return round(value, DEFAULT_ROUND_TO, RoundingMode.UP);
	}
	
}
