package com.dbr.retail.web.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dbr.retail.server.error.RestPreconditions;
import com.dbr.retail.server.model.Item;
import com.dbr.retail.server.service.intf.ItemServiceManager;

@RestController
@RequestMapping("/entity")
public class TestController {

	private static final Logger logger = Logger.getLogger(TestController.class);

	@Autowired
	ItemServiceManager service;


	@RequestMapping( method = RequestMethod.GET )
	@ResponseBody
	public List< Item > findAll(){
		logger.info("findAll: fetching all entries...");
		return service.getAll();
	}

	@RequestMapping( value = "/get", method = RequestMethod.GET )
	@ResponseBody
	public Object findOne( @RequestParam("id") Long id){
		Object result;
		try {
			result = RestPreconditions.checkFound( service.getItemById(id ) );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result = e.getMessage();
		}
		return result;
	}

	@RequestMapping( value = "/add" , method = RequestMethod.POST )
	@ResponseStatus( HttpStatus.CREATED )
	@ResponseBody
	public Long create( @RequestBody Item resource ){
		try {
			RestPreconditions.checkNotNull( resource , resource.getName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return service.addItem(resource).getId();
	}

	@RequestMapping( value = "/update", method = RequestMethod.PUT )
	@ResponseStatus( HttpStatus.OK )
	public Item update( @RequestParam Long id, @RequestBody Item resource ){
		try {
			RestPreconditions.checkNotNull( resource , String.valueOf(id));
			RestPreconditions.checkNotNull( service.getItemById( resource.getId() ), String.valueOf(resource.getId()) );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return service.updateItem( resource );
	}

	@RequestMapping( value = "/delete", method = RequestMethod.DELETE )
	@ResponseStatus( HttpStatus.OK )
	public void delete( @RequestParam Long id ){
		service.deleteItemById( id );
	}

}
