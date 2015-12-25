package org.hello.generic.service;

import java.util.List;

import org.hello.generic.persistence.Dao;
import org.springframework.beans.factory.annotation.Autowired;

public class basicService<E> {
	
	@Autowired
	protected Dao<E> dao;
	
	private Class<E> clazz;
	
	public basicService(Class<E> clazz) {
		this.setClazz(clazz);
	}

	public Class<E> getClazz() {
		return clazz;
	}

	public void setClazz(Class<E> clazz) {
		this.clazz = clazz;
	}
	
	public String getName() {
		return clazz.getSimpleName();
	}
	
	public void insert(E object) {
		dao.insert(object);
	}
	
	public void update(E object) {
		dao.update(object);
	}
	
	public void delete(E object) {
		dao.delete(object);
	}
	
	public List<E> select() {
		return dao.select();
	}
	
	public List<E> select(Object id) {
		return dao.select(id);
	}
	
	public List<E> select(String key, String value) {
		return dao.select(key, value);
	}
	
}
