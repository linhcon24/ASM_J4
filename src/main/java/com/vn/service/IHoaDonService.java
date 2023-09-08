package com.vn.service;

import java.util.List;

import com.vn.model.HoaDon;

public interface IHoaDonService {
	public List<HoaDon> getAll();
	public boolean add(HoaDon hoaDon);
	public boolean update(HoaDon hoaDon);
	public boolean delete(HoaDon hoaDon);
	public HoaDon getByID(Integer id);
	
}
