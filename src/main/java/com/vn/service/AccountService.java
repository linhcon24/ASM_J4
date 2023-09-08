package com.vn.service;

import java.util.List;

import com.vn.model.Account;
import com.vn.repositories.AccountRepositories;

public class AccountService implements IAccountService{
	AccountRepositories repo ;
	
	public AccountService() {
		repo = new AccountRepositories();
	}

	@Override
	public List<Account> getAll() {
		// TODO Auto-generated method stub
		return repo.getAll();
	}

	@Override
	public boolean add(Account account) {
		// TODO Auto-generated method stub
		return repo.add(account);
	}

	@Override
	public boolean update(Account account) {
		// TODO Auto-generated method stub
		return repo.update(account);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		 repo.delete(id);
	}


	public Account getByUsername(String username) {
		// TODO Auto-generated method stub
		return repo.getByUsername(username);
	}

	@Override
	public void doiMatKhau(String username, String password) {
		// TODO Auto-generated method stub
		 repo.doiMatKhau(username, password);
	}

	@Override
	public Account findAccUserAndPass(String username, String password) {
		// TODO Auto-generated method stub
		return repo.findAccUserAndPass(username, password);
	}

	@Override
	public List<Account> getAllPhanTrang(Integer limitPage, Integer page) {
		// TODO Auto-generated method stub
		return repo.getAllPhanTrang(limitPage, page);
	}





}
