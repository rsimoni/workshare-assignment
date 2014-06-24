package workshareassignment.rest;

import java.io.IOException;
import java.util.Iterator;
import java.util.logging.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
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
		private final int videosNumber;
		
		public Report(int documentsNumber, int videosNumber) {
			super();
			this.documentsNumber = documentsNumber;
			this.videosNumber = videosNumber;
		}

		public int getDocumentsNumber() {
			return documentsNumber;
		}

		public int getVideosNumber() {
			return videosNumber;
		}

		public static Report valueOf(JSONArray json) {
			int documents = 0;
			int videos = 0;
			Iterator<Object> iterator = json.iterator();
			while (iterator.hasNext()) {
				JSONObject file = (JSONObject) iterator.next();
				String fileExtension = (String) file.get("extension");
				if ("pdf".equalsIgnoreCase(fileExtension))
					documents++;
				if ("avi".equalsIgnoreCase(fileExtension))
					videos++;
			}
			return new Report(documents, videos);
		}

		@Override
		public String toString() {
			return "Report [documentsNumber=" + documentsNumber + "]";
		}

	}

}
