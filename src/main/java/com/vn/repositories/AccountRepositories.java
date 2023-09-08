package com.vn.repositories;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.vn.model.Account;
import com.vn.unlities.HibernateConfig;

public class AccountRepositories {
	public List<Account> getAll(){
		Session session = HibernateConfig.getFACTORY().openSession();
		Query q = session.createQuery("From Account");
		List<Account> list = q.getResultList();
		
		return list;
		
	}
	public List<Account> getAllPhanTrang(Integer limitPage , Integer page){
		Session session = HibernateConfig.getFACTORY().openSession();
		Query q = session.createQuery("From Account where role = 1 or role = 0");
		q.setFirstResult((limitPage * page ) -limitPage);
		q.setMaxResults(limitPage);
		List<Account> list = q.getResultList();
		
		return list;
	}
	public boolean add(Account account) {
		Transaction transaction = null ;
		try (Session s = HibernateConfig.getFACTORY().openSession()){
            transaction = s.beginTransaction();
            s.save(account);
            transaction.commit();
            s.close();
            return true;
            
        } catch (Exception e) {
            transaction.rollback();
            return false;
        }
	}
	public boolean update (Account account) {
		Transaction transaction = null;
        try ( Session s = HibernateConfig.getFACTORY().openSession()) {
            transaction = s.beginTransaction();
            s.update(account);
            transaction.commit();
            s.close();
            return true;

        } catch (Exception e) {
            transaction.rollback();
            return false;
        }
	}
	public void delete (String id) {
        Session s = HibernateConfig.getFACTORY().openSession();
        Transaction transaction = s.beginTransaction();
           Query q = s.createQuery("Update Account set role = 2 where username = :id");
	        q.setParameter("id", id);
	        int index = q.executeUpdate();
	        transaction.commit();
	        s.close();
     

	}
	public Account getByUsername(String username) {
		  Session session = HibernateConfig.getFACTORY().openSession();
	        Query q = session.createQuery("From Account where username = :id");
	        q.setParameter("id", username);
	        List<Account> list = q.getResultList();
	        if (list.size() == 0) {
	            return null;
	        }
	        return list.get(0);
    }
	public void doiMatKhau(String username , String password) {
		 Session session = HibernateConfig.getFACTORY().openSession();
	        Transaction transaction = session.beginTransaction();
	        Query q = session.createQuery("UPDATE Account SET password = :matkhau where username = :ma");
	        q.setParameter("ma", username);
	        q.setParameter("matkhau", password);
	        int index = q.executeUpdate();
	        transaction.commit();
	        session.close();
	}
	
	public Account findAccUserAndPass(String username , String password) {
		Session session = HibernateConfig.getFACTORY().openSession();
		Query q = session.createQuery("FROM Account  WHERE username = :u AND password = :p");
		q.setParameter("u", username);
		q.setParameter("p", password);
		List<Account> list = q.getResultList();	
		if (list.size() == 0) {
			return null;
		}
		
		return list.get(0);
	}

}
