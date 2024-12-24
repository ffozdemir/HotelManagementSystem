package com.myhotel.repository;

import com.myhotel.config.HibernateUtils;
import com.myhotel.domain.Reservation;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ReservationRepository {
	private Session session;
	private Transaction transaction;


	public void save(Reservation reservation) {
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(reservation);
			transaction.commit();
		} catch (
				HibernateException e) {
			e.printStackTrace();
		} finally {
			HibernateUtils.closeSession(session);
		}
	}

	public Reservation findById(Long id) {
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			return session.get(Reservation.class, id);
		} catch (
				Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtils.closeSession(session);
		}
		return null;
	}


	public void delete(Reservation foundReservation) {
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.delete(foundReservation);
			transaction.commit();
		} catch (
				Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtils.closeSession(session);
		}
	}

	public List<Reservation> findAll() {
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			return session.createQuery("FROM Reservation ", Reservation.class).getResultList();
		} catch (
				Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtils.closeSession(session);
		}
		return null;
	}
}
