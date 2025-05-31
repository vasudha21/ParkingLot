package manager;

import data.VehicleType;

public class VehicleTypeManagerFactory {
    private static VehicleTypeManagerFactory instance;
    private static final Object lock = new Object();

    private VehicleTypeManagerFactory() {
        // Private constructor
    }

    public static VehicleTypeManagerFactory getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new VehicleTypeManagerFactory();
                }
            }
        }
        return instance;
    }

    public VehicleParkingManager getVehicleParkingManager(VehicleType vehicleType) {
        switch (vehicleType) {
            case TWO_WHEELER:
                return new TwoWheelerManager();
            case FOUR_WHEELER:
                return new FourWheelerManager();
            case HEAVY_VEHICLE:
                return new HeavyWheelerManager();
            default:
                throw new IllegalArgumentException("Unsupported vehicle type: " + vehicleType);
        }
    }
}
