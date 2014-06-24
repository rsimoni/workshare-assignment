package workshareassignment.rest;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.math.BigDecimal;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONValue;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import workshareassignment.rest.FileWeightsResource.Report;

public class FileWeightsResourceReportTest {

	@Test public void valueOf_returns_3_documents() throws IOException {
		final JSONArray json = aJSONArray(
				aFileAsJSON("Workshare for iPhone and iPad", "pdf", "1858008"), 
				aFileAsJSON("hello world!", "pdf", "1858008"), 
				aFileAsJSON("bye bye", "pdf", "1858008")
		);
		Report report = FileWeightsResource.Report.valueOf(json);
		assertEquals(3, report.getDocumentsNumber());
	}

	@Test public void valueOf_returns_2_videos_with_expected_weights() throws IOException {
		final JSONArray json = aJSONArray(
				aFileAsJSON("wombats", "avi", megabyte(10)),
				aFileAsJSON("crazy-dog", "avi", megabyte(22))
		);
		Report report = FileWeightsResource.Report.valueOf(json);
		assertEquals(2, report.getVideosNumber());
		assertEquals(new BigDecimal("46976204.8"), report.getVideosGravity());
	}

	private String aFileAsJSON(String name, String extension, long size) {
		return aFileAsJSON(name, extension, Long.toString(size));
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

	private long megabyte(int megabyte) {
		return megabyte * 1024 * 1024;
	}
	
}
