package com.vn.service;

import java.util.List;

import com.vn.model.HoaDonChiTiet;
import com.vn.repositories.HoaDonChiTietRepositories;

public class HoaDonChiTietService implements IHoaDonChiTietService{
	HoaDonChiTietRepositories repo ;
	
	public HoaDonChiTietService() {
		repo = new HoaDonChiTietRepositories();
	}

	

	@Override
	public void xoa(Integer idHoaDon) {
		repo.xoa(idHoaDon);
		
	}



	@Override
	public List<HoaDonChiTiet> getAll() {
		// TODO Auto-generated method stub
		return repo.getAll();
	}



	@Override
	public boolean add(HoaDonChiTiet hoaDonChiTiet) {
		// TODO Auto-generated method stub
		return repo.add(hoaDonChiTiet);
	}



	@Override
	public boolean update(HoaDonChiTiet hoaDonChiTiet) {
		// TODO Auto-generated method stub
		return repo.update(hoaDonChiTiet);
	}



	@Override
	public boolean delete(HoaDonChiTiet hoaDonChiTiet) {
		// TODO Auto-generated method stub
		return repo.delete(hoaDonChiTiet);
	}



	@Override
	public HoaDonChiTietRepositories getByID(Integer id) {
		// TODO Auto-generated method stub
		return repo.getByID(id);
	}
	
	
}
