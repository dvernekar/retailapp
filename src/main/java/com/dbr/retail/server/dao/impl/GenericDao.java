package com.dbr.retail.server.dao.impl;

import java.io.Serializable;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.dbr.retail.server.dao.intf.GenericDaoIntf;

@Repository
@Scope( BeanDefinition.SCOPE_PROTOTYPE )
public class GenericDao<T extends Serializable> extends AbstractDao<T> implements GenericDaoIntf<T> {

	@Override
	public void create(T entity) {
		// TODO Auto-generated method stub
		
	}

}
