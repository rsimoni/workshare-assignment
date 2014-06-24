package workshareassignment.rest;

import java.io.IOException;
import java.util.logging.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import net.minidev.json.JSONArray;
import workshareassignment.provided.Workshare;

@Path("fileweights")
@Produces({ MediaType.APPLICATION_JSON })
public class FileWeightsResource {

	private static final Logger LOG = Logger.getLogger(FileWeightsResource.class.getName());
	
	private final Workshare workshare;

	public FileWeightsResource() {
		this(new Workshare("13922396-557d"));
	}

	public FileWeightsResource(Workshare workshare) {
		this.workshare = workshare;
	}

	//FIXME rimuovere i default
	@GET
	public FileWeightsResource.Report report(@QueryParam("username") @DefaultValue("rsimoni.job+test@gmail.com") String username, @QueryParam("password") @DefaultValue("ch1natown") String password) {
		try {
			workshare.login(username, password);
			FileWeightsResource.Report report = FileWeightsResource.Report.valueOf(workshare.getFiles());
			LOG.info("report: " + report);
			return report;
		} catch (IOException e) {
			LOG.log(Level.SEVERE, "Unable to connect to workshare", e);
			// FIXME capire come segnalare la cosa
			throw new RuntimeException("unexpected error", e);
		}
	}
	
	public static class Report {

		private final int documentsNumber;
		
		public Report(int documentsNumber) {
			super();
			this.documentsNumber = documentsNumber;
		}

		public int getDocumentsNumber() {
			return documentsNumber;
		}

		public static Report valueOf(JSONArray json) {
			return new Report(json.size());
		}

		@Override
		public String toString() {
			return "Report [documentsNumber=" + documentsNumber + "]";
		}

	}

}
