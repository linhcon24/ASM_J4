package com.vn.repositories;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.vn.model.Account;
import com.vn.model.Category;
import com.vn.model.Product;
import com.vn.unlities.HibernateConfig;

public class CategoryRepositories {
	public List<Category> getAll(){
		Session session = HibernateConfig.getFACTORY().openSession();
		Query q = session.createQuery("From Category");
		List<Category> list = q.getResultList();
		return list;
		
	}
	public List<Category> getAllPhanTrang(Integer limitPage , Integer page){
		Session session = HibernateConfig.getFACTORY().openSession();
		Query q = session.createQuery("From Category where trangThai = 0");
		q.setFirstResult((limitPage * page ) -limitPage);
		q.setMaxResults(limitPage);
		List<Category> list = q.getResultList();
		
		return list;
	}
	public boolean add(Category category) {
		Transaction transaction = null ;
		try (Session s = HibernateConfig.getFACTORY().openSession()){
            transaction = s.beginTransaction();
            s.save(category);
            transaction.commit();
            s.close();
            return true;
            
        } catch (Exception e) {
            transaction.rollback();
            return false;
        }
		
	}
	public boolean update (Category category) {
		Transaction transaction = null;
        try ( Session s = HibernateConfig.getFACTORY().openSession()) {
            transaction = s.beginTransaction();
            s.update(category);
            transaction.commit();
            s.close();
            return true;

        } catch (Exception e) {
            transaction.rollback();
            return false;
        }
	}
	public void delete (Integer id) {
        Session s = HibernateConfig.getFACTORY().openSession();
        Transaction transaction = s.beginTransaction();
           Query q = s.createQuery("Update Category set trangthai = 1 where idCategory = :id");
	        q.setParameter("id", id);
	        int index = q.executeUpdate();
	        transaction.commit();
	        s.close();
	}
	public Category getByID(Integer id) {
		  Session session = HibernateConfig.getFACTORY().openSession();
	        Query q = session.createQuery("From Category where idCategory = :id");
	        q.setParameter("id", id);
	        List<Category> list = q.getResultList();
	        if (list.size() == 0) {
	            return null;
	        }
	        return list.get(0);
    }
}
