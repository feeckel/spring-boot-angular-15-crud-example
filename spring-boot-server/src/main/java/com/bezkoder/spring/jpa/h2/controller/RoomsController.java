package com.bezkoder.spring.jpa.h2.controller;


import java.util.List;
import java.util.Optional;

import com.bezkoder.spring.jpa.h2.model.Room;
import com.bezkoder.spring.jpa.h2.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



/**
 * Controller to handle room related operations.
 */
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RoomsController {

	/** Service layer to handle business logic related to rooms. */
	private final RoomService roomService;

	/**
	 * Retrieve all rooms.
	 *
	 * @return A ResponseEntity containing the list of all rooms or HttpStatus.NO_CONTENT if no rooms exist.
	 */
	@GetMapping("/rooms")
	public ResponseEntity<List<Room>> getAllRooms() {
		try {
			List<Room> rooms = roomService.getAllRooms();
			if (rooms.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(rooms, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Retrieve a specific room by ID.
	 *
	 * @param id ID of the room to retrieve.
	 * @return A ResponseEntity containing the room if found, or HttpStatus.NOT_FOUND otherwise.
	 */
	@GetMapping("/rooms/{id}")
	public ResponseEntity<Room> getRoomById(@PathVariable("id") long id) {
		Optional<Room> roomData = roomService.getRoomById(id);
		return roomData.map(room -> new ResponseEntity<>(room, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}


	/**
	 * Create a new room.
	 *
	 * @param room Room details to create.
	 * @return A ResponseEntity containing the created room.
	 */
	@PostMapping("/rooms")
	public ResponseEntity<Room> createRoom(@RequestBody Room room) {
		try {
			Room _room = roomService.createRoom(room);
			return new ResponseEntity<>(_room, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	/**
	 * Update details of a specific room.
	 *
	 * @param id ID of the room to update.
	 * @param room Updated room details.
	 * @return A ResponseEntity containing the updated room or HttpStatus.NOT_FOUND if not found.
	 */
	@PutMapping("/rooms/{id}")
	public ResponseEntity<Room> updateRoom(@PathVariable("id") long id, @RequestBody Room room) {
		Optional<Room> roomData = roomService.updateRoom(id, room);

		return roomData.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}


	/**
	 * Deletes a specific room by ID.
	 *
	 * @param id ID of the room to delete.
	 * @return A ResponseEntity indicating the result of the deletion. HttpStatus.NO_CONTENT if the deletion was successful, or HttpStatus.INTERNAL_SERVER_ERROR if there was an error during deletion.
	 */
	@DeleteMapping("/rooms/{id}")
	public ResponseEntity<HttpStatus> deleteRoom(@PathVariable("id") long id) {
		try {
			roomService.deleteRoom(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
