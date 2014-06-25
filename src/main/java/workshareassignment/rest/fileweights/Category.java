package workshareassignment.rest.fileweights;

import org.apache.commons.lang3.ArrayUtils;

public enum Category { 
	documents("odt", "docx", "pdf"), 
	videos("avi"), 
	songs("mp3"), 
	others();

	private final String[] extensions;
	
	private Category(String... extensions) {
		this.extensions = extensions;
	}

	public static Category byExtension(String extension) {
		for (Category each : values()) {
			if (ArrayUtils.contains(each.extensions, extension))
				return each;
		}
		return Category.others;
	}
	
}