package delteria.repo.items;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Item {
	
	@Id
	public String item_id;

	@Column
	public String name;

	@Column
	public String icon_file;

	@Column
	public String description;
	
	public Map<String, String> properties;
	
	@Column
	public boolean quest_item;
		
	Item() {
		
	}

	public Item(String item_id, String name, String icon_file, String description, boolean quest_item, Map<String, String> properties) {
		this.item_id = item_id;
		this.name = name;
		this.icon_file = icon_file;
		this.description = description;
		this.quest_item = quest_item;
		this.properties = properties;
	}
	
	public String getItemId() {
		return item_id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getIconFile() {
		return icon_file;
	}
	
	public String getDescription() {
		return description;
	}
	
	public boolean isQuestItem() {
		return quest_item;
	}
	
	public Map<String, String> getProperties() {
		return properties;
	}
}
