package com.sqa.lab9_2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class SeatReservationTest {

    @Mock
    private SeatDAO seatDAO;

    @Test
    void shouldReturnTrue_whenRequestedSeatIsAvailable() throws SQLException {

        List<String> availableSeats = Arrays.asList("A1", "A2", "B5", "C10");

        when(seatDAO.fetchAvailableSeats()).thenReturn(availableSeats);
        SeatReservation seatReservation = new SeatReservation(seatDAO);

        boolean result = seatReservation.checkSeatAvailability("A2");

        assertTrue(result);
        verify(seatDAO, times(1)).fetchAvailableSeats();
    }

    @Test
    void shouldReturnFalse_whenRequestedSeatIsNotAvailable() throws SQLException {

        List<String> availableSeats = Arrays.asList("A1", "A2", "B5");
        when(seatDAO.fetchAvailableSeats()).thenReturn(availableSeats);

        SeatReservation seatReservation = new SeatReservation(seatDAO);

        boolean result = seatReservation.checkSeatAvailability("Z99");

        assertFalse(result);
    }

    @Test
    void shouldReturnFalse_whenNoSeatsAreAvailable() throws SQLException {

        when(seatDAO.fetchAvailableSeats()).thenReturn(Collections.emptyList());

        SeatReservation seatReservation = new SeatReservation(seatDAO);

        boolean result = seatReservation.checkSeatAvailability("A1");

        assertFalse(result);
    }
}