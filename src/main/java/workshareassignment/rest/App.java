package workshareassignment.rest;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api")
public class App extends ResourceConfig {

	public App() {
		super();
		
		// NOTE: enable debug of calls
		//property("jersey.config.server.tracing.type", "ALL");
		register(new JacksonFeature());
		register(FileWeightsResource.class);
	}
	
}
