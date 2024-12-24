package com.myhotel.repository;

import com.myhotel.config.HibernateUtils;
import com.myhotel.domain.Guest;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class GuestRepository {
	private Session session;
	private Transaction transaction;

	//9-c
	public void save(Guest guest) {
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(guest);
			transaction.commit();
		} catch (
				Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtils.closeSession(session);
		}
	}

	public Guest findById(Long guestId) {
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			return session.get(Guest.class, guestId);
		} catch (
				HibernateException e) {
			e.printStackTrace();
		} finally {
			HibernateUtils.closeSession(session);
		}
		return null;
	}

	public void deleteById(Guest foundGuest) {
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.delete(foundGuest);
			transaction.commit();
		} catch (
				Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtils.closeSession(session);
		}
	}

	public List<Guest> findAll() {
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			return session.createQuery("from Guest", Guest.class).getResultList();
		} catch (
				Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtils.closeSession(session);
		}
		return null;
	}
}
