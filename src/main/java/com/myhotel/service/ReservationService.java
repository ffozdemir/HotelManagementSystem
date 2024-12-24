package com.myhotel.service;

import com.myhotel.domain.Guest;
import com.myhotel.domain.Reservation;
import com.myhotel.domain.Room;
import com.myhotel.exceptions.ReservationNotFoundException;
import com.myhotel.repository.ReservationRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ReservationService {
	private Scanner scanner = new Scanner(System.in);
	private final ReservationRepository reservationRepository;
	private final RoomService roomService;
	private final GuestService guestService;

	public ReservationService(ReservationRepository reservationRepository, RoomService roomService, GuestService guestService) {
		this.reservationRepository = reservationRepository;
		this.roomService = roomService;
		this.guestService = guestService;
	}

	//10-b
	public void createReservation() {
		Reservation reservation = new Reservation();
		System.out.println("Enter check-in date (yyyy-MM-dd): ");
		String checkin = scanner.nextLine();
		reservation.setCheckInDate(LocalDate.parse(checkin));
		System.out.println("Enter check-out date (yyyy-MM-dd): ");
		String checkout = scanner.nextLine();
		reservation.setCheckOutDate(LocalDate.parse(checkout));

		System.out.println("Enter room id : ");
		Long roomId = scanner.nextLong();
		scanner.nextLine();
		Room room = roomService.findRoomById(roomId);

		System.out.println("Enter guest id : ");
		Long guestId = scanner.nextLong();
		scanner.nextLine();
		Guest guest = guestService.findGuestById(guestId);

		if (room != null && guest != null) {
			reservation.setRoom(room);
			reservation.setGuest(guest);
			reservationRepository.save(reservation);
			System.out.println("Reservation created successfully...");
		} else {
			System.out.println("Room or Guest not found. Reservation failed!!!");
		}


	}

	public Reservation findReservationById(Long id) {
		try {
			Reservation foundReservation = reservationRepository.findById(id);
			if (foundReservation != null) {
				System.out.println(foundReservation);
				return foundReservation;
			} else {
				throw new ReservationNotFoundException("Reservation not found by ID: " + id);
			}
		} catch (
				ReservationNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public void deleteReservationById(Long reservationId) {
		Reservation foundReservation = findReservationById(reservationId);
		if (foundReservation != null) {
			System.out.println(foundReservation);
			System.out.println("Are you sure you want to delete this Reservation? (Y/N)");
			String answer = scanner.nextLine();
			if (answer.equalsIgnoreCase("Y")) {
				reservationRepository.delete(foundReservation);
			} else {
				System.out.println("Reservation deletion failed!!!");
			}
		} else {
			System.out.println("Reservation deletion failed!!!");
		}
	}

	public void getAllReservations() {
		List<Reservation> reservationList = reservationRepository.findAll();
		if (reservationList.isEmpty()) {
			System.out.println("No reservations found");
		} else {
			System.out.println("=========RESERVATIONS==========");
			for (Reservation reservation : reservationList) {
				System.out.println(reservation);
			}
			System.out.println("=========RESERVATIONS==========");
		}
	}
}
