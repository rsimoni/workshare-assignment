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

	public boolean containsItemFor(Category category) {
		return items.containsKey(category);
	}

	public Item itemOf(Category category) {
		return items.get(category);
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

	public boolean contains(Item categorySummary) {
		return items.containsValue(categorySummary);
	}

}
