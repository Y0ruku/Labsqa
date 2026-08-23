package com.sqa.lab9_2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class GateCheckinTest {

    @Mock
    private TicketCounter ticketCounter;

    @Test
    void shouldRegisterCustomerAndNotifyTicketCounter_whenNewTicketIsScanned() {

        GateCheckin gateCheckin = new GateCheckin(ticketCounter);
        gateCheckin.customerEntry(1001);

        assertTrue(gateCheckin.getPassengersOnBoard().contains(1001));
        verify(ticketCounter, times(1)).changeTicketStatus(true);
    }

    @Test
    void shouldNotNotifyTicketCounterAgain_whenSameTicketIsScannedTwice() {

        GateCheckin gateCheckin = new GateCheckin(ticketCounter);
        gateCheckin.customerEntry(2002);
        gateCheckin.customerEntry(2002);

        assertFalse(gateCheckin.customerIsEligible(2002));
        verify(ticketCounter, times(1)).changeTicketStatus(true);
    }

    @Test
    void shouldNotifyTicketCounterOncePerDistinctCustomer_whenMultipleCustomersEnter() {

        GateCheckin gateCheckin = new GateCheckin(ticketCounter);

        gateCheckin.customerEntry(1001);
        gateCheckin.customerEntry(1002);
        gateCheckin.customerEntry(1003);

        assertTrue(gateCheckin.getPassengersOnBoard().size() == 3);
        verify(ticketCounter, times(3)).changeTicketStatus(true);
    }
}