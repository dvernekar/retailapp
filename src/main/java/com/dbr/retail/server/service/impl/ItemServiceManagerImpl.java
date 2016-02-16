package com.dbr.retail.server.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dbr.retail.server.dao.intf.GenericDaoIntf;
import com.dbr.retail.server.model.Item;
import com.dbr.retail.server.service.intf.ItemServiceManager;

@Service
@Transactional
public class ItemServiceManagerImpl implements ItemServiceManager {

	GenericDaoIntf<Item> itemDao;

	public GenericDaoIntf<Item> getItemDao() {
		return itemDao;
	}

	@Autowired
	public void setItemDao(GenericDaoIntf<Item> itemDao) {
		this.itemDao = itemDao;
		this.itemDao.setClazz(Item.class);
	}
	
	public ItemServiceManagerImpl() {
		super();
	}

	@Override
	public List<Item> getAll() {
		return itemDao.findAll();
		
	}

	@Override
	public Item addItem(Item item) {
		itemDao.create(item);
		return null;
	}

	@Override
	public void deleteItem(Item item) {
		itemDao.delete(item);
	}

	@Override
	public Item getItemById(long id) {
		Item item = itemDao.findOne(id);
		return item;
	}

	@Override
	public Item getItemByKeyAttrs(Set<String> keyAttr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item updateItem(Item item) {
		return itemDao.update(item);
	}

	@Override
	public void deleteItemById(long id) {
		itemDao.deleteById(id);
	}


}
