package ro.unitbv.dao;

import java.util.List;

public interface GenericDAO<T> {

	T findById(int id);

	List<T> findAll();

	T create(T entity);

	T update(T entity);

	void delete(int id);

}
