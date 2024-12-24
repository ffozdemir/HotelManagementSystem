package com.myhotel.repository;

import com.myhotel.config.HibernateUtils;
import com.myhotel.domain.Hotel;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HotelRepository {
	private Session session;
	private Transaction transaction;

	//1-b:
	public void save(Hotel hotel) {
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(hotel);//insert into t_hotel values
			transaction.commit();
		} catch (
				HibernateException e) {
			System.out.println(e.getMessage());
		} finally {
			HibernateUtils.closeSession(session);
		}
	}

	//2-c:DB den id verilen satırı getirme
	public Hotel findById(Long id) {
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			//Transaction transaction = session.beginTransaction(); --fetch islemlerinde gerek yok
			return session.get(Hotel.class, id);
		} catch (
				Exception e) {
			System.out.println(e.getMessage());
		} finally {
			HibernateUtils.closeSession(session);
		}
		return null;
	}

	//3-c: DB den tablonun tüm kayitlarini cekme
	public List<Hotel> findAll() {
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			return session.createQuery("FROM Hotel", Hotel.class).getResultList();
		} catch (
				Exception e) {
			System.out.println(e.getMessage());
		} finally {
			HibernateUtils.closeSession(session);
		}
		return null;
	}

	public void delete(Hotel foundHotel) {
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.delete(foundHotel);
			transaction.commit();
		} catch (
				Exception e) {
			System.out.println(e.getMessage());
		} finally {
			HibernateUtils.closeSession(session);
		}
	}

	//8-c
	public void update(Hotel existingHotel) {
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.update(existingHotel);
			transaction.commit();
		} catch (
				Exception e) {
			System.out.println(e.getMessage());
		} finally {
			HibernateUtils.closeSession(session);
		}
	}
}
