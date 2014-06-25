package workshareassignment.rest.fileweights;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import workshareassignment.util.BigDecimals;

public class CategoryTest {

	@Test public void returns_expected_categories_by_extension() {
		assertEquals(Category.documents, Category.byExtension("odt"));
		assertEquals(Category.documents, Category.byExtension("docx"));
		
		assertEquals(Category.videos,    Category.byExtension("avi"));

		assertEquals(Category.songs,     Category.byExtension("mp3"));
		
		assertEquals(Category.binaries,  Category.byExtension("bin"));

		assertEquals(Category.text,      Category.byExtension("txt"));

		assertEquals(Category.others,    Category.byExtension("abracadabra"));
	}

	@Test public void weight_returns_specified_size_multiplied_by_a_gravity_related_to_songs() {
		assertEquals(new BigDecimal("4.20"), Category.songs.weight(BigDecimals.megabyte(3.5)));
	}

	@Test public void weight_add_a_fixed_gravity_of_100_when_category_is_text() {
		assertEquals(new BigDecimal("100.10"), Category.text.weight(BigDecimals.megabyte(0.1)));
	}

	@Test public void weight_roundup_to_the_upper_0_dot_05() {
		assertEquals(new BigDecimal("100.05"), Category.text.weight(BigDecimals.megabyte(0.005)));
		assertEquals(new BigDecimal("100.10"), Category.text.weight(BigDecimals.megabyte(0.06)));
	}

}
