package ticket;

import data.ParkingSpot;
import data.PaymentStatus;
import data.Ticket;
import data.Vehicle;

import java.time.LocalDateTime;
import java.util.UUID;

public class TicketGenerator {
    private static final TicketGenerator instance = new TicketGenerator();

    private TicketGenerator() {
        // Private constructor
    }

    public static TicketGenerator getInstance() {
        return instance;
    }

    public Ticket generateTicket(Vehicle vehicle, ParkingSpot parkingSpot) {
        String ticketNum = generateUniqueTicketNum();
        LocalDateTime entryTime = LocalDateTime.now();

        return new Ticket.TicketBuilder()
                .refNum(ticketNum)
                .vehicle(vehicle)
                .parkingSpot(parkingSpot)
                .entryTime(entryTime)
                .paymentStatus(PaymentStatus.PENDING)
                .build();
    }

    private String generateUniqueTicketNum() {
        return UUID.randomUUID().toString();
    }
}
