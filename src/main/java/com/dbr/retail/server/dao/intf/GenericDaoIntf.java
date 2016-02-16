package com.dbr.retail.server.dao.intf;

import java.io.Serializable;
import java.util.List;

public interface GenericDaoIntf<T extends Serializable> {

	public void setClazz(final Class< T > clazzToSet );
	
	public T findOne(final long id);

	public List<T> findAll();

	public void create(final T entity);

	public T update(final T entity);

	public void delete(final T entity);

	public void deleteById(final long entityId);
}
