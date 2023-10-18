package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.model.Room;
import com.bezkoder.spring.jpa.h2.repository.RoomsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service class for managing Room entities.
 * Provides methods for CRUD operations on rooms.
 */
@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomsRepository roomsRepository;

    /**
     * Fetches all rooms from the repository.
     *
     * @return a list of all rooms
     */
    public List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();
        roomsRepository.findAll().forEach(rooms::add);
        return rooms;
    }

    /**
     * Fetches a room by its ID.
     *
     * @param id the ID of the room
     * @return an Optional containing the room if found, empty otherwise
     */
    public Optional<Room> getRoomById(Long id) {
        return roomsRepository.findById(id);
    }

    /**
     * Creates a new room.
     *
     * @param room the room details
     * @return the created room
     */
    public Room createRoom(Room room) {
        return roomsRepository.save(Room.builder()
                .title(room.getTitle())
                .description(room.getDescription())
                .occupation(room.isOccupation())
                .numberOfSeats(room.getNumberOfSeats())
                .build());
    }

    /**
     * Updates an existing room.
     *
     * @param id   the ID of the room to be updated
     * @param room the new room details
     * @return an Optional containing the updated room if successful, empty otherwise
     */
    public Optional<Room> updateRoom(Long id, Room room) {
        Optional<Room> roomData = roomsRepository.findById(id);
        if (roomData.isPresent()) {
            Room _room = roomData.get();
            _room.setTitle(room.getTitle());
            _room.setNumberOfSeats(room.getNumberOfSeats());
            _room.setDescription(room.getDescription());
            _room.setOccupation(room.isOccupation());
            return Optional.of(roomsRepository.save(_room));
        } else {
            return Optional.empty();
        }
    }

    /**
     * Deletes a room by its ID.
     *
     * @param id the ID of the room to be deleted
     */
    public void deleteRoom(Long id) {
        roomsRepository.deleteById(id);
    }

}