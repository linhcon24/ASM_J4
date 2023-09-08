package com.vn.service;

import java.util.List;

import com.vn.model.HoaDonChiTiet;
import com.vn.repositories.HoaDonChiTietRepositories;

public interface IHoaDonChiTietService {
	public List<HoaDonChiTiet> getAll();
	public boolean add(HoaDonChiTiet hoaDonChiTiet);
	public boolean update(HoaDonChiTiet hoaDonChiTiet);
	public boolean delete(HoaDonChiTiet hoaDonChiTiet);
	public HoaDonChiTietRepositories getByID(Integer id);
	public void xoa(Integer idHoaDon);
}
