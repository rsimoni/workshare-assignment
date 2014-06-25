package workshareassignment.rest.fileweights;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import workshareassignment.util.BigDecimals;


public class CategorySummaryTest {

	@Test public void add_sum_specified_size_to_idealWeight_then_calculate_gravity_weight_and_increment_count_by_one() {
		CategorySummary categorySummary = new CategorySummary(Category.videos);
		categorySummary.add(BigDecimals.megabyte(10));
		categorySummary.add(BigDecimals.megabyte(22));
		assertEquals(2, categorySummary.getCount());
		assertEquals(new BigDecimal("44.80"), categorySummary.getWeight());
		assertEquals(new BigDecimal("32.00"), categorySummary.getIdealWeight());
	}

}
