package com.bezkoder.spring.jpa.h2.controller;

import com.bezkoder.spring.jpa.h2.model.Room;
import com.bezkoder.spring.jpa.h2.service.RoomService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
public class RoomsControllerTest {

    @InjectMocks
    private RoomsController roomsController;

    @Mock
    private RoomService roomService;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(roomsController).build();
        objectMapper = new ObjectMapper();  // Für die Umwandlung von Objekten in JSON
    }

    @Test
    void testGetAllRooms() throws Exception {
        Room room = Room.builder().title("Room Title").description("Room Description").numberOfSeats(5).occupation(true).build();
        when(roomService.getAllRooms()).thenReturn(Arrays.asList(room));

        mockMvc.perform(get("/api/rooms"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void testGetRoomById() throws Exception {
        Room room = new Room();
        when(roomService.getRoomById(1L)).thenReturn(Optional.of(room));

        mockMvc.perform(get("/api/rooms/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    void testCreateRoom() throws Exception {
        Room room = Room.builder().title("Room Title").description("Room Description").numberOfSeats(5).occupation(true).build();
        Room savedRoom = Room.builder().title("Room Title").description("Room Description").numberOfSeats(5).occupation(true).build();

        when(roomService.createRoom(room)).thenReturn(savedRoom);

        mockMvc.perform(post("/api/rooms")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(room)))
                .andExpect(status().isCreated());
    }

    @Test
    void testUpdateRoom() throws Exception {
        Room room = Room.builder().title("Room Title").description("Room Description").numberOfSeats(5).occupation(true).build();
        Room updatedRoom = Room.builder().title("Room Title").description("Room Description").numberOfSeats(5).occupation(true).build();

        when(roomService.updateRoom(1L, room)).thenReturn(Optional.of(updatedRoom));

        mockMvc.perform(put("/api/rooms/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(room)))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateRoomNotFound() throws Exception {
        Room room = Room.builder().title("Room Title").description("Room Description").numberOfSeats(5).occupation(true).build();

        when(roomService.updateRoom(1L, room)).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/rooms/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(room)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteRoom() throws Exception {
        doNothing().when(roomService).deleteRoom(1L);

        mockMvc.perform(delete("/api/rooms/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteRoomInternalServerError() throws Exception {
        doThrow(new RuntimeException()).when(roomService).deleteRoom(1L);

        mockMvc.perform(delete("/api/rooms/1"))
                .andExpect(status().isInternalServerError());
    }

}
