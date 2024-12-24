package com.myhotel.service;

import com.myhotel.domain.Hotel;
import com.myhotel.exceptions.HotelNotFoundException;
import com.myhotel.repository.HotelRepository;

import java.util.List;
import java.util.Scanner;

public class HotelService {
	private Scanner scanner = new Scanner(System.in);
	private final HotelRepository hotelRepository;

	public HotelService(HotelRepository hotelRepository) {
		this.hotelRepository = hotelRepository;
	}

	//1-c:save hotel
	public void saveHotel() {
		Hotel hotel = new Hotel();
		System.out.println("Enter hotel ID: ");
		hotel.setId(scanner.nextLong());
		scanner.nextLine();
		System.out.println("Enter hotel name: ");
		hotel.setName(scanner.nextLine());
		System.out.println("Enter hotel location: ");
		hotel.setLocation(scanner.nextLine());
		hotelRepository.save(hotel);
		System.out.println("Hotel saved successfully. ID: " + hotel.getId());
	}

	//2-b idsi verilen otelin konsolda yazilmasi
	public Hotel findHotelById(Long id) {
		try {
			Hotel foundHotel = hotelRepository.findById(id);//idsi verilen hotel
			if (foundHotel != null) {
				System.out.println("----------------------------------");
				System.out.println(foundHotel);
				System.out.println("----------------------------------");
				return foundHotel;
			} else {
				throw new HotelNotFoundException("Hotel not found by ID: " + id);
			}
		} catch (
				HotelNotFoundException e) {
			System.out.println(e.getMessage());
		}

		return null;
	}

	public void getAllHotels() {
		List<Hotel> allHotels = hotelRepository.findAll(); //select * from t_hotel
		if (allHotels.isEmpty()) {
			System.out.println("Hotel list is empty!");
		} else {
			System.out.println("----------------ALL HOTELS---------------");
			for (Hotel hotel : allHotels) {
				System.out.println(hotel);
			}
			System.out.println("----------------ALL HOTELS---------------");
		}
	}

	//7-b
	public void deleteHotelById(Long hotelid) {
		Hotel foundHotel = findHotelById(hotelid);
		if (foundHotel != null) {
			System.out.println(foundHotel);
			System.out.println("Are you sure you want to delete this hotel? Y/N");
			String select = scanner.next();
			if (select.equalsIgnoreCase("y")) {
				hotelRepository.delete(foundHotel);
			} else {
				System.out.println("Delete operation CANCELLED!!!");
			}
		} else {
			System.out.println("Delete operation CANCELLED!!!");
		}

	}

	public Hotel updateHotelById(Long updatedHotelId) {
		Hotel existingHotel = findHotelById(updatedHotelId);
		if (existingHotel != null) {
			System.out.println("Enter hotel name: ");
			existingHotel.setName(scanner.nextLine());
			System.out.println("Enter hotel location: ");
			existingHotel.setLocation(scanner.nextLine());
			hotelRepository.update(existingHotel);
			System.out.println("Hotel updated successfully...");
		} else {
			System.out.println("Hotel not found by ID: " + updatedHotelId);
		}
		return null;
	}
}
