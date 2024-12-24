package com.myhotel.service;

import com.myhotel.domain.Address;
import com.myhotel.domain.Guest;
import com.myhotel.exceptions.GuestNotFoundException;
import com.myhotel.repository.GuestRepository;

import java.util.List;
import java.util.Scanner;

public class GuestService {

	private Scanner scanner = new Scanner(System.in);
	private final GuestRepository guestRepository;

	public GuestService(GuestRepository guestRepository) {
		this.guestRepository = guestRepository;
	}

	//9-b
	public void saveGuest() {
		Guest guest = new Guest();
		Address address = new Address();
		System.out.println("Please enter your name: ");
		guest.setName(scanner.nextLine());
		System.out.println("Enter street: ");
		address.setStreet(scanner.nextLine());
		System.out.println("Enter city: ");
		address.setCity(scanner.nextLine());
		System.out.println("Enter country: ");
		address.setCountry(scanner.nextLine());
		System.out.println("Enter zipcode: ");
		address.setZipcode(scanner.nextLine());
		guest.setAddress(address);
		guestRepository.save(guest);
		System.out.println("Guest saved successfully!");
	}

	public Guest findGuestById(Long guestId) {
		try {
			Guest foundGuest = guestRepository.findById(guestId);
			if (foundGuest != null) {
				System.out.println("*---------------------------------*");
				System.out.println(foundGuest);
				System.out.println("*---------------------------------*");
				return foundGuest;
			} else {
				throw new GuestNotFoundException("Guest not found by ID: " + guestId);
			}
		} catch (
				GuestNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public void deleteGuestById(Long guestid) {
		Guest foundGuest = findGuestById(guestid);
		if (foundGuest != null) {
			System.out.println("=======GUEST========");
			System.out.println(foundGuest);
			System.out.println("=======GUEST========");
			System.out.println("Are you sure you want to delete this Guest? (Y/N)");
			String answer = scanner.next();
			if (answer.equalsIgnoreCase("Y")) {
				guestRepository.deleteById(foundGuest);
			} else {
				System.out.println("Guest delete operation failed!!!");
			}
		} else {
			System.out.println("Guest delete operation failed!!!");
		}
	}

	public void getAllGuests() {
		List<Guest> guestList = guestRepository.findAll();
		if (guestList.isEmpty()) {
			System.out.println("Guest list is empty!");
		} else {
			System.out.println("===========Guest List:============");
			for (Guest guest : guestList) {
				System.out.println(guest);
			}
			System.out.println("===========Guest List:============");
		}

	}
}
