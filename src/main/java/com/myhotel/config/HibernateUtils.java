package com.myhotel.config;

import com.myhotel.domain.Guest;
import com.myhotel.domain.Hotel;
import com.myhotel.domain.Reservation;
import com.myhotel.domain.Room;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {

	private static SessionFactory sessionFactory;

	static {
		try {
			Configuration configuration = new Configuration().configure().addAnnotatedClass(Hotel.class).addAnnotatedClass(Room.class).addAnnotatedClass(Reservation.class).addAnnotatedClass(Guest.class);
			sessionFactory = configuration.buildSessionFactory();
		} catch (
				Exception e) {
			System.out.println("Initialization of session factory is FAILED!!!");
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	//SF kapatalim
	public static void shutDown() {
		getSessionFactory().close();
	}

	//session kapatalim
	public static void closeSession(Session session) {
		if (session != null && session.isOpen()) {
			session.close();
		}
	}

}
