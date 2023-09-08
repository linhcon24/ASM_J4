package com.vn.service;

import java.util.List;

import com.vn.model.Cart;

public interface ICartService {
	public List<Cart> getAllByUsername(String username);
	public Cart getCart(String username , Integer id , String kichthuoc);
	public boolean add(Cart cart);
	public void updateCart(String username , Integer idProduct , Integer soLuong , String kichThuoc);
	public void deleteCart(String username , Integer idProduct , String kichThuoc);
	public List<Object[]> listCartbyUser(String user);
}
