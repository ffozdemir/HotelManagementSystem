package com.myhotel.service;

import com.myhotel.domain.Hotel;
import com.myhotel.domain.Room;
import com.myhotel.exceptions.RoomNotFoundException;
import com.myhotel.repository.RoomRepository;

import java.util.List;
import java.util.Scanner;

//NOT:entitynin service classları kendi repository classları ile görüşür
//başka bir entity ile ilgili işlem gerekirse diğer entitynin service ile görüşür
public class RoomService {

	private Scanner scanner = new Scanner(System.in);

	private final RoomRepository roomRepository;

	private final HotelService hotelService;

	public RoomService(RoomRepository roomRepository, HotelService hotelService) {
		this.roomRepository = roomRepository;
		this.hotelService = hotelService;
	}

	//4-b: alinan bilgiler ile odayi kaydetme

	public void saveRoom() {
		Room room = new Room();
		System.out.println("Enter room ID: ");
		room.setId(scanner.nextLong());
		scanner.nextLine();
		System.out.println("Enter room number: ");
		room.setNumber(scanner.nextLine());
		System.out.println("Enter room capacity: ");
		room.setCapacity(scanner.nextInt());
		scanner.nextLine();
		System.out.println("Enter hotel ID: ");
		Long hotelId = scanner.nextLong();
		scanner.nextLine();
		//id si verilen oteli bulma
		//hotelservice
		Hotel hotel = hotelService.findHotelById(hotelId);
		if (hotel != null) {
			room.setHotel(hotel);
			roomRepository.save(room);
			System.out.println("Room saved successfully! Room id: " + room.getId());
		} else {
			System.out.println("Room couldn't saved!!!");
		}

	}

	public void getAllRooms() {
		List<Room> allRooms = roomRepository.findAll();
		if (allRooms.isEmpty()) {
			System.out.println("No rooms found");
		} else {
			System.out.println("==========ALL ROOMS===========");
			for (Room room : allRooms) {
				System.out.println(room);
			}
			System.out.println("==========ALL ROOMS===========");
		}
	}

	public Room findRoomById(Long id) {
		try {
			Room foundRoom = roomRepository.findById(id);
			if (foundRoom != null) {
				System.out.println("========ROOM=======");
				System.out.println(foundRoom);
				System.out.println("=======ROOM========");
				return foundRoom;
			} else {
				throw new RoomNotFoundException("Room not found");
			}
		} catch (
				RoomNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public void deleteRoomById(Long roomid) {
		Room foundRoom = findRoomById(roomid);
		if (foundRoom != null) {
			System.out.println("========ROOM=======");
			System.out.println(foundRoom);
			System.out.println("=======ROOM=======");
			System.out.println("Are you sure you want to delete this room? (y/n)");
			String answer = scanner.next();
			if (answer.equalsIgnoreCase("y")) {
				roomRepository.deleteById(foundRoom);
			} else {
				System.out.println("Delete operation failed!!!");
			}
		} else {
			System.out.println("Delete operation failed!!!");
		}


	}
}
