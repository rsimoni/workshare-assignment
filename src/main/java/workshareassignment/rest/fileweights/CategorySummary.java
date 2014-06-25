package workshareassignment.rest.fileweights;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class CategorySummary {
	
	int count;
	BigDecimal weight;
	
	public CategorySummary() {
		super();
		this.count = 0;
		this.weight = BigDecimal.ZERO;
	}

	public void add(BigDecimal weight) {
		this.weight = this.weight.add(weight);
		count++;
	}

	public int getCount() {
		return count;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}