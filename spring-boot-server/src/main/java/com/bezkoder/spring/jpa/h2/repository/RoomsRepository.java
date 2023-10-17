package com.bezkoder.spring.jpa.h2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bezkoder.spring.jpa.h2.model.Room;

/**
 * Repository interface for CRUD operations on the Room entity.
 * Extends JpaRepository to leverage built-in CRUD operations for the Room entity.
 */
public interface RoomsRepository extends JpaRepository<Room, Long> {

}