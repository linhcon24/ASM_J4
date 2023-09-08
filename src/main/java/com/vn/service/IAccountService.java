package com.vn.service;

import java.util.List;

import com.vn.model.Account;

public interface IAccountService {
	public List<Account> getAll();
	public boolean add(Account account);
	public boolean update(Account account);
	public void delete (String id);
	public Account getByUsername(String username);
	public void doiMatKhau(String username , String password);
	public Account findAccUserAndPass(String username , String password);
	public List<Account> getAllPhanTrang(Integer limitPage , Integer page);
}
