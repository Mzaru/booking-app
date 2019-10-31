package com.mzaru.booking.service;

import com.mzaru.booking.dao.IRoomDao;
import com.mzaru.booking.dto.RoomDto;
import com.mzaru.booking.entity.Room;
import com.mzaru.booking.exception.WrongAdminPasswordException;
import com.mzaru.booking.exception.room.RoomAlreadyExistsException;
import com.mzaru.booking.exception.room.RoomNotFoundException;
import com.mzaru.booking.validator.RoomValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

class RoomServiceTest {

    @Mock
    private IRoomDao roomDao;
    @Mock
    private Environment env;
    @Mock
    RoomValidator validator;
    private final String correctPassword = "correctPassword";
    private final String incorrectPassword = "incorrectPassword";
    private final String name = "name";
    @InjectMocks
    private RoomService unit = new RoomService();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddRoomWithCorrectPassword() {
        Room room = new Room();
        RoomDto wrapper = new RoomDto(room, correctPassword);
        when(env.getProperty("admin.password")).thenReturn(correctPassword);

        unit.addRoom(wrapper);

        verify(roomDao, Mockito.times(1)).addRoom(room);
    }

    @Test
    void testAddRoomWithIncorrectPassword() {
        Room room = new Room();
        RoomDto wrapper = new RoomDto(room, incorrectPassword);
        when(env.getProperty("admin.password")).thenReturn(correctPassword);

        assertThrows(WrongAdminPasswordException.class, () -> unit.addRoom(wrapper));
    }

    @Test
    void testAddRoomWithIncorrectName() {
        Room room = new Room();
        room.setName(name);
        RoomDto wrapper = new RoomDto(room, correctPassword);
        when(env.getProperty("admin.password")).thenReturn(correctPassword);
        when(roomDao.getRoomByName(name)).thenReturn(room);

        assertThrows(RoomAlreadyExistsException.class, () -> unit.addRoom(wrapper));
    }

    @Test
    void testAddRoomWithNullProjector() {
        Room room = new Room();
        room.setName(name);
        RoomDto wrapper = new RoomDto(room, correctPassword);
        when(env.getProperty("admin.password")).thenReturn(correctPassword);

        unit.addRoom(wrapper);

        assertFalse(room.getHasProjector());
    }

    @Test
    void getAllRooms() {
        List expectedResult = new ArrayList();
        when(roomDao.getAllRooms()).thenReturn(expectedResult);

        List actualResult = unit.getAllRooms();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void editRoomWithCorrectPassword() {
        Room room = new Room();
        RoomDto wrapper = new RoomDto(room, correctPassword);
        room.setName(name);
        when(roomDao.getRoomByName(name)).thenReturn(room);
        when(env.getProperty("admin.password")).thenReturn(correctPassword);

        unit.editRoom(wrapper);

        verify(roomDao, times(1)).editRoom(room);
        verify(validator, times(1)).validate(room);
    }

    @Test
    void editRoomWithIncorrectPassword() {
        Room room = new Room();
        RoomDto wrapper = new RoomDto(room, incorrectPassword);
        room.setName(name);
        when(roomDao.getRoomByName(name)).thenReturn(room);
        when(env.getProperty("admin.password")).thenReturn(correctPassword);

        assertThrows(WrongAdminPasswordException.class, () -> unit.editRoom(wrapper));
    }

    @Test
    void editRoomWithIncorrectName() {
        Room room = new Room();
        RoomDto wrapper = new RoomDto(room, correctPassword);
        room.setName(name);
        when(roomDao.getRoomByName(name)).thenReturn(null);
        when(env.getProperty("admin.password")).thenReturn(correctPassword);

        assertThrows(RoomNotFoundException.class, () -> unit.editRoom(wrapper));
    }

    @Test
    void deleteRoomWithCorrectPassword() {
        Room room = new Room();
        RoomDto wrapper = new RoomDto(room, correctPassword);
        room.setName(name);
        when(roomDao.getRoomByName(name)).thenReturn(room);
        when(env.getProperty("admin.password")).thenReturn(correctPassword);

        unit.deleteRoom(wrapper);

        verify(roomDao, times(1)).deleteRoom(room);
    }

    @Test
    void deleteRoomWithIncorrectPassword() {
        Room room = new Room();
        RoomDto wrapper = new RoomDto(room, incorrectPassword);
        room.setName(name);
        when(roomDao.getRoomByName(name)).thenReturn(room);
        when(env.getProperty("admin.password")).thenReturn(correctPassword);

        assertThrows(WrongAdminPasswordException.class, () -> unit.deleteRoom(wrapper));
    }

    @Test
    void deleteRoomWithIncorrectName() {
        Room room = new Room();
        RoomDto wrapper = new RoomDto(room, correctPassword);
        room.setName(name);
        when(roomDao.getRoomByName(name)).thenReturn(null);
        when(env.getProperty("admin.password")).thenReturn(correctPassword);

        assertThrows(RoomNotFoundException.class, () -> unit.deleteRoom(wrapper));
    }

    @Test
    void getRoomByName() {
        Room room = new Room();
        when(roomDao.getRoomByName(name)).thenReturn(room);

        Room result = unit.getRoomByName(name);

        assertEquals(result, room);
    }
}