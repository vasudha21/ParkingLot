package manager;

public interface VehicleParkingManager {

    List<ParkingSpot> getParkingSpots();

    double getParkingFees(double durationInHours);
}
