package com.vn.repositories;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.vn.model.Category;
import com.vn.model.HoaDonChiTiet;
import com.vn.unlities.HibernateConfig;

public class HoaDonChiTietRepositories {
	public List<HoaDonChiTiet> getAll(){
		Session session = HibernateConfig.getFACTORY().openSession();
		Query q = session.createQuery("From HoaDonChiTiet where hoaDon.idHoaDon = :id");
		List<HoaDonChiTiet> list = q.getResultList();
		
		return list;
		
	}
	public boolean add(HoaDonChiTiet hoaDonChiTiet) {
		Transaction transaction = null ;
		try (Session s = HibernateConfig.getFACTORY().openSession()){
            transaction = s.beginTransaction();
            s.save(hoaDonChiTiet);
            transaction.commit();
            s.close();
            return true;
            
        } catch (Exception e) {
            transaction.rollback();
            return false;
        }
	}
	public boolean update (HoaDonChiTiet hoaDonChiTiet) {
		Transaction transaction = null;
        try ( Session s = HibernateConfig.getFACTORY().openSession()) {
            transaction = s.beginTransaction();
            s.update(hoaDonChiTiet);
            transaction.commit();
            s.close();
            return true;

        } catch (Exception e) {
            transaction.rollback();
            return false;
        }
	}
	public boolean delete (HoaDonChiTiet hoaDonChiTiet) {
		Transaction transaction = null;
        try ( Session s = HibernateConfig.getFACTORY().openSession()) {
            transaction = s.beginTransaction();
            s.delete(hoaDonChiTiet);
            transaction.commit();
            s.close();
            return true;

        } catch (Exception e) {
            transaction.rollback();
            return false;
        }
	}
	public void xoa(Integer idHoaDon) {
		Session s = HibernateConfig.getFACTORY().openSession();
		Transaction transaction = s.beginTransaction();
		Query q = s.createQuery("Delete HoaDonChiTiet where hoaDon.idHoaDon =:id");
		q.setParameter("id", idHoaDon);
		int index = q.executeUpdate();
		transaction.commit();
		s.close();
	}
	public HoaDonChiTietRepositories getByID(Integer id) {
		  Session session = HibernateConfig.getFACTORY().openSession();
	        Query q = session.createQuery("From HoaDonChiTiet where idHoaDon = :id");
	        q.setParameter("id", id);
	        List<HoaDonChiTietRepositories> list = q.getResultList();
	        if (list.size() == 0) {
	            return null;
	        }
	        return list.get(0);
    }
}
