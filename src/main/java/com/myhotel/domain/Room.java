package com.myhotel.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_room")
public class Room {

	@Id
	private Long id;

	@Column(nullable = false)
	private String number;

	@Column(nullable = false)
	private Integer capacity;

	@ManyToOne(fetch = FetchType.LAZY)//örnek
	@JoinColumn(nullable = false)
	private Hotel hotel; //bu oda hangi otelin

	//oda rez list: 1,2,3  /  rez list 1 id li olani cikardim : tablodan silelim

	@OneToMany(mappedBy = "room", orphanRemoval = true)
	private List<Reservation> reservations = new ArrayList<>();

	public Room() {
	}

	public Room(Long id, String number, Integer capacity, Hotel hotel) {
		this.id = id;
		this.number = number;
		this.capacity = capacity;
		this.hotel = hotel;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	@Override
	public String toString() {
		return "Room{" + "id=" + id + ", number='" + number + '\'' + ", capacity=" + capacity + '}';
	}
}
