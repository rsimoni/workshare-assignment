package workshareassignment.rest;

import static org.junit.Assert.*;

import java.io.IOException;

import net.minidev.json.*;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.*;

import workshareassignment.SampleData;
import workshareassignment.provided.Workshare;

public class FileWeightsResourceTest {

	@Rule public JUnitRuleMockery context = new JUnitRuleMockery() {{ 
		setImposteriser(ClassImposteriser.INSTANCE);
	}};
	private Workshare workshare = context.mock(Workshare.class);
	
	@Test public void collects_files_from_workshare_and_create_report_with_1_document() throws IOException {
		final JSONArray filesAsJsonArray = (JSONArray) JSONValue.parse("[{\"extension\":\"pdf\",\"id\":9618642,\"folder_id\":695712,\"updated_at\":\"2014-06-23T14:43:22Z\",\"name\":\"Workshare for iPhone and iPad\",\"created_at\":\"2014-06-23T14:43:22Z\",\"first_page_url\":\"https://my.workshare.com/images/icons/medium/pdf.png\",\"access_token\":\"ue7T6DnwsgAnFmfF\",\"url\":\"https://my.workshare.com/decks/9618642\",\"creator\":{\"email\":\"rsimoni.job+test@gmail.com\",\"name\":\"Roberto Simoni\",\"uuid\":\"27070e9c-0473-49f5-a7d1-80bd7a04252e\"},\"size\":1858008,\"version\":1}]");
		context.checking(new Expectations() {{ 
			oneOf(workshare).login(SampleData.WORKSHARE_USERNAME, SampleData.WORKSHARE_PASSWORD);
			oneOf(workshare).getFiles();
				will(returnValue(filesAsJsonArray));
			oneOf(workshare).logout();
		}});
		
		FileWeightsResource resource = new FileWeightsResource(workshare);
		FileWeightsResource.Report report = resource.report(SampleData.WORKSHARE_USERNAME, SampleData.WORKSHARE_PASSWORD);
		assertEquals(1, report.getDocumentsNumber());
	}

}
