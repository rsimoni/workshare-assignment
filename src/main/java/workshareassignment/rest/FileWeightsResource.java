package workshareassignment.rest;

import java.io.IOException;
import java.math.BigDecimal;
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
		private final BigDecimal videosGravity;
		
		public Report(int documentsNumber, int videosNumber, BigDecimal videosGravity) {
			super();
			this.documentsNumber = documentsNumber;
			this.videosNumber = videosNumber;
			this.videosGravity = videosGravity;
		}

		public int getDocumentsNumber() {
			return documentsNumber;
		}

		public int getVideosNumber() {
			return videosNumber;
		}

		public BigDecimal getVideosGravity() {
			return videosGravity;
		}

		public static Report valueOf(JSONArray json) {
			BigDecimal videosGravity = new BigDecimal("1.4");

			int documents = 0;
			int videos = 0;
			BigDecimal videosWeights = BigDecimal.ZERO;
			Iterator<Object> iterator = json.iterator();
			while (iterator.hasNext()) {
				JSONObject file = (JSONObject) iterator.next();
				String fileExtension = (String) file.get("extension");
				BigDecimal size = new BigDecimal((Integer) file.get("size"));
				if ("pdf".equalsIgnoreCase(fileExtension))
					documents++;
				if ("avi".equalsIgnoreCase(fileExtension)) {
					videos++;
					BigDecimal weight = videosGravity.multiply(size);
					videosWeights = videosWeights.add(weight); 
				}
			}
			return new Report(documents, videos, videosWeights);
		}

		@Override
		public String toString() {
			return "Report [documentsNumber=" + documentsNumber + "]";
		}

	}

}
