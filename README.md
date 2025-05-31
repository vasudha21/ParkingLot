# Parking Lot System

## Overview
A robust parking lot management system that handles different types of vehicles, parking spots, and payment processing. The system is designed with scalability, maintainability, and extensibility in mind.

## System Architecture

### Component-Level Design Pattern Usage
```mermaid
graph TB
    subgraph Singleton_Pattern
        PL[ParkingLot<br>Singleton]
        VMF[VehicleTypeManagerFactory<br>Singleton]
        TG[TicketGenerator<br>Singleton]
    end

    subgraph State_Pattern
        PS[ParkingSpot]
        PS --> PSI[ParkingSpotState<br>Interface]
        PSI --> AS[AvailableState]
        PSI --> OS[OccupiedState]
        PSI --> RS[ReservedState]
        PSI --> MS[MaintenanceState]
    end

    subgraph Factory_Pattern
        VMF --> VPMI[VehicleParkingManager<br>Interface]
        VPMI --> TWM[TwoWheelerManager]
        VPMI --> FWM[FourWheelerManager]
        VPMI --> HWM[HeavyWheelerManager]
    end

    subgraph Builder_Pattern
        T[Ticket]
        T --> TB[TicketBuilder]
        TB --> |builds| T
    end

    %% Component Relationships
    PL --> PS
    PL --> VMF
    PL --> TG
    TG --> T
    VMF --> VPMI

    %% Design Pattern Legend
    classDef singleton fill:#f9f,stroke:#333,stroke-width:2px
    classDef state fill:#bbf,stroke:#333,stroke-width:2px
    classDef factory fill:#bfb,stroke:#333,stroke-width:2px
    classDef builder fill:#fbb,stroke:#333,stroke-width:2px

    class PL,VMF,TG singleton
    class PS,PSI,AS,OS,RS,MS state
    class VMF,VPMI,TWM,FWM,HWM factory
    class T,TB builder
```

