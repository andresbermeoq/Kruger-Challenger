package com.kruger.services;

import java.util.List;

public interface AbstractService<T, I> {
	
	public List<T> findAll();
	public T saveEntity(T entity);
	public T findById(I id);
	public T updateEntity(I id, T entity);
	public void deleteEntity(I id);
}
