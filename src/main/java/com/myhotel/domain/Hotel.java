package com.myhotel.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_hotel")
public class Hotel {

	@Id
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String location;

	//A oteli 11,12,13 oda listesinden 11 i cikarsam: 12,13 -->room tablosunda kalmaya devam etsin
	//orphan removal : 11 i tablodadan da silerdi
	@OneToMany(mappedBy = "hotel", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	private List<Room> rooms = new ArrayList<>();

	public Hotel() {
	}

	public Hotel(Long id, String name, String location) {
		this.id = id;
		this.name = name;
		this.location = location;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	@Override
	public String toString() {
		return "Hotel{" + "id=" + id + ", name='" + name + '\'' + ", location='" + location + '\'' + ", rooms=" + rooms + '}';
	}
}
