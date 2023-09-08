package com.vn.service;

import java.util.List;

import com.vn.model.Account;
import com.vn.model.Product;

public interface IProductService {
	public List<Product> getAll();
	public boolean add(Product product);
	public boolean update(Product product, Integer id);
	public void delete(Integer id);
	public Product getByID(Integer id);
	public List<Product> getAllPhanTrang(Integer limitPage , Integer page);
}
