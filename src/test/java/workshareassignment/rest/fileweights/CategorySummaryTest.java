package workshareassignment.rest.fileweights;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;


public class CategorySummaryTest {

	@Test public void add_sum_weight_and_increment_count_by_one() {
		CategorySummary categorySummary = new CategorySummary();
		categorySummary.add(BigDecimal.TEN);
		categorySummary.add(BigDecimal.ONE);
		assertEquals(2, categorySummary.getCount());
		assertEquals(BigDecimal.valueOf(11), categorySummary.getWeight());
	}

}
