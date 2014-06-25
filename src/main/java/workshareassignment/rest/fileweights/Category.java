package workshareassignment.rest.fileweights;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.apache.commons.lang3.ArrayUtils;

public enum Category {

	documents(new BigDecimal("1.1"), "odt", "docx", "pdf"), 
	videos(new BigDecimal("1.4"), "avi"), 
	songs(new BigDecimal("1.2"), "mp3"),
	binaries("bin"),
	text("txt"),
	
	others();

	private static final BigDecimal DEFAULT_ROUND_TO = new BigDecimal(System.getProperty("workshareassignment.category.roundto", "0.05"));
	private static final BigDecimal ONE_MEGABYTE = BigDecimal.valueOf(1024 * 1024);
	
	private final BigDecimal gravity;
	private final String[] extensions;

	private Category(String... extensions) {
		this(BigDecimal.ONE, extensions);
	}

	private Category(BigDecimal gravity, String... extensions) {
		this.gravity = gravity;
		this.extensions = extensions;
	}

	public static Category byExtension(String extension) {
		for (Category each : values()) {
			if (ArrayUtils.contains(each.extensions, extension))
				return each;
		}
		return Category.others;
	}

	public BigDecimal weight(BigDecimal size) {
		BigDecimal value = size.multiply(gravity);
		value = value.divide(ONE_MEGABYTE, 10, RoundingMode.HALF_UP);
		if (this.equals(text))
			value = value.add(BigDecimal.valueOf(100L));
		return round(value, DEFAULT_ROUND_TO, RoundingMode.UP);
	}
	
	private static BigDecimal round(BigDecimal value, BigDecimal roundTo, RoundingMode roundingMode) {
		if (roundTo.signum() == 0)
			return value;
		BigDecimal divided = value.divide(roundTo, 0, roundingMode);
		BigDecimal result = divided.multiply(roundTo);
		return result;
	}
	
}