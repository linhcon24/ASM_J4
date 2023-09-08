package com.vn.repositories;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.vn.model.Cart;
import com.vn.unlities.HibernateConfig;

public class CartRepositories {
	public List<Cart> getAllByUsername(String username){
		Session session = HibernateConfig.getFACTORY().openSession();
		Query q = session.createQuery("From Cart where username.username = :user");
		q.setParameter("user", username);
		List<Cart> list = q.getResultList();
		
		return list;
		
	}
	
	public Cart getCart(String username , Integer id , String kichthuoc) {
		Session session = HibernateConfig.getFACTORY().openSession();
		Query q = session.createQuery("From Cart where username.username = :user and product.id = :id and kichThuoc = :kichThuoc");
		q.setParameter("user", username);
		q.setParameter("id", id);
		q.setParameter("kichThuoc", kichthuoc);
		List<Cart> list = q.getResultList();
		if (list.size() == 0) {
			return null;
		}
		return list.get(0);
	}
	public boolean add(Cart cart) {
		Transaction transaction = null ;
		try (Session s = HibernateConfig.getFACTORY().openSession()){
            transaction = s.beginTransaction();
            s.save(cart);
            transaction.commit();
            s.close();
            return true;
            
        } catch (Exception e) {
            transaction.rollback();
            return false;
        }
	}
	public void updateCart(String username , Integer idProduct , Integer soLuong , String kichThuoc) {
		  Session s = HibernateConfig.getFACTORY().openSession();
	        Transaction transaction = s.beginTransaction();
	           Query q = s.createQuery("Update Cart set soLuong = :soluong where username.username = :id and product.id =:idPro and kichThuoc =:kichThuoc");
		        q.setParameter("id", username);
		        q.setParameter("soluong", soLuong);
		        q.setParameter("idPro", idProduct);
		        q.setParameter("kichThuoc", kichThuoc);
		        int index = q.executeUpdate();
		        transaction.commit();
		        s.close();
	}
	public void deleteCart(String username , Integer idProduct , String kichThuoc) {
		  Session s = HibernateConfig.getFACTORY().openSession();
	        Transaction transaction = s.beginTransaction();
	           Query q = s.createQuery("Delete Cart where username.username = :id and product.id =:idPro and kichThuoc =:kichThuoc");
		        q.setParameter("id", username);
		        q.setParameter("idPro", idProduct);
		        q.setParameter("kichThuoc", kichThuoc);
		        int index = q.executeUpdate();
		        transaction.commit();
		        s.close();
	}
	public List<Object[]> listCartbyUser(String user){
		Session session = HibernateConfig.getFACTORY().openSession();
		Query q = session.createQuery("Select id_product , count(soLuong) , donGia , kichThuoc from Cart where username = :user\r\n"
				+ "Group by id_product , donGia , kichThuoc");
		q.setParameter("user", user);
		List<Object[]> list = q.getResultList();
		return list;
		
	}
	
}
