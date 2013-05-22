package com.example.dao;

import java.io.Serializable;
import java.util.List;

import com.example.entity.Entity;
import com.example.exception.DaoException;

public interface Dao<T extends Entity, ID extends Serializable> {

	void insert(T entity) throws DaoException;

	void update(T entity) throws DaoException;

	void delete(T entity) throws DaoException;

	// Note: Returns null if not found
	T findById(ID id) throws DaoException;

	int count() throws DaoException;

	// Note: Never returns null. Returns empty list if no elements are found
	List<T> findAll() throws DaoException;

	// For paging, returns from 0-based index, size elements
	List<T> findAll(int index, int size) throws DaoException;

}
