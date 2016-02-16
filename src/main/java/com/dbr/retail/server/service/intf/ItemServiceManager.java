package com.dbr.retail.server.service.intf;

import java.util.List;
import java.util.Set;

import com.dbr.retail.server.model.Item;


public interface ItemServiceManager {

	List<Item> getAll();
	Item addItem(Item item);
	void deleteItem(Item item);
	void deleteItemById(long id);
	Item getItemById(long id);
	Item getItemByKeyAttrs(Set<String> keyAttr);
	Item updateItem(Item item);
}
