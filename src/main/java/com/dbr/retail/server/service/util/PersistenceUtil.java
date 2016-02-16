package com.dbr.retail.server.service.util;

import com.dbr.retail.server.model.BaseObject;
import com.dbr.retail.server.model.Item;
import com.dbr.retail.server.service.impl.ItemServiceManagerImpl;
import com.dbr.retail.server.service.intf.ItemServiceManager;

public class PersistenceUtil {

	
	private static PersistenceUtil instance = null;
	
	public static PersistenceUtil getInstance() {
		if (instance == null) {
			synchronized (instance) {
				if (instance == null) {
					instance = new PersistenceUtil();
				}
			}
		}
		return instance;
	}
	
	public void addObject(BaseObject entity) {
		if (entity instanceof Item) {
			ItemServiceManager item = new ItemServiceManagerImpl();
			item.addItem((Item)entity);
		}
	}
	
}
