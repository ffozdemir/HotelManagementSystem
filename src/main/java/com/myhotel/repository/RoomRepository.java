package com.myhotel.repository;

import com.myhotel.config.HibernateUtils;
import com.myhotel.domain.Room;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class RoomRepository {

	private Session session;
	private Transaction transaction;

	public List<Room> findAll() {
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			return session.createQuery("FROM Room").getResultList();
		} catch (
				Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtils.closeSession(session);
		}
		return null;
	}

	//4-c
	public void save(Room room) {
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(room);
			transaction.commit();
		} catch (
				Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtils.closeSession(session);
		}
	}

	public Room findById(Long id) {
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			return session.get(Room.class, id);
		} catch (
				Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtils.closeSession(session);
		}
		return null;
	}

	public void deleteById(Room foundRoom) {
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.delete(foundRoom);
			transaction.commit();
		} catch (
				Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtils.closeSession(session);
		}
	}
}