### Class Diagram
```mermaid
classDiagram
    %% Core Classes
    class ParkingLot {
        -ParkingLot instance
        -ReentrantLock lock
        -List<ParkingSpot> parkingSpots
        -VehicleTypeManagerFactory vehicleTypeManagerFactory
        +getInstance() ParkingLot
        +getAvailableSpots(VehicleType) List<ParkingSpot>
        +parkVehicle(Vehicle, ParkingSpot) boolean
        +vacateSpot(ParkingSpot) boolean
        +reserveSpot(ParkingSpot) boolean
        +markSpotForMaintenance(ParkingSpot) boolean
    }

    class ParkingSpot {
        -String floorNumber
        -VehicleType vehicleType
        -String name
        -ParkingSpotState state
        +isFree() boolean
        +park() void
        +vacate() void
        +reserve() void
        +markForMaintenance() void
    }

    class Ticket {
        -String refNum
        -Vehicle vehicle
        -ParkingSpot parkingSpot
        -LocalDateTime entryTime
        -LocalDateTime exitTime
        -double amount
        -PaymentStatus paymentStatus
        +getRefNum() String
        +getVehicle() Vehicle
        +getParkingSpot() ParkingSpot
        +getEntryTime() LocalDateTime
        +getExitTime() LocalDateTime
        +getAmount() double
        +getPaymentStatus() PaymentStatus
    }

    class Vehicle {
        <<interface>>
        +getVehicleType() VehicleType
        +getLicensePlate() String
    }

    %% Enums
    class VehicleType {
        <<enum>>
        TWO_WHEELER
        FOUR_WHEELER
        HEAVY_VEHICLE
    }

    class PaymentStatus {
        <<enum>>
        PENDING
        PROCESSING
        COMPLETED
        FAILED
    }

    %% State Pattern
    class ParkingSpotState {
        <<interface>>
        +isFree() boolean
        +park() ParkingSpotState
        +vacate() ParkingSpotState
        +reserve() ParkingSpotState
        +markForMaintenance() ParkingSpotState
    }

    class AvailableState {
        +isFree() boolean
        +park() ParkingSpotState
        +vacate() ParkingSpotState
        +reserve() ParkingSpotState
        +markForMaintenance() ParkingSpotState
    }

    class OccupiedState {
        +isFree() boolean
        +park() ParkingSpotState
        +vacate() ParkingSpotState
        +reserve() ParkingSpotState
        +markForMaintenance() ParkingSpotState
    }

    class ReservedState {
        +isFree() boolean
        +park() ParkingSpotState
        +vacate() ParkingSpotState
        +reserve() ParkingSpotState
        +markForMaintenance() ParkingSpotState
    }

    class MaintenanceState {
        +isFree() boolean
        +park() ParkingSpotState
        +vacate() ParkingSpotState
        +reserve() ParkingSpotState
        +markForMaintenance() ParkingSpotState
    }

    %% Factory Pattern
    class VehicleTypeManagerFactory {
        -VehicleTypeManagerFactory instance
        -Object lock
        +getInstance() VehicleTypeManagerFactory
        +getVehicleParkingManager(VehicleType) VehicleParkingManager
    }

    class VehicleParkingManager {
        <<interface>>
        +getParkingSpots() List<ParkingSpot>
    }

    class TwoWheelerManager {
        +getParkingSpots() List<ParkingSpot>
    }

    class FourWheelerManager {
        +getParkingSpots() List<ParkingSpot>
    }

    class HeavyWheelerManager {
        +getParkingSpots() List<ParkingSpot>
    }

    %% Builder Pattern
    class TicketBuilder {
        -String refNum
        -Vehicle vehicle
        -ParkingSpot parkingSpot
        -LocalDateTime entryTime
        -LocalDateTime exitTime
        -double amount
        -PaymentStatus paymentStatus
        +refNum(String) TicketBuilder
        +vehicle(Vehicle) TicketBuilder
        +parkingSpot(ParkingSpot) TicketBuilder
        +entryTime(LocalDateTime) TicketBuilder
        +exitTime(LocalDateTime) TicketBuilder
        +amount(double) TicketBuilder
        +paymentStatus(PaymentStatus) TicketBuilder
        +build() Ticket
    }

    %% Relationships
    ParkingLot --> ParkingSpot : manages
    ParkingLot --> VehicleTypeManagerFactory : uses
    ParkingSpot --> ParkingSpotState : has
    ParkingSpotState <|.. AvailableState : implements
    ParkingSpotState <|.. OccupiedState : implements
    ParkingSpotState <|.. ReservedState : implements
    ParkingSpotState <|.. MaintenanceState : implements
    VehicleTypeManagerFactory --> VehicleParkingManager : creates
    VehicleParkingManager <|.. TwoWheelerManager : implements
    VehicleParkingManager <|.. FourWheelerManager : implements
    VehicleParkingManager <|.. HeavyWheelerManager : implements
    Ticket --> TicketBuilder : created by
    Ticket --> Vehicle : contains
    Ticket --> ParkingSpot : references
    Ticket --> PaymentStatus : has
    Vehicle --> VehicleType : has
```

## Design Patterns Implementation

### 1. Singleton Pattern
- **Implementation**: `ParkingLot`, `VehicleTypeManagerFactory`, `TicketGenerator`
- **Purpose**: Ensure single instance of critical system components
- **Thread Safety**: Double-checked locking with ReentrantLock
- **Usage**:
  ```java
  // ParkingLot singleton
  private static ParkingLot instance;
  private static final ReentrantLock lock = new ReentrantLock();
  
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
  ```

### 2. State Pattern
- **Implementation**: `ParkingSpot` with `ParkingSpotState` interface
- **Purpose**: Manage parking spot states and their transitions
- **States**: Available, Occupied, Reserved, Maintenance
- **Usage**:
  ```java
  public class ParkingSpot {
      private ParkingSpotState state;
      
      public void park() {
          state = state.park();
      }
      
      public void vacate() {
          state = state.vacate();
      }
  }
  ```

