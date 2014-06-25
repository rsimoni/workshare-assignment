package workshareassignment.rest.fileweights;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class CategorySummary {
	
	private int count;
	private BigDecimal weight;
	
	public CategorySummary() {
		this(0, BigDecimal.ZERO);
	}

	public CategorySummary(int count, BigDecimal weight) {
		super();
		this.count = count;
		this.weight = weight;
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

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}