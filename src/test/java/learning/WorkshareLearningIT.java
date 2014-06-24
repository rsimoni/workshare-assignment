package learning;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.io.IOException;

import net.minidev.json.JSONArray;

import org.junit.*;

import workshareassignment.SampleData;
import workshareassignment.provided.Workshare;

public class WorkshareLearningIT {

	private Workshare client;

	@Before public void login() throws IOException {
		client = new Workshare();
		client.login(SampleData.WORKSHARE_USERNAME, SampleData.WORKSHARE_PASSWORD);
	}

	@Test public void returns_at_least_one_file() throws IOException {
		JSONArray files = client.getFiles();
		System.out.println(files);
		assertThat(files.size(), is(greaterThan(0)));
	}

	@After public void logout() throws IOException {
		client.logout();
	}
	
}
