package com.vn.service;

import java.util.List;

import com.vn.model.Category;

public interface ICategoryService {
	public List<Category> getAll();
	public boolean add(Category category);
	public boolean update (Category category);
	public void delete (Integer id);
	public Category getByID(Integer id);
	public List<Category> getAllPhanTrang(Integer limitPage , Integer page);
}
