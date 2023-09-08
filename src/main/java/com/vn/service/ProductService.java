package com.vn.service;

import java.util.List;

import com.vn.model.Product;
import com.vn.repositories.ProductRepositories;

public class ProductService implements IProductService{
	ProductRepositories repo ;
	
	public ProductService() {
		repo = new ProductRepositories();
	}

	@Override
	public List<Product> getAll() {
		// TODO Auto-generated method stub
		return repo.getAll();
	}

	@Override
	public boolean add(Product product) {
		// TODO Auto-generated method stub
		return repo.add(product);
	}

	@Override
	public boolean update(Product product, Integer id) {
		// TODO Auto-generated method stub
		return repo.update(product,id);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		 repo.delete(id);
	}

	@Override
	public Product getByID(Integer id) {
		// TODO Auto-generated method stub
		return repo.getByID(id);
	}

	@Override
	public List<Product> getAllPhanTrang(Integer limitPage, Integer page) {
		// TODO Auto-generated method stub
		return repo.getAllPhanTrang(limitPage, page);
	}
}
