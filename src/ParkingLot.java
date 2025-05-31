package data;

import data.ParkingSpot;
import data.Vehicle;
import data.VehicleType;
import manager.VehicleParkingManager;
import manager.VehicleTypeManagerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class ParkingLot {
    private static ParkingLot instance;
    private static final ReentrantLock lock = new ReentrantLock();
    
    private final List<ParkingSpot> parkingSpots;
    private final VehicleTypeManagerFactory vehicleTypeManagerFactory;

    private ParkingLot() {
        this.parkingSpots = new ArrayList<>();
        this.vehicleTypeManagerFactory = VehicleTypeManagerFactory.getInstance();
        initializeParkingSpots();
    }

    public static ParkingLot getInstance() {
        if (instance == null) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new ParkingLot();
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    private void initializeParkingSpots() {
        // Initialize parking spots for different vehicle types
        // This is a sample initialization
        for (int i = 1; i <= 10; i++) {
            parkingSpots.add(new ParkingSpot("1", VehicleType.TWO_WHEELER, "A" + i, true));
        }
        for (int i = 1; i <= 20; i++) {
            parkingSpots.add(new ParkingSpot("1", VehicleType.FOUR_WHEELER, "B" + i, true));
        }
        for (int i = 1; i <= 5; i++) {
            parkingSpots.add(new ParkingSpot("1", VehicleType.HEAVY_VEHICLE, "C" + i, true));
        }
    }

    public List<ParkingSpot> getAvailableSpots(VehicleType vehicleType) {
        VehicleParkingManager manager = vehicleTypeManagerFactory.getVehicleParkingManager(vehicleType);
        return manager.getParkingSpots();
    }

    public boolean parkVehicle(Vehicle vehicle, ParkingSpot spot) {
        if (!spot.isFree()) {
            return false;
        }
        spot.park();
        return true;
    }

    public boolean vacateSpot(ParkingSpot spot) {
        spot.vacate();
        return true;
    }

    public boolean reserveSpot(ParkingSpot spot) {
        if (!spot.isFree()) {
            return false;
        }
        spot.reserve();
        return true;
    }

    public boolean markSpotForMaintenance(ParkingSpot spot) {
        spot.markForMaintenance();
        return true;
    }
} 