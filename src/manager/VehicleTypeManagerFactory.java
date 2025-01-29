package manager;

import data.VehicleType;

public class VehicleTypeManagerFactory {

    private VehicleTypeManagerFactory() {

    }

    public static VehicleParkingManager getVehicleParkingManager(VehicleType vehicleType) {
        VehicleParkingManager vehicleParkingManager;
        if(vehicleType.equals(VehicleType.TWO_WHEELER)) vehicleParkingManager = new TwoWheelerManager();
        return vehicleParkingManager;
    }
}
