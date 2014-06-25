package workshareassignment.rest.fileweights;

import java.math.*;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import org.apache.commons.lang3.builder.*;

import workshareassignment.util.BigDecimals;

public class Report {

	private final Map<Category, Item> items;
	
	public Report(Map<Category, Item> items) {
		this.items = items;
	}

	/**
	 * @deprecated Needed by Jersey for JSON serialization! Do not use!
	 */
	public Map<Category, Item> getItems() {
		return items;
	}

	public Item itemOf(Category category) {
		return items.get(category);
	}

	public boolean contains(Item item) {
		return items.containsValue(item);
	}

	public boolean contains(Category category) {
		return items.containsKey(category);
	}

	public static Report valueOf(JSONArray json) {
		Map<Category, Item> items = new LinkedHashMap<>();

		Iterator<Object> iterator = json.iterator();
		while (iterator.hasNext()) {
			JSONObject file = (JSONObject) iterator.next();
			
			String fileExtension = (String) file.get("extension");
			BigDecimal bytes = new BigDecimal((Integer) file.get("size"));
			Category category = Category.byExtension(fileExtension);

			Item item = items.get(category);
			if (item == null) {
				item = new Item(category);
				items.put(category, item);
			}

			item.add(bytes);
		}
		
		return new Report(items);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public static class Item {
		
		private Category category;
		private int count;
		private BigDecimal weight;
		private BigDecimal idealWeight;
		
		public Item(Category category) {
			this.category = category;
			this.count = 0;
			this.weight = BigDecimal.ZERO;
			this.idealWeight = BigDecimal.ZERO;
		}

		Item(Category category, int count, BigDecimal weight, BigDecimal idealWeight) {
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

}
