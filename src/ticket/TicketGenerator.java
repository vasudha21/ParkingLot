package ticket;

import data.ParkingSpot;
import data.Ticket;
import data.Vehicle;

public class TicketGenerator {

    public Ticket generateTicket(Vehicle vehicle, ParkingSpot parkingSpot){
        String ticketNum = genUniqueTicketNum();

        /*
        check if parkingspot is free -> yes then allocate
        realworld -> can have racing condition -> solve using sync or lock
         */
        return new Ticket(ticketNum, vehicle, parkingSpot);
    }

    private String genUniqueTicketNum(){
        return "";
    }
}
