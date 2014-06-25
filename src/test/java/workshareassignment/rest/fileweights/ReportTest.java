package workshareassignment.rest.fileweights;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.math.BigDecimal;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONValue;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import workshareassignment.rest.fileweights.Category;
import workshareassignment.rest.fileweights.Report;


public class ReportTest {

	@Test public void valueOf_returns_3_documents() throws IOException {
		final JSONArray json = aJSONArray(
				aFileAsJSON("Workshare for iPhone and iPad", "pdf", "1858008"), 
				aFileAsJSON("hello world!", "pdf", "1858008"), 
				aFileAsJSON("bye bye", "pdf", "1858008")
		);
		Report report = Report.valueOf(json);
		assertEquals(3, report.countOf(Category.documents));
	}

	@Test public void valueOf_returns_2_videos_and_1_song_with_expected_weights() throws IOException {
		final JSONArray json = aJSONArray(
				aFileAsJSON("wombats", "avi", megabyte(10)),
				aFileAsJSON("crazy-dog", "avi", megabyte(22)),
				aFileAsJSON("backinblack", "mp3", megabyte(3.5))
		);
		Report report = Report.valueOf(json);
		System.out.println(report);
		assertEquals(2, report.countOf(Category.videos));
		assertEquals(new BigDecimal("46976204.80"), report.weightOf(Category.videos));
		assertEquals(1, report.countOf(Category.songs));
		assertEquals(new BigDecimal("4404019.20"), report.weightOf(Category.songs));
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

	private long megabyte(double megabyte) {
		return (long) (megabyte * 1024 * 1024);
	}
	
}
