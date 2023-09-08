package com.vn.repositories;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.vn.model.Account;
import com.vn.model.Product;
import com.vn.unlities.HibernateConfig;

public class ProductRepositories {
	public List<Product> getAll(){
		Session session = HibernateConfig.getFACTORY().openSession();
		Query q = session.createQuery("From Product where trangThai = 0");
		List<Product> list = q.getResultList();
		
		return list;
		
	}
	public boolean add(Product product) {
		Transaction transaction = null ;
		try (Session s = HibernateConfig.getFACTORY().openSession()){
            transaction = s.beginTransaction();
            s.save(product);
            transaction.commit();
            return true;
            
        } catch (Exception e) {
            transaction.rollback();
            return false;
        }
	}
	public boolean update (Product product , Integer id) {
		Transaction transaction = null;
        try ( Session s = HibernateConfig.getFACTORY().openSession()) {
            transaction = s.beginTransaction();
//            product = s.find(Product.class, id);
            s.update(product);
            transaction.commit();
            return true;

        } catch (Exception e) {
            transaction.rollback();
            return false;
        }
	}
	public void delete (Integer id) {
         Session s = HibernateConfig.getFACTORY().openSession();
         Transaction transaction = s.beginTransaction();
            Query q = s.createQuery("Update Product set trangthai = 1 where id_Product = :id");
	        q.setParameter("id", id);
	        int index = q.executeUpdate();
	        transaction.commit();
	        s.close();

	}
	public Product getByID(Integer id) {
		  Session session = HibernateConfig.getFACTORY().openSession();
	        Query q = session.createQuery("From Product where id = :id");
	        q.setParameter("id", id);
	        List<Product> list = q.getResultList();
	        if (list.size() == 0) {
	            return null;
	        }
	        return list.get(0);
    }
	public List<Product> getAllPhanTrang(Integer limitPage , Integer page){
		Session session = HibernateConfig.getFACTORY().openSession();
		Query q = session.createQuery("From Product where trangThai = 0");
		q.setFirstResult((limitPage * page ) -limitPage);
		q.setMaxResults(limitPage);
		List<Product> list = q.getResultList();
		
		return list;
	}
}
