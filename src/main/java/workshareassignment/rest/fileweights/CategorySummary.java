package workshareassignment.rest.fileweights;

import java.math.*;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import workshareassignment.util.BigDecimals;

public class CategorySummary {
	
	private Category category;
	private int count;
	private BigDecimal weight;
	private BigDecimal idealWeight;
	
	public CategorySummary() {
		this(0, BigDecimal.ZERO);
	}

	public CategorySummary(int count, BigDecimal weight) {
		super();
		this.count = count;
		this.weight = weight;
	}

	public CategorySummary(int count, BigDecimal weight, BigDecimal idealWeight) {
		super();
		this.count = count;
		this.weight = weight;
		this.idealWeight = idealWeight;
	}

	public CategorySummary(Category category) {
		this.category = category;
		this.count = 0;
		this.weight = BigDecimal.ZERO;
		this.idealWeight = BigDecimal.ZERO;
	}

	public CategorySummary(Category category, int count, BigDecimal weight, BigDecimal idealWeight) {
		super();
		this.category = category;
		this.count = count;
		this.weight = weight;
		this.idealWeight = idealWeight;
	}

	public void add(BigDecimal sizeInBytes) {
		BigDecimal sizeInMB = BigDecimals.round(sizeInBytes.divide(BigDecimals.ONE_MEGABYTE, 10, RoundingMode.HALF_UP));
		this.idealWeight = this.idealWeight.add(sizeInMB);
		this.weight = this.weight.add(category.weight(sizeInBytes));
		count++;
	}

	public int getCount() {
		return count;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public BigDecimal getIdealWeight() {
		return idealWeight;
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
