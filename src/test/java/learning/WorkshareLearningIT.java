package learning;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.io.IOException;

import net.minidev.json.JSONArray;

import org.junit.*;

import workshareassignment.provided.Workshare;

public class WorkshareLearningIT {

	private Workshare client;

	@Before public void login() throws IOException {
		client = new Workshare("13922396-557d");
		client.login("rsimoni.job+test@gmail.com", "ch1natown");
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
