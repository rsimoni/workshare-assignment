package utils;

import java.math.BigDecimal;

public class BigDecimals {

	private static final BigDecimal ONE_MEGABYTE = BigDecimal.valueOf(1024 * 1024);

	private BigDecimals() { }
	
	public static BigDecimal megabyte(double megabyte) {
		return BigDecimal.valueOf(megabyte).multiply(ONE_MEGABYTE);
	}

}
