package com.vn.service;

import java.util.List;

import com.vn.model.Cart;
import com.vn.repositories.CartRepositories;

public class CartService implements ICartService{
	CartRepositories repo ;
	
	public CartService() {
		repo = new CartRepositories();
	}

	@Override
	public List<Cart> getAllByUsername(String username) {
		// TODO Auto-generated method stub
		return repo.getAllByUsername(username);
	}

	@Override
	public boolean add(Cart cart) {
		// TODO Auto-generated method stub
		return repo.add(cart);
	}


	@Override
	public List<Object[]> listCartbyUser(String user) {
		// TODO Auto-generated method stub
		return repo.listCartbyUser(user);
	}

	@Override
	public Cart getCart(String username, Integer id, String kichthuoc) {
		// TODO Auto-generated method stub
		return repo.getCart(username, id, kichthuoc);
	}

	@Override
	public void updateCart(String username, Integer idProduct, Integer soLuong, String kichThuoc) {
		// TODO Auto-generated method stub
	   repo.updateCart(username, idProduct, soLuong, kichThuoc);
		
	}

	@Override
	public void deleteCart(String username, Integer idProduct, String kichThuoc) {
		// TODO Auto-generated method stub
		repo.deleteCart(username, idProduct, kichThuoc);
		
	}
}
