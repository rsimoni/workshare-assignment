package learning;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.io.IOException;

import net.minidev.json.JSONArray;

import org.junit.*;

public class WorkshareLearningTest {

	private ApiClient client;

	@Before public void login() throws IOException {
		client = new ApiClient("13922396-557d");
		client.login("rsimoni.job+test@gmail.com", "ch1natown");
	}

	@Test public void returns_at_least_one_file() throws IOException {
		JSONArray files = client.getFiles();
		assertThat(files.size(), is(greaterThan(0)));
	}

	@After public void logout() throws IOException {
		client.logout();
	}
	
}