### 3. Factory Pattern
- **Implementation**: `VehicleTypeManagerFactory`
- **Purpose**: Create appropriate vehicle parking managers
- **Usage**:
  ```java
  public VehicleParkingManager getVehicleParkingManager(VehicleType vehicleType) {
      switch (vehicleType) {
          case TWO_WHEELER:
              return new TwoWheelerManager();
          case FOUR_WHEELER:
              return new FourWheelerManager();
          case HEAVY_VEHICLE:
              return new HeavyWheelerManager();
      }
  }
  ```

### 4. Builder Pattern
- **Implementation**: `Ticket.TicketBuilder`
- **Purpose**: Construct complex Ticket objects with validation
- **Usage**:
  ```java
  public Ticket generateTicket(Vehicle vehicle, ParkingSpot parkingSpot) {
      return new Ticket.TicketBuilder()
              .refNum(generateUniqueTicketNum())
              .vehicle(vehicle)
              .parkingSpot(parkingSpot)
              .entryTime(LocalDateTime.now())
              .paymentStatus(PaymentStatus.PENDING)
              .build();
  }
  ```

## SOLID Principles Implementation

### 1. Single Responsibility Principle (SRP)
- **ParkingSpot**: Manages only parking spot state and properties
- **Ticket**: Handles only ticket-related information
- **VehicleParkingManager**: Manages only vehicle-specific parking logic
- **TicketGenerator**: Focuses solely on ticket generation

### 2. Open/Closed Principle (OCP)
- **Vehicle Types**: New vehicle types can be added without modifying existing code
- **Parking States**: New states can be added by implementing ParkingSpotState
- **Payment Methods**: New payment methods can be added without changing existing code
- **Pricing Strategies**: New pricing strategies can be added without modifying existing code

### 3. Liskov Substitution Principle (LSP)
- **Vehicle Implementations**: All vehicle types can be used interchangeably
- **ParkingSpotState**: All state implementations can be used interchangeably
- **VehicleParkingManager**: All manager implementations can be used interchangeably

### 4. Interface Segregation Principle (ISP)
- **Vehicle Interface**: Contains only vehicle-specific methods
- **ParkingSpotState Interface**: Contains only state-related methods
- **VehicleParkingManager Interface**: Contains only parking management methods

### 5. Dependency Inversion Principle (DIP)
- **ParkingLot**: Depends on VehicleParkingManager interface, not concrete implementations
- **ParkingSpot**: Depends on ParkingSpotState interface, not concrete states
- **TicketGenerator**: Depends on Ticket interface, not concrete implementation

## Additional Design Notes

### 1. Thread Safety
- Singleton implementations use double-checked locking
- ReentrantLock for ParkingLot instance
- Synchronized block for VehicleTypeManagerFactory

### 2. Error Handling
- Validation in TicketBuilder
- IllegalStateException for missing required fields
- IllegalArgumentException for unsupported vehicle types

### 3. Extensibility Points
- New vehicle types can be added
- New parking spot states can be added
- New payment methods can be added
- New pricing strategies can be added

### 4. Testing Considerations
- Interfaces allow for easy mocking
- State pattern enables state transition testing
- Builder pattern simplifies test object creation
- Factory pattern allows for test-specific implementations

### 5. Future Enhancements
- Add payment processing system
- Implement pricing strategies
- Add reservation system
- Add user authentication
- Add reporting system
- Add notification system

## Low-Level Design Preparation Notes

1. **Class Design**
   - Clear separation of concerns
   - Proper encapsulation
   - Immutable objects where appropriate
   - Thread-safe implementations

2. **Interface Design**
   - Small, focused interfaces
   - Clear contract definitions
   - Proper abstraction levels

3. **Error Handling**
   - Proper exception hierarchy
   - Meaningful error messages
   - Graceful error recovery

4. **Testing Strategy**
   - Unit test coverage
   - Integration test scenarios
   - Performance test cases
   - Edge case handling

5. **Documentation**
   - Clear class responsibilities
   - Method documentation
   - Design pattern usage
   - Extension points

6. **Code Quality**
   - Consistent naming conventions
   - Proper code organization
   - Clean code principles
   - SOLID principles adherence 