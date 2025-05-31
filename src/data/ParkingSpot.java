package data;

public class ParkingSpot {

    private final String floorNumber;
    private final VehicleType vehicleType;
    private final String name;
    private ParkingSpotState state;

    public ParkingSpot(String floorNumber, VehicleType vehicleType, String name, boolean isFree) {
        this.floorNumber = floorNumber;
        this.vehicleType = vehicleType;
        this.name = name;
        this.state = isFree ? new AvailableState() : new OccupiedState();
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
        return state.isFree();
    }

    public void park() {
        state = state.park();
    }

    public void vacate() {
        state = state.vacate();
    }

    public void reserve() {
        state = state.reserve();
    }

    public void markForMaintenance() {
        state = state.markForMaintenance();
    }

    // State interface
    private interface ParkingSpotState {
        boolean isFree();
        ParkingSpotState park();
        ParkingSpotState vacate();
        ParkingSpotState reserve();
        ParkingSpotState markForMaintenance();
    }

    // Concrete states
    private static class AvailableState implements ParkingSpotState {
        @Override
        public boolean isFree() {
            return true;
        }

        @Override
        public ParkingSpotState park() {
            return new OccupiedState();
        }

        @Override
        public ParkingSpotState vacate() {
            return this;
        }

        @Override
        public ParkingSpotState reserve() {
            return new ReservedState();
        }

        @Override
        public ParkingSpotState markForMaintenance() {
            return new MaintenanceState();
        }
    }

    private static class OccupiedState implements ParkingSpotState {
        @Override
        public boolean isFree() {
            return false;
        }

        @Override
        public ParkingSpotState park() {
            return this;
        }

        @Override
        public ParkingSpotState vacate() {
            return new AvailableState();
        }

        @Override
        public ParkingSpotState reserve() {
            return this;
        }

        @Override
        public ParkingSpotState markForMaintenance() {
            return new MaintenanceState();
        }
    }

    private static class ReservedState implements ParkingSpotState {
        @Override
        public boolean isFree() {
            return false;
        }

        @Override
        public ParkingSpotState park() {
            return new OccupiedState();
        }

        @Override
        public ParkingSpotState vacate() {
            return new AvailableState();
        }

        @Override
        public ParkingSpotState reserve() {
            return this;
        }

        @Override
        public ParkingSpotState markForMaintenance() {
            return new MaintenanceState();
        }
    }

    private static class MaintenanceState implements ParkingSpotState {
        @Override
        public boolean isFree() {
            return false;
        }

        @Override
        public ParkingSpotState park() {
            return this;
        }

        @Override
        public ParkingSpotState vacate() {
            return this;
        }

        @Override
        public ParkingSpotState reserve() {
            return this;
        }

        @Override
        public ParkingSpotState markForMaintenance() {
            return this;
        }
    }
}
