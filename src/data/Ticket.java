package data;

import java.time.LocalDateTime;

public class Ticket {

    private final String refNum;
    private final Vehicle vehicle;
    private final ParkingSpot parkingSpot;
    private final LocalDateTime entryTime;
    private final LocalDateTime exitTime;
    private final double amount;
    private final PaymentStatus paymentStatus;

    private Ticket(TicketBuilder builder) {
        this.refNum = builder.refNum;
        this.vehicle = builder.vehicle;
        this.parkingSpot = builder.parkingSpot;
        this.entryTime = builder.entryTime;
        this.exitTime = builder.exitTime;
        this.amount = builder.amount;
        this.paymentStatus = builder.paymentStatus;
    }

    public String getRefNum() {
        return refNum;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public double getAmount() {
        return amount;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public static class TicketBuilder {
        private String refNum;
        private Vehicle vehicle;
        private ParkingSpot parkingSpot;
        private LocalDateTime entryTime;
        private LocalDateTime exitTime;
        private double amount;
        private PaymentStatus paymentStatus;

        public TicketBuilder refNum(String refNum) {
            this.refNum = refNum;
            return this;
        }

        public TicketBuilder vehicle(Vehicle vehicle) {
            this.vehicle = vehicle;
            return this;
        }

        public TicketBuilder parkingSpot(ParkingSpot parkingSpot) {
            this.parkingSpot = parkingSpot;
            return this;
        }

        public TicketBuilder entryTime(LocalDateTime entryTime) {
            this.entryTime = entryTime;
            return this;
        }

        public TicketBuilder exitTime(LocalDateTime exitTime) {
            this.exitTime = exitTime;
            return this;
        }

        public TicketBuilder amount(double amount) {
            this.amount = amount;
            return this;
        }

        public TicketBuilder paymentStatus(PaymentStatus paymentStatus) {
            this.paymentStatus = paymentStatus;
            return this;
        }

        public Ticket build() {
            if (refNum == null || vehicle == null || parkingSpot == null || entryTime == null) {
                throw new IllegalStateException("Required fields are missing");
            }
            return new Ticket(this);
        }
    }
}
