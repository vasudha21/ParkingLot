package finder;

import manager.VehicleParkingManager;
import selector.ParkingSpotSelector;

public class ParkingSpotFinder {


    private final VehicleParkingManager vehicleParkingManager;
    private final ParkingSpotSelector parkingSpotSelector;

    public ParkingSpotFinder(VehicleParkingManager vehicleParkingManager, ParkingSpotSelector parkingSpotSelector) {
        this.vehicleParkingManager = vehicleParkingManager;
        this.parkingSpotSelector = parkingSpotSelector;
    }

    /*
    strategy methos
     */
    public ParkingSpot findParkingSpot(){
        List<ParkingSpot> parkingSpotList = this.vehicleParkingManager.getParkingSpots();
        return this.parkingSpotSelector.selectSpot(parkingSpotList);
    }
}
