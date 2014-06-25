package workshareassignment.rest.fileweights;

import static org.junit.Assert.*;

import org.junit.Test;

public class CategoryTest {

	@Test public void returns_documents() {
		assertEquals(Category.documents, Category.byExtension("odt"));
		assertEquals(Category.documents, Category.byExtension("docx"));
	}

	@Test public void returns_videos() {
		assertEquals(Category.videos, Category.byExtension("avi"));
	}

	@Test public void returns_songs() {
		assertEquals(Category.songs, Category.byExtension("mp3"));
	}

	@Test public void returns_others() {
		assertEquals(Category.others, Category.byExtension("abracadabra"));
	}

}
