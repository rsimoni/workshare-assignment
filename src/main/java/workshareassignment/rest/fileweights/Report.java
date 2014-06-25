package workshareassignment.rest.fileweights;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Report {

	private final Map<Category, CategorySummary> categories;
	
	public Report(Map<Category, CategorySummary> categories) {
		this.categories = categories;
	}

	public Map<Category, CategorySummary> getCategories() {
		return categories;
	}

	public int countOf(Category category) {
		return categories.get(category).count;
	}

	public BigDecimal weightOf(Category category) {
		return categories.get(category).weight;
	}

	public static Report valueOf(JSONArray json) {
		Map<Category, CategorySummary> categories = new LinkedHashMap<>();

		Iterator<Object> iterator = json.iterator();
		while (iterator.hasNext()) {
			JSONObject file = (JSONObject) iterator.next();
			
			String fileExtension = (String) file.get("extension");
			BigDecimal size = new BigDecimal((Integer) file.get("size"));
			Category category = Category.byExtension(fileExtension);

			CategorySummary categorySummary = categories.get(category);
			if (categorySummary == null) {
				categorySummary = new CategorySummary();
				categories.put(category, categorySummary);
			}

			BigDecimal weight = category.weight(size);
			categorySummary.add(weight);
		}
		
		return new Report(categories);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}