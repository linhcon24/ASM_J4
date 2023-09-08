package com.vn.service;

import java.util.List;

import com.vn.model.Category;
import com.vn.repositories.CategoryRepositories;

public class CategoryService implements ICategoryService{
	CategoryRepositories repo;
	
	public CategoryService() {
		repo = new CategoryRepositories();
	}

	@Override
	public List<Category> getAll() {
		// TODO Auto-generated method stub
		return repo.getAll();
	}

	@Override
	public boolean add(Category category) {
		// TODO Auto-generated method stub
		return repo.add(category);
	}

	@Override
	public boolean update(Category category) {
		// TODO Auto-generated method stub
		return repo.update(category);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		 repo.delete(id);
	}

	@Override
	public Category getByID(Integer id) {
		// TODO Auto-generated method stub
		return repo.getByID(id);
	}

	@Override
	public List<Category> getAllPhanTrang(Integer limitPage, Integer page) {
		// TODO Auto-generated method stub
		return repo.getAllPhanTrang(limitPage, page);
	}
	
	
}
