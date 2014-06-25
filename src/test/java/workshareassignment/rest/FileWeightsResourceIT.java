package workshareassignment.rest;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import workshareassignment.SampleData;

public class FileWeightsResourceIT extends JerseyTest {

	@Override
    protected Application configure() {
        return new ResourceConfig(FileWeightsResource.class);
    }
	
	@Test public void returns_output() {
		String json = target("fileweights")
				.request()
				.header("username", SampleData.WORKSHARE_USERNAME)
				.header("password", SampleData.WORKSHARE_PASSWORD)
				.get(String.class);
        System.out.println("json: " + json);
        assertThat(json, containsString("documents"));
	}

}
