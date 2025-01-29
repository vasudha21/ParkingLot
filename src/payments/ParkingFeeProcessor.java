package payments;

import data.Ticket;
import manager.VehicleParkingManager;
import manager.VehicleTypeManagerFactory;

public class ParkingFeeProcessor {

    public double getParkingFees(Ticket ticket){
        double duration =0;
        /*
        logic to figure out duration
        ticket contains vehicle-> vehicle contain vehicletype
         */
        return VehicleTypeManagerFactory.getVehicleParkingManager(ticket.getVehicle().getVehicleType()).getParkingFees(duration);
    }

    //pass payment process
    public boolean processParkingFees(Ticket ticket, PaymentProcessor paymentProcessor){
        //some critical info from client side (like payment amount) can't be trusted, so strict check and validation for those
        if(getParkingFees(ticket)!=paymentProcessor.getAmount()) throw new RuntimeException("error in payment");
        return paymentProcessor.executePayment();
    }



}
