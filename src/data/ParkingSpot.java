package data;

public class ParkingSpot {

    private final String floorNumber;
    private final VehicleType vehicleType;
    private final String name;
    private final boolean isFree;


    public ParkingSpot(String floorNumber, VehicleType vehicleType, String name, boolean isFree) {
        this.floorNumber = floorNumber;
        this.vehicleType = vehicleType;
        this.name = name;
        this.isFree = isFree;
    }

    public String getFloorNumber() {
        return floorNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public String getName() {
        return name;
    }

    public boolean isFree() {
        return isFree;
    }
}
