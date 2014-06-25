package workshareassignment.rest;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;



import workshareassignment.provided.Workshare;
import workshareassignment.rest.fileweights.Report;

@Path("fileweights")
@Produces({ MediaType.APPLICATION_JSON })
public class FileWeightsResource {

	private static final Logger LOG = Logger.getLogger(FileWeightsResource.class.getName());
	
	private final Workshare workshare;

	public FileWeightsResource() {
		this(new Workshare());
	}

	public FileWeightsResource(Workshare workshare) {
		this.workshare = workshare;
	}

	@GET
	public Report report(@HeaderParam("username") String username, @HeaderParam("password") String password) {
		try {
			workshare.login(username, password);
			Report report = Report.valueOf(workshare.getFiles());
			LOG.info("report: " + report);
			return report;
		} catch (IOException e) {
			LOG.log(Level.SEVERE, "Unable to connect to workshare", e);
			// FIXME capire come segnalare la cosa
			throw new RuntimeException("unexpected error", e);
		} finally {
			try { workshare.logout(); } 
			catch (IOException ignore) { }
		}
	}

}
