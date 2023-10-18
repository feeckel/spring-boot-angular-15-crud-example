package com.bezkoder.spring.jpa.h2.model;


import lombok.*;

import javax.persistence.*;

/**
 * Represents a room entity with various attributes such as title, description, number of seats, and occupation status.
 */
@Data
@Builder
@Entity
@Table(name = "Room")
@AllArgsConstructor
@NoArgsConstructor
public class Room {

	/**
	 * The unique identifier for the room.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	/**
	 * The title or name of the room.
	 */
	@Column(name = "title")
	private String title;

	/**
	 * A brief description about the room.
	 */
	@Column(name = "description")
	private String description;

	/**
	 * Specifies the number of seats available in the room.
	 */
	@Column(name = "numberOfSeats")
	private int numberOfSeats;

	/**
	 * Indicates whether the room is occupied or not.
	 */
	@Column(name = "occupation")
	private boolean occupation;
}