package workshareassignment.rest.fileweights;

import static org.junit.Assert.*;

import java.io.IOException;
import java.math.BigDecimal;

import net.minidev.json.*;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import workshareassignment.util.BigDecimals;


public class ReportTest {

	@Test public void valueOf_returns_counters_and_weights_as_provided_in_assignment() throws IOException {
		final JSONArray json = aJSONArray(
				aFileAsJSON("wombats",     "avi",  BigDecimals.megabyte( 10)),
				aFileAsJSON("crazy-dog",   "avi",  BigDecimals.megabyte( 22)),
				aFileAsJSON("backinblack", "mp3",  BigDecimals.megabyte(  3.5)),
				aFileAsJSON("study1",      "odt",  BigDecimals.megabyte(  1.1)),
				aFileAsJSON("study2",      "docx", BigDecimals.megabyte(  2.0)),
				aFileAsJSON("firefox",     "bin",  BigDecimals.megabyte(220.0)),
				aFileAsJSON("readme",      "txt",  BigDecimals.megabyte(  0.1))
		);
		Report report = Report.valueOf(json);
		System.out.println(report);
		assertTrue(report.contains(new Item(Category.videos,    2, new BigDecimal( "44.80"), new BigDecimal( "32.00"))));
		assertTrue(report.contains(new Item(Category.songs,     1, new BigDecimal(  "4.20"), new BigDecimal(  "3.50"))));
		assertTrue(report.contains(new Item(Category.documents, 2, new BigDecimal(  "3.45"), new BigDecimal(  "3.10"))));
		assertTrue(report.contains(new Item(Category.binaries,  1, new BigDecimal("220.00"), new BigDecimal("220.00"))));
		assertTrue(report.contains(new Item(Category.text,      1, new BigDecimal("100.10"), new BigDecimal(  "0.10"))));
		assertNull(report.itemOf(Category.others));
	}

	private String aFileAsJSON(String name, String extension, BigDecimal size) {
		return aFileAsJSON(name, extension, Long.toString(size.longValue()));
	}

	private String aFileAsJSON(String name, String extension, String sizeAsText) {
		return new StringBuilder()
			.append("{\"extension\":\"")
			.append(extension)
			.append("\",\"id\":9618642,\"folder_id\":695712,\"updated_at\":\"2014-06-23T14:43:22Z\",\"name\":\"")
			.append(name)
			.append("\",\"created_at\":\"2014-06-23T14:43:22Z\",\"first_page_url\":\"https://my.workshare.com/images/icons/medium/pdf.png\",\"access_token\":\"ue7T6DnwsgAnFmfF\",\"url\":\"https://my.workshare.com/decks/9618642\",\"creator\":{\"email\":\"rsimoni.job+test@gmail.com\",\"name\":\"Roberto Simoni\",\"uuid\":\"27070e9c-0473-49f5-a7d1-80bd7a04252e\"},\"size\":")
			.append(sizeAsText)
			.append(",\"version\":1}")
			.toString();
	}
	
	private JSONArray aJSONArray(String... items) {
		return (JSONArray) JSONValue.parse("["
				+ StringUtils.join(items, ", ")
				+ "]");
	}

}
