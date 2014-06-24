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
		this(new Workshare());
	}

	public FileWeightsResource(Workshare workshare) {
		this.workshare = workshare;
	}

	@GET
	public FileWeightsResource.Report report(@HeaderParam("username") String username, @HeaderParam("password") String password) {
		try {
			workshare.login(username, password);
			FileWeightsResource.Report report = FileWeightsResource.Report.valueOf(workshare.getFiles());
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
