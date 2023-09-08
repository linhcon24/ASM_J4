package com.vn.service;

import java.util.List;

import com.vn.model.HoaDon;
import com.vn.repositories.HoaDonRepositories;

public class HoaDonService implements IHoaDonService{
	HoaDonRepositories repo ;
	
	public HoaDonService() {
		repo = new HoaDonRepositories();
	}

	@Override
	public List<HoaDon> getAll() {
		// TODO Auto-generated method stub
		return repo.getAll();
	}

	@Override
	public boolean add(HoaDon hoaDon) {
		// TODO Auto-generated method stub
		return repo.add(hoaDon);
	}

	@Override
	public boolean update(HoaDon hoaDon) {
		// TODO Auto-generated method stub
		return repo.update(hoaDon);
	}

	@Override
	public boolean delete(HoaDon hoaDon) {
		// TODO Auto-generated method stub
		return repo.delete(hoaDon);
	}

	@Override
	public HoaDon getByID(Integer id) {
		// TODO Auto-generated method stub
		return repo.getByID(id);
	}
	
	
}
