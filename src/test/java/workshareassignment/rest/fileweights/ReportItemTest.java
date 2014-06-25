package workshareassignment.rest.fileweights;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import workshareassignment.rest.fileweights.Report.Item;
import workshareassignment.util.BigDecimals;


public class ReportItemTest {

	@Test public void add_sum_specified_size_to_idealWeight_then_calculate_gravity_weight_and_increment_count_by_one() {
		Item item = new Item(Category.videos);
		item.add(BigDecimals.megabyte(10));
		item.add(BigDecimals.megabyte(22));
		assertEquals(2, item.getCount());
		assertEquals(new BigDecimal("44.80"), item.getWeight());
		assertEquals(new BigDecimal("32.00"), item.getIdealWeight());
	}

}
