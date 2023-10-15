package com.bezkoder.spring.jpa.h2.controller;

import java.util.ArrayList;
import java.util.List;

import com.bezkoder.spring.jpa.h2.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.jpa.h2.repository.RoomsRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class RoomsController {

	@Autowired
	RoomsRepository roomsRepository;

	@GetMapping("/rooms")
	public ResponseEntity<List<Room>> getAllRoomss(@RequestParam(required = false) String title) {
		try {
			List<Room> Roomss = new ArrayList<Room>();

			if (title == null)
				roomsRepository.findAll().forEach(Roomss::add);
			else
				roomsRepository.findByTitleContaining(title).forEach(Roomss::add);

			if (Roomss.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(Roomss, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/rooms")
	public ResponseEntity<Room> createTutorial(@RequestBody Room room) {
		try {
			Room _room = roomsRepository
					.save(new Room(room.getTitle(), room.getDescription(), false));
			return new ResponseEntity<>(_room, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
