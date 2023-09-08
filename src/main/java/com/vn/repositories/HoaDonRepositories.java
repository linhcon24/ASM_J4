package com.vn.repositories;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.vn.model.Category;
import com.vn.model.HoaDon;
import com.vn.unlities.HibernateConfig;

public class HoaDonRepositories {
	public List<HoaDon> getAll(){
		Session session = HibernateConfig.getFACTORY().openSession();
		Query q = session.createQuery("From HoaDon");
		List<HoaDon> list = q.getResultList();
		
		return list;
		
	}
	public boolean add(HoaDon hoaDon) {
		Transaction transaction = null ;
		try (Session s = HibernateConfig.getFACTORY().openSession()){
            transaction = s.beginTransaction();
            s.save(hoaDon);
            transaction.commit();
            s.close();
            return true;
            
        } catch (Exception e) {
            transaction.rollback();
            return false;
        }
	}
	public boolean update (HoaDon hoaDon) {
		Transaction transaction = null;
        try ( Session s = HibernateConfig.getFACTORY().openSession()) {
            transaction = s.beginTransaction();
            s.update(hoaDon);
            transaction.commit();
            s.close();
            return true;

        } catch (Exception e) {
            transaction.rollback();
            return false;
        }
	}
	public boolean delete (HoaDon hoaDon) {
		Transaction transaction = null;
        try ( Session s = HibernateConfig.getFACTORY().openSession()) {
            transaction = s.beginTransaction();
            s.delete(hoaDon);
            transaction.commit();
            s.close();
            return true;

        } catch (Exception e) {
            transaction.rollback();
            return false;
        }
	}
	public HoaDon getByID(Integer id) {
		  Session session = HibernateConfig.getFACTORY().openSession();
	        Query q = session.createQuery("From HoaDon where idHoaDon = :id");
	        q.setParameter("id", id);
	        List<HoaDon> list = q.getResultList();
	        if (list.size() == 0) {
	            return null;
	        }
	        return list.get(0);
    }
}
